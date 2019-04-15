package com.example.demo.Service;

import com.example.demo.Models.*;
import com.example.demo.Presentation.HwPresentation;
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

    @Autowired
    CustomerSiteHwRepository customerSiteHwRepository;

    @Autowired
    HwSvoLineRepository hwSvoLineRepository;

    @Override
    public void addServiceOrder(ServiceOrder serviceOrder) { serviceOrderRepository.save(serviceOrder);}

    @Override
    public List<ServiceOrder> findAll() {
        return serviceOrderRepository.findAllByOrOrderBySvoIdDesc();
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
    public List<ServiceOrder> findAllByOrOrderBySvoIdDesc()
    {
        return serviceOrderRepository.findAllByOrOrderBySvoIdDesc();
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

    // saves hardware worked on for service order -- HW Svo Lines
    public void saveHwWorkedOn(List<HwPresentation> hwPresentations, ServiceOrderLinePresentation serviceOrderLinePresentation){


        for(HwPresentation hwPresentation: hwPresentations) {

            CustomerSiteHw customerSiteHw = new CustomerSiteHw();
            customerSiteHw = customerSiteHwRepository.findById(hwPresentation.getCustSiteHwId());
            HwSvoLine hwSvoLine = new HwSvoLine();
            hwSvoLine.setCustomerSiteHw(customerSiteHw);
            hwSvoLine.setServiceOrderLine(serviceOrderLineRepository.findById(serviceOrderLinePresentation.getSvoLineId()));
            System.out.println(hwSvoLine.getCustomerSiteHw().getCustSiteHwId());
            hwSvoLineRepository.save(hwSvoLine);

        }

    }

    // Returns list of hardware at a customer site, saves it as a list of HwPresentation objects for presenting in the template
    public List<HwPresentation> getCustSiteHwList(int svoLineId){
        ServiceOrderLine serviceOrderLine = serviceOrderLineRepository.findById(svoLineId);
        CustomerSite customerSite = customerSiteService.findCustomerSiteByCustSiteId(serviceOrderLine.getServiceOrder().getCustomerSite().getCustSiteId());
        List<CustomerSiteHw> customerSiteHws = customerSiteHwRepository.findCustomerSiteHwsByCustomerSite(customerSite);
        List<HwPresentation> hwPresentations = new ArrayList<>();

        // Transfer Customer Site Hw to a HwPresentation object
        for(CustomerSiteHw customerSiteHw: customerSiteHws)
        {
            HwPresentation hwPresentation = new HwPresentation();
            hwPresentation.setCustSiteHwId(customerSiteHw.getCustSiteHwId());
            hwPresentation.setCustSiteSerialNumber(customerSiteHw.getCustSiteSerialNumber());
            hwPresentation.setHwManuName(customerSiteHw.getHwModel().getHwSeries().getHwManufacturer().getHwManuName());
            hwPresentation.setHwSeriesName(customerSiteHw.getHwModel().getHwSeries().getHwSeriesName());
            hwPresentation.setHwModel(customerSiteHw.getHwModel().getHwModel());
            hwPresentation.setHwType(customerSiteHw.getHwModel().getHwSeries().getHwType().getHwType());
            hwPresentation.setSvcName(serviceOrderLine.getSvc().getSvcName());

            hwPresentations.add(hwPresentation);
        }

            return hwPresentations;

    }

    // Returns all the Hw worked on and sold for a specific service order
    public List<HwPresentation> getHwWorkedOn(int svoId){

        System.out.println(svoId);
        ServiceOrder serviceOrder = findServiceOrderBySvoId(svoId);
        CustomerSite customerSite = customerSiteService.findCustomerSiteByCustSiteId(serviceOrder.getCustomerSite().getCustSiteId());
        List<HwSvoLine> hwSvoLines = hwSvoLineRepository.findByServiceOrderId(svoId);
        List<HwPresentation> hwPresentations = new ArrayList<>();


        for(HwSvoLine hwSvoLine: hwSvoLines)
        {

            System.out.println(hwSvoLine.getCustomerSiteHw().getCustSiteHwId());
            System.out.println(hwSvoLine.getServiceOrderLine().getSvoLineId());
            System.out.println(hwSvoLine.getServiceOrderLine().getServiceOrder().getSvoId());

            HwPresentation hwPresentation = new HwPresentation();
            CustomerSiteHw customerSiteHw = new CustomerSiteHw();
            customerSiteHw = hwSvoLine.getCustomerSiteHw();

            hwPresentation.setCustSiteHwId(customerSiteHw.getCustSiteHwId());
            hwPresentation.setHwSeriesName(customerSiteHw.getHwModel().getHwSeries().getHwSeriesName());
            hwPresentation.setHwModel(customerSiteHw.getHwModel().getHwModel());
            hwPresentation.setCustSiteSerialNumber(customerSiteHw.getCustSiteSerialNumber());
            hwPresentation.setCustSiteMacAddress(customerSiteHw.getCustSiteMacAddress());
            hwPresentation.setHwType(customerSiteHw.getHwModel().getHwSeries().getHwType().getHwType());
            hwPresentation.setHwManuName(customerSiteHw.getHwModel().getHwSeries().getHwManufacturer().getHwManuName());
            hwPresentation.setSvcName(hwSvoLine.getServiceOrderLine().getSvc().getSvcName());

            hwPresentations.add(hwPresentation);

        }

        return hwPresentations;


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
                serviceOrder.getCustomerSite().getStateProvince().getStateName() + ", " + serviceOrder.getCustomerSite().getCustSiteZip();
        serviceOrderPresentation.setCountryName(serviceOrder.getCustomerSite().getCountry().getCountryName());


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
        serviceOrderPresentation.setSvoStatus(serviceOrder.getServiceOrderStatus().getSvoStatus());

        serviceOrderPresentation.setServiceOrderLines(serviceOrder.getServiceOrderLines());

       Set<ServiceOrderLine> serviceOrderLines =  serviceOrder.getServiceOrderLines();

       // Add services to the presentation object based on the services assigned to the service order lines
       List<Svc> svcs = new ArrayList<>();
       for(ServiceOrderLine serviceOrderLine: serviceOrderLines)
       {
            svcs.add(serviceOrderLine.getSvc());
       }
        serviceOrderPresentation.setSvcs(svcs);

       serviceOrderPresentation.setIncidents(serviceOrder.getIncidents());

       serviceOrderPresentation.setPayments(serviceOrder.getPayments());

       serviceOrderPresentation.setTotal(serviceOrder.getTotal());


        return serviceOrderPresentation;
    }

    // for displaying service order line and services in the same table
    public List<ServiceOrderLinePresentation> getServiceOrderLinePresentation(Set<ServiceOrderLine> serviceOrderLines){

        List<ServiceOrderLinePresentation> serviceOrderLinePresentations = new ArrayList<>();



            for(ServiceOrderLine serviceOrderLine:serviceOrderLines)
            {
                ServiceOrderLinePresentation serviceOrderLinePresentation = new ServiceOrderLinePresentation();

                serviceOrderLinePresentation.setSvcName(serviceOrderLine.getSvc().getSvcName());
                serviceOrderLinePresentation.setSvcId(serviceOrderLine.getSvc().getSvcId());
                serviceOrderLinePresentation.setSvoLineId(serviceOrderLine.getSvoLineId());

            serviceOrderLinePresentations.add(serviceOrderLinePresentation);

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
