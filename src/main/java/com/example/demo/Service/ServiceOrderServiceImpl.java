package com.example.demo.Service;

import com.example.demo.Models.*;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.nimbus.State;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

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

        // Add Service Order Lines
        for (Svc svc: serviceOrderPresentation.getSvcs()
             ) {
            ServiceOrderLine serviceOrderLine = new ServiceOrderLine();
            serviceOrderLine.setServiceOrder(serviceOrder);
            serviceOrderLine.setSvc(svc);
            serviceOrderLineRepository.save(serviceOrderLine);
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


    public ServiceOrderPresentation addCustomerSite(CustomerSite customerSite){

        ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();

        serviceOrderPresentation.setCustomerSite(customerSite);
        serviceOrderPresentation.setCustSiteEmail(customerSite.getCustSiteEmail());
        serviceOrderPresentation.setCustSitePhone(customerSite.getCustSitePhone());
        serviceOrderPresentation.setCustSiteNumber(customerSite.getCustSiteNumber());
        serviceOrderPresentation.setCustSiteName(customerSite.getCustSiteName());
        serviceOrderPresentation.setCustSiteAddress(customerSite.getCustSiteAddress());
        serviceOrderPresentation.setCustSiteCity(customerSite.getCustSiteCity());
        serviceOrderPresentation.setCustSiteZip(customerSite.getCustSiteZip());
        serviceOrderPresentation.setCountryName(customerSite.getCountry().getCountryName());
        serviceOrderPresentation.setStateName(customerSite.getStateProvince().getStateName());

        return serviceOrderPresentation;
    }




}
