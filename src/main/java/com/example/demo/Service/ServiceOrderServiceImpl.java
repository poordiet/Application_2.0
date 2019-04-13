package com.example.demo.Service;

import com.example.demo.Models.*;
import com.example.demo.Presentation.ServiceOrderLinePresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ServiceOrderServiceImpl implements ServiceOrderService {

    @Autowired
    ServiceOrderRepository serviceOrderRepository;

    @Autowired
    ServiceOrderLineRepository serviceOrderLineRepository;

    @Autowired
    ServiceOrderStatusRepository serviceOrderStatusRepository;

    @Autowired
    CustomerSiteService customerSiteService;

    @Autowired
    CountryService countryService;

    @Autowired
    StateProvinceService stateProvinceService;

    @Autowired
    ContactService contactService;

    @Autowired
    CustomerSiteStatusRepository customerSiteStatusRepository;

    @Autowired
    CustomerSiteTypeRepository customerSiteTypeRepository;

    @Autowired
    ContactTypeRepository contactTypeRepository;

    @Autowired
    ContactStatusRepository contactStatusRepository;

    @Autowired
    ContractorRepository contractorRepository;

    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    AssignmentStatusRepository assignmentStatusRepository;

    @Autowired
    SvcService svcService;

    @Override
    public void addServiceOrder(ServiceOrder serviceOrder) { serviceOrderRepository.save(serviceOrder);}

    @Override
    public List<ServiceOrder> findAll() {
        return serviceOrderRepository.findAllByOrderByDateScheduledDesc();
    }

    @Override
    public List<ServiceOrder> findServiceOrderInProgress(){
        return serviceOrderRepository.findServiceOrderInProgress();
    }

    @Override
    public ServiceOrder findServiceOrderBySvoId(int svoId){
        return serviceOrderRepository.findServiceOrderBySvoId(svoId);
    }

    @Override
    public void saveServiceOrder(ServiceOrder serviceOrder)
    {
        serviceOrderRepository.save(serviceOrder);
    }


    @Override
    public void saveServiceOrderFromForm(ServiceOrderPresentation serviceOrderPresentation)
    {
        ServiceOrder serviceOrder = new ServiceOrder();
        CustomerSite customerSite = new CustomerSite();
        Contact contact = new Contact();

        // Add Customer Site

        customerSite.setCustSiteAddress(serviceOrderPresentation.getCustSiteAddress());


        customerSite.setCustSiteCity(serviceOrderPresentation.getCustSiteCity());
        customerSite.setCustSiteEmail(serviceOrderPresentation.getCustSiteEmail());
        customerSite.setCustSiteName(serviceOrderPresentation.getCustSiteName());
        customerSite.setCustSiteNumber(serviceOrderPresentation.getCustSiteNumber());
        customerSite.setCustSiteStart(serviceOrderPresentation.getCustSiteStart());
        customerSite.setCustSiteZip(serviceOrderPresentation.getCustSiteZip());
        customerSite.setCustSitePhone(serviceOrderPresentation.getCustSitePhone());

        // get current date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        customerSite.setCustSiteStart(currentSqlDate);

        CustomerSiteStatus customerSiteStatus = customerSiteStatusRepository.findByCustSiteStatusId(1);
        customerSite.setCustomerSiteStatus(customerSiteStatus);

        CustomerSiteType customerSiteType = customerSiteTypeRepository.findByCustSiteTypeId(1);
        customerSite.setCustomerSiteType(customerSiteType);

        customerSite.setStateProvince(stateProvinceService.findByStateId(serviceOrderPresentation.getStateId()));

        customerSite.setCountry(countryService.findByCountryId(serviceOrderPresentation.getCountryId()));

        customerSiteService.saveCustomerSite(customerSite);
        serviceOrder.setCustomerSite(customerSite);

        // Add Contact
        contact.setCustomerSite(customerSite);
        contact.setContactEmail(serviceOrderPresentation.getContactEmail());
        ContactStatus contactStatus = contactStatusRepository.findByContactStatusId(1);
        contact.setContactStatus(contactStatus);

        ContactType contactType = contactTypeRepository.findByContactTypeId(1);
        contact.setContactType(contactType);

        contact.setContactFname(serviceOrderPresentation.getContactFname());
        contact.setContactLname(serviceOrderPresentation.getContactLname());
        contact.setContactPhone(serviceOrderPresentation.getContactPhone());
        contactService.saveContact(contact);
        serviceOrder.setContact(contact);

        // Add Service Order Status
        ServiceOrderStatus serviceOrderStatus = serviceOrderStatusRepository.findServiceOrderStatusBySvoStatusId(1);
        serviceOrder.setServiceOrderStatus(serviceOrderStatus);

        // Add Service order Information
        serviceOrder.setDateRequested(currentSqlDate);

        //serviceOrder.setDateScheduled(serviceOrderPresentation.getDateScheduled());
        // temp place holder
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        serviceOrder.setDateScheduled(timestamp);
        serviceOrder.setWorkRequest(serviceOrderPresentation.getWorkRequest());

        // This must be before the service order lines because service order lines need the Service Order Id, and Svo_id is assigned once it is saved
        serviceOrderRepository.save(serviceOrder);


        Set<ServiceOrderLine> serviceOrderLines = new HashSet<>();
        // Add Service Order Lines
        for (Svc svc: serviceOrderPresentation.getSvcs()
        ) {
            ServiceOrderLine serviceOrderLine = new ServiceOrderLine();
            serviceOrderLine.setServiceOrder(serviceOrder);
            serviceOrderLine.setSvc(svc);
            serviceOrderLines.add(serviceOrderLine);
            serviceOrderLineRepository.save(serviceOrderLine);
        }


        serviceOrderPresentation.setServiceOrderLines(serviceOrderLines);
        for(ServiceOrderLine serviceOrderLine: serviceOrderPresentation.getServiceOrderLines())
        {
            System.out.println(serviceOrderLine.getSvc());
            System.out.println(serviceOrderLine.getSvoLineId());
        }

    }

    @Override
    public ServiceOrderStatus findServiceOrderStatusBySvoStatusId(int svoStatusId){
        return serviceOrderStatusRepository.findServiceOrderStatusBySvoStatusId(svoStatusId);
    }

    public List<ServiceOrderPresentation> getServiceOrderPresentation(List<ServiceOrder> serviceOrders){

        List<ServiceOrderPresentation> serviceOrderPresentations = new ArrayList<>();


        for (ServiceOrder serviceOrder1:serviceOrders) {

            int count = 0;

            ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
            String svcName = new String();
            String contactName;
            String contractors = new String();
            String location;

            serviceOrderPresentation.setDateFinished(serviceOrder1.getDateFinished());
            serviceOrderPresentation.setDateRequested(serviceOrder1.getDateRequested());
            serviceOrderPresentation.setDateScheduled(serviceOrder1.getDateScheduled());
            serviceOrderPresentation.setDateStarted(serviceOrder1.getDateStarted());
            serviceOrderPresentation.setSvoId(serviceOrder1.getSvoId());
            serviceOrderPresentation.setTotal(serviceOrder1.getTotal());
            serviceOrderPresentation.setServiceOrderLines(serviceOrder1.getServiceOrderLines());
            serviceOrderPresentation.setCustSiteId(serviceOrder1.getCustomerSite().getCustSiteId());
            serviceOrderPresentation.setCustSiteNumber(serviceOrder1.getCustomerSite().getCustSiteNumber());
            serviceOrderPresentation.setCustSiteName(serviceOrder1.getCustomerSite().getCustSiteName());
            serviceOrderPresentation.setCustSitePhone(serviceOrder1.getCustomerSite().getCustSitePhone());
            serviceOrderPresentation.setCustSiteEmail(serviceOrder1.getCustomerSite().getCustSiteEmail());
            serviceOrderPresentation.setSvoStatus(serviceOrder1.getServiceOrderStatus().getSvoStatus());

            location = serviceOrder1.getCustomerSite().getCustSiteAddress()+ " " + serviceOrder1.getCustomerSite().getCustSiteCity() + ", "+
                    serviceOrder1.getCustomerSite().getStateProvince().getStateName() + ", " + serviceOrder1.getCustomerSite().getCustSiteZip()
                    +", " + serviceOrder1.getCustomerSite().getCountry().getCountryName();

            serviceOrderPresentation.setCustSiteLocation(location);

           contactName = serviceOrder1.getContact().getContactFname() + " " + serviceOrder1.getContact().getContactLname();
           serviceOrderPresentation.setContactName(contactName);

            Set<ServiceOrderLine> serviceOrderLines = serviceOrder1.getServiceOrderLines();


            int count2 = 0;

            for(ServiceOrderLine serviceOrderLine1:serviceOrderLines)
            {

                serviceOrderLine1.getSvc().getSvcName();
                if(count > 0)
                {
                    svcName += ", ";
                }
                svcName+=serviceOrderLine1.getSvc().getSvcName();
                //System.out.println(svcName);
                serviceOrderPresentation.setSvcName(svcName);
                count++;


                Set<Assignment> assignments = serviceOrderLine1.getAssignments();

                for(Assignment assignment: assignments) {
                    if (count2 > 0) {
                        contractors += ", ";
                    }
                    contractors += assignment.getContractor().getContractorFname() + " " + assignment.getContractor().getContractorLname();
                    //System.out.println(contractors);
                    serviceOrderPresentation.setContractors(contractors);
                    count2++;
                }
            }

            serviceOrderPresentations.add(serviceOrderPresentation);

        }
        return serviceOrderPresentations;
    }

    // Create a Service Order Presentation for a Service Order Profile
    public ServiceOrderPresentation getServiceOrderPresentationForProfile(ServiceOrder serviceOrder){

        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();

        System.out.println(serviceOrder.getSvoId());

        serviceOrderPresentation.setSvoId(serviceOrder.getSvoId());

        System.out.println(serviceOrderPresentation.getSvoId());
        serviceOrderPresentation.setCustSiteId(serviceOrder.getCustomerSite().getCustSiteId());
        serviceOrderPresentation.setCustSiteName(serviceOrder.getCustomerSite().getCustSiteName());
        String location = serviceOrder.getCustomerSite().getCustSiteAddress()+ " " + serviceOrder.getCustomerSite().getCustSiteCity() + ", "+
                serviceOrder.getCustomerSite().getStateProvince().getStateName() + ", " + serviceOrder.getCustomerSite().getCustSiteZip()
                +", " + serviceOrder.getCustomerSite().getCountry().getCountryName();

        serviceOrderPresentation.setCustSiteLocation(location);
        serviceOrderPresentation.setCustSitePhone(serviceOrder.getCustomerSite().getCustSitePhone());
        serviceOrderPresentation.setCustSiteEmail(serviceOrder.getCustomerSite().getCustSiteEmail());

        serviceOrderPresentation.setDateRequested(serviceOrder.getDateRequested());
        serviceOrderPresentation.setDateScheduled(serviceOrder.getDateScheduled());
        serviceOrderPresentation.setDateFinished(serviceOrder.getDateFinished());
        serviceOrderPresentation.setDateStarted(serviceOrder.getDateStarted());

        serviceOrderPresentation.setContactId(serviceOrder.getContact().getContactId());
        serviceOrderPresentation.setContactName(serviceOrder.getContact().getContactFname() + ' ' + serviceOrder.getContact().getContactLname());
        serviceOrderPresentation.setContactPhone(serviceOrder.getContact().getContactPhone());
        serviceOrderPresentation.setContactEmail(serviceOrder.getContact().getContactEmail());
        serviceOrderPresentation.setContactType(serviceOrder.getContact().getContactType().getContactType());
        serviceOrderPresentation.setContactStatus(serviceOrder.getContact().getContactStatus().getContactStatus());

        serviceOrderPresentation.setWorkRequest(serviceOrder.getWorkRequest());
        serviceOrderPresentation.setWorkSummary(serviceOrder.getWorkSummary());
        serviceOrderPresentation.setSvoStatus(serviceOrder.getServiceOrderStatus().getSvoStatus());

        serviceOrderPresentation.setServiceOrderLines(serviceOrder.getServiceOrderLines());


        return serviceOrderPresentation;
    }

    // for displaying service order line and services in the same table
    public List<ServiceOrderLinePresentation> getServiceOrderLinePresentation(Set<ServiceOrderLine> serviceOrderLines){

        List<ServiceOrderLinePresentation> serviceOrderLinePresentations = new ArrayList<>();



            for(ServiceOrderLine serviceOrderLine:serviceOrderLines)
            {
                ServiceOrderLinePresentation serviceOrderLinePresentation = new ServiceOrderLinePresentation();

                System.out.println(serviceOrderLine.getSvoLineId());
                serviceOrderLinePresentation.setSvcName(serviceOrderLine.getSvc().getSvcName());
                serviceOrderLinePresentation.setSvcId(serviceOrderLine.getSvc().getSvcId());
                serviceOrderLinePresentation.setSvoLineId(serviceOrderLine.getSvoLineId());

            serviceOrderLinePresentations.add(serviceOrderLinePresentation);
                for(ServiceOrderLinePresentation serviceOrderLinePresentation1:serviceOrderLinePresentations)
                {
                    System.out.println(serviceOrderLinePresentation.getSvcName());
                    System.out.println(serviceOrderLinePresentation.getSvoLineId());
                }
        }
        return serviceOrderLinePresentations;
    }



    public ServiceOrderPresentation addCustomerSite(CustomerSite customerSite){

        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();

        serviceOrderPresentation.setCustomerSite(customerSite);
        serviceOrderPresentation.setCustSiteId(customerSite.getCustSiteId());
        serviceOrderPresentation.setCustSiteEmail(customerSite.getCustSiteEmail());
        serviceOrderPresentation.setCustSitePhone(customerSite.getCustSitePhone());
        serviceOrderPresentation.setCustSiteNumber(customerSite.getCustSiteNumber());
        serviceOrderPresentation.setCustSiteName(customerSite.getCustSiteName());
        serviceOrderPresentation.setCustSiteAddress(customerSite.getCustSiteAddress());
        serviceOrderPresentation.setCustSiteCity(customerSite.getCustSiteCity());
        serviceOrderPresentation.setCustSiteZip(customerSite.getCustSiteZip());
        serviceOrderPresentation.setCountry(customerSite.getCountry());
        serviceOrderPresentation.setCountryName(customerSite.getCountry().getCountryName());
        serviceOrderPresentation.setCountryId(customerSite.getCountry().getCountryId());
        serviceOrderPresentation.setStateName(customerSite.getStateProvince().getStateName());
        serviceOrderPresentation.setStateId(customerSite.getStateProvince().getStateId());

        return serviceOrderPresentation;
    }


    public void saveServiceOrderFromCurrentCustomerForm(ServiceOrderPresentation serviceOrderPresentation)
    {
        ServiceOrder serviceOrder = new ServiceOrder();

        System.out.println(serviceOrderPresentation.getCustSiteId());
        CustomerSite customerSite = customerSiteService.findCustomerSiteByCustSiteId(serviceOrderPresentation.getCustSiteId());
        System.out.println(customerSite.getCustSiteId());
        System.out.println(serviceOrderPresentation.getContactId());
        Contact contact = contactService.findByContactId(serviceOrderPresentation.getContactId());

        // set customer site to existing customer for service order
        serviceOrder.setCustomerSite(customerSite);

        // if the serviceOrderPresentation does not have a contactId -- the user did not select a contact, then do this
        if(contact == null)
        {
            Contact contact1 = new Contact();
            contact1.setContactPhone(serviceOrderPresentation.getContactPhone());
            contact1.setContactLname(serviceOrderPresentation.getContactLname());
            contact1.setContactFname(serviceOrderPresentation.getContactFname());
            contact1.setContactEmail(serviceOrderPresentation.getContactEmail());
            contact1.setCustomerSite(customerSite);
            ContactType contactType = contactTypeRepository.findByContactTypeId(1);
            contact1.setContactType(contactType);
            ContactStatus contactStatus = contactStatusRepository.findByContactStatusId(1);
            contact1.setContactStatus(contactStatus);
            contactService.saveContact(contact1);
            serviceOrder.setContact(contact1);
        }
        // if the user did select a contact, then set the service order as the contact selected
        else
        {
            serviceOrder.setContact(contact);
        }


        // get current date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        // Add Service Order Status
        ServiceOrderStatus serviceOrderStatus = serviceOrderStatusRepository.findServiceOrderStatusBySvoStatusId(1);
        serviceOrder.setServiceOrderStatus(serviceOrderStatus);

        // Add Service order Information
        serviceOrder.setDateRequested(currentSqlDate);

        //serviceOrder.setDateScheduled(serviceOrderPresentation.getDateScheduled());
        // temp place holder

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        serviceOrder.setDateScheduled(timestamp);
       /* Timestamp timestamp = Timestamp.valueOf(serviceOrderPresentation.getDateScheduledString());
        serviceOrderPresentation.setDateScheduled(timestamp);
        serviceOrder.setDateScheduled(timestamp);*/
        serviceOrder.setWorkRequest(serviceOrderPresentation.getWorkRequest());

        // This must be before the service order lines because service order lines need the Service Order Id, and Svo_id is assigned once it is saved
        serviceOrderRepository.save(serviceOrder);

        serviceOrderPresentation.setSvoId(serviceOrder.getSvoId());
        System.out.println(serviceOrderPresentation.getSvoId());


        /*
        Set<ServiceOrderLine> serviceOrderLines = new HashSet<>();
        // Add Service Order Lines
        for (Svc svc: serviceOrderPresentation.getSvcs()
        ) {
            ServiceOrderLine serviceOrderLine = new ServiceOrderLine();
            serviceOrderLine.setServiceOrder(serviceOrder);
            serviceOrderLine.setSvc(svc);
            serviceOrderLines.add(serviceOrderLine);
            serviceOrderLineRepository.save(serviceOrderLine);
        }


        serviceOrderPresentation.setServiceOrderLines(serviceOrderLines);
        for(ServiceOrderLine serviceOrderLine: serviceOrderPresentation.getServiceOrderLines())
        {
            System.out.println(serviceOrderLine.getSvc());
            System.out.println(serviceOrderLine.getSvoLineId());
        }

        // Add Assignments ?????????????????

        for (ServiceOrderLine serviceOrderLine:serviceOrderPresentation.getServiceOrderLines()
        ) {
            for(Contractor contractor:serviceOrderPresentation.getContractorList()) {
                Assignment assignment = new Assignment();
                assignment.setServiceOrderLine(serviceOrderLine);
                assignment.setAsgmtDate(currentSqlDate);
                assignment.setContractor(contractor);
                assignment.setAssignmentStatus(assignmentStatusRepository.findById(1));
                assignmentRepository.save(assignment);
            }
        }
        */

    }

    public void addContractorAssignments(ServiceOrderPresentation serviceOrderPresentation)
    {

        System.out.println(serviceOrderPresentation.getSvoId());
        System.out.println(serviceOrderPresentation.getSvcId());

        for(Contractor contractor: serviceOrderPresentation.getContractorList())
        {
            System.out.println(contractor.getContractorId());
            System.out.println(contractor.getContractorFname());
        }

        ServiceOrder serviceOrder = serviceOrderRepository.findServiceOrderBySvoId(serviceOrderPresentation.getSvoId());
        Svc svc = svcService.findBySvcId(serviceOrderPresentation.getSvcId());

        // Set Service Order Line
        ServiceOrderLine serviceOrderLine = new ServiceOrderLine();
        serviceOrderLine.setSvc(svc);
        serviceOrderLine.setServiceOrder(serviceOrder);
        serviceOrderLineRepository.save(serviceOrderLine);

        // get current date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

            for(Contractor contractor:serviceOrderPresentation.getContractorList()) {
                Assignment assignment = new Assignment();
                assignment.setServiceOrderLine(serviceOrderLine);
                assignment.setAsgmtDate(currentSqlDate);
                assignment.setContractor(contractor);
                assignment.setAssignmentStatus(assignmentStatusRepository.findById(1));
                assignmentRepository.save(assignment);
            }

    }




    // OLD METHODS FOR OLD VIEWS

    public void addAssignments(ServiceOrderPresentation serviceOrderPresentation)
    {
        // get current date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        // Set Service Order Line
        ServiceOrderLine serviceOrderLine = serviceOrderLineRepository.findById(serviceOrderPresentation.getSvoLineId());

        for(Contractor contractor:serviceOrderPresentation.getContractorList()) {
            Assignment assignment = new Assignment();
            assignment.setServiceOrderLine(serviceOrderLine);
            assignment.setAsgmtDate(currentSqlDate);
            assignment.setContractor(contractor);
            assignment.setAssignmentStatus(assignmentStatusRepository.findById(1));
            assignmentRepository.save(assignment);
        }
    }

    public void saveServiceOrderFromCurrentCustomerFormOld(ServiceOrderPresentation serviceOrderPresentation)
    {
        ServiceOrder serviceOrder = new ServiceOrder();

        System.out.println(serviceOrderPresentation.getCustSiteId());
        CustomerSite customerSite = customerSiteService.findCustomerSiteByCustSiteId(serviceOrderPresentation.getCustSiteId());
        System.out.println(customerSite.getCustSiteId());
        System.out.println(serviceOrderPresentation.getContactId());
        Contact contact = contactService.findByContactId(serviceOrderPresentation.getContactId());

        // set customer site to existing customer for service order
        serviceOrder.setCustomerSite(customerSite);

        // if the serviceOrderPresentation does not have a contactId -- the user did not select a contact, then do this
        if(contact == null)
        {
            Contact contact1 = new Contact();
            contact1.setContactPhone(serviceOrderPresentation.getContactPhone());
            contact1.setContactLname(serviceOrderPresentation.getContactLname());
            contact1.setContactFname(serviceOrderPresentation.getContactFname());
            contact1.setContactEmail(serviceOrderPresentation.getContactEmail());
            contact1.setCustomerSite(customerSite);
            ContactType contactType = contactTypeRepository.findByContactTypeId(1);
            contact1.setContactType(contactType);
            ContactStatus contactStatus = contactStatusRepository.findByContactStatusId(1);
            contact1.setContactStatus(contactStatus);
            contactService.saveContact(contact1);
            serviceOrder.setContact(contact1);
        }
        // if the user did select a contact, then set the service order as the contact selected
        else
        {
            serviceOrder.setContact(contact);
        }


        // get current date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        // Add Service Order Status
        ServiceOrderStatus serviceOrderStatus = serviceOrderStatusRepository.findServiceOrderStatusBySvoStatusId(4);
        serviceOrder.setServiceOrderStatus(serviceOrderStatus);

        // Add Service order Information
        serviceOrder.setDateRequested(currentSqlDate);


        /*Datetime picker returns 2019-04-19T01:59 -- Have to replace T with a space and append seconds (:00) to make it correct format
         for Timestamp (java.sql.timestamp) */
        String formattedTime = serviceOrderPresentation.getDateScheduledString().replace("T"," ");
        formattedTime+=":00";
        Timestamp ts = Timestamp.valueOf(formattedTime);

        // Set Date Scheduled
        serviceOrderPresentation.setDateScheduled(ts);
        serviceOrder.setDateScheduled(ts);

        serviceOrder.setWorkRequest(serviceOrderPresentation.getWorkRequest());

        //This must be before the service order lines because service order lines need the Service Order Id, and Svo_id is assigned once it is saved
        serviceOrderRepository.save(serviceOrder);

        serviceOrderPresentation.setSvoId(serviceOrder.getSvoId());

        Set<ServiceOrderLine> serviceOrderLines = new HashSet<>();
        // Add Service Order Lines
        for (Svc svc: serviceOrderPresentation.getSvcs()
        ) {
            ServiceOrderLine serviceOrderLine = new ServiceOrderLine();
            serviceOrderLine.setServiceOrder(serviceOrder);
            serviceOrderLine.setSvc(svc);
            serviceOrderLines.add(serviceOrderLine);
            serviceOrderLineRepository.save(serviceOrderLine);
        }


        serviceOrderPresentation.setServiceOrderLines(serviceOrderLines);
        for(ServiceOrderLine serviceOrderLine: serviceOrderPresentation.getServiceOrderLines())
        {
            System.out.println(serviceOrderLine.getSvc());
            System.out.println(serviceOrderLine.getSvoLineId());
        }

    }

}
