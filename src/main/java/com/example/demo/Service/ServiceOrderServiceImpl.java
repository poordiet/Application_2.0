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
        System.out.println(serviceOrderPresentation.getCustSiteAddress());

        customerSite.setCustSiteCity(serviceOrderPresentation.getCustSiteCity());
        System.out.println(serviceOrderPresentation.getCustSiteCity());

        customerSite.setCustSiteEmail(serviceOrderPresentation.getCustSiteEmail());
        customerSite.setCustSiteName(serviceOrderPresentation.getCustSiteName());
        customerSite.setCustSiteNumber(serviceOrderPresentation.getCustSiteNumber());
        customerSite.setCustSiteStart(serviceOrderPresentation.getCustSiteStart());
        customerSite.setCustSiteZip(serviceOrderPresentation.getCustSiteZip());
        customerSite.setCustSitePhone(serviceOrderPresentation.getCustSitePhone());
        System.out.println(serviceOrderPresentation.getCustSitePhone());

        // get current date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        customerSite.setCustSiteStart(currentSqlDate);

        CustomerSiteStatus customerSiteStatus = customerSiteStatusRepository.findByCustSiteStatusId(1);
        customerSite.setCustomerSiteStatus(customerSiteStatus);

        CustomerSiteType customerSiteType = customerSiteTypeRepository.findByCustSiteTypeId(1);
        customerSite.setCustomerSiteType(customerSiteType);

        customerSite.setStateProvince(stateProvinceService.findByStateId(serviceOrderPresentation.getStateId()));
        System.out.println(serviceOrderPresentation.getStateId());
        System.out.println(stateProvinceService.findByStateId(serviceOrderPresentation.getStateId()));
        System.out.println(stateProvinceService.findByStateId(serviceOrderPresentation.getStateId()).getStateName());


        customerSite.setCountry(countryService.findByCountryId(serviceOrderPresentation.getCountryId()));
        System.out.println(serviceOrderPresentation.getCountryId());
        System.out.println(countryService.findByCountryId(serviceOrderPresentation.getCountryId()));
        System.out.println(countryService.findByCountryId(serviceOrderPresentation.getCountryId()).getCountryName());

        customerSiteService.saveCustomerSite(customerSite);
        serviceOrder.setCustomerSite(customerSite);

        // Add Contact
        contact.setCustomerSite(customerSite);
        contact.setContactEmail(serviceOrderPresentation.getContactEmail());
        System.out.println(serviceOrderPresentation.getContactEmail());


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
        System.out.println(serviceOrderStatusRepository.findServiceOrderStatusBySvoStatusId(1).getSvoStatus());

        // Add Service order Information
        serviceOrder.setDateRequested(currentSqlDate);
        System.out.println(currentSqlDate);
        //serviceOrder.setDateScheduled(serviceOrderPresentation.getDateScheduled());
        // temp place holder
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        serviceOrder.setDateScheduled(timestamp);
        serviceOrder.setWorkRequest(serviceOrderPresentation.getWorkRequest());
        System.out.println(serviceOrderPresentation.getWorkRequest());

        // Add Service Order Lines


        serviceOrderRepository.save(serviceOrder);
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
            serviceOrderPresentation.setSvoStatus(serviceOrder1.getServiceOrderStatus().getSvoStatus());

            location = serviceOrder1.getCustomerSite().getCustSiteAddress()+ " " + serviceOrder1.getCustomerSite().getCustSiteCity() + ", "+
                    serviceOrder1.getCustomerSite().getStateProvince().getStateName() + ", " + serviceOrder1.getCustomerSite().getCustSiteZip();

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



}
