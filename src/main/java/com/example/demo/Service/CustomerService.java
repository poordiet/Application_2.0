package com.example.demo.Service;

import com.example.demo.Models.*;
import com.example.demo.Presentation.CustomerPresentation;
import com.example.demo.Presentation.ServiceOrderPresentation;
import com.example.demo.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class CustomerService {

    @Autowired
    CustomerSiteRepository customerSiteRepository;



    @Autowired
    CustomerSiteStatusRepository customerSiteStatusRepository;

    @Autowired
    CustomerSiteTypeRepository customerSiteTypeRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CountryService countryService;

    @Autowired
    StateProvinceRepository stateProvinceRepository;

    @Autowired
    StateProvinceService stateProvinceService;



    @Autowired
    ContactRepository contactRepository;

    @Autowired
    ContactStatusRepository contactStatusRepository;

    @Autowired
    ContactTypeRepository contactTypeRepository;

    @Autowired
    ContactService contactService;


    public List<CustomerSite> findAll() {
        return customerSiteRepository.findAllByOrderByCustSiteId();
    }


  /*  public CustomerPresentation getAddCustomerPresentation(CustomerSite customerSite){


        CustomerSite customerSite1 = new CustomerSite();
        customerSite1.setCustSiteName(customerPresentation.getCustSiteName());
        customerSite1.setCustSiteNumber(customerPresentation.getCustSiteNumber());
        customerSite1.setCustSiteEmail(customerPresentation.getCustSiteEmail());
        customerSite1.setCustSitePhone(customerPresentation.getCustSitePhone());
        customerSite1.setCustSiteAddress(customerPresentation.getCustSiteAddress());
        customerSite1.setCustSiteCity(customerPresentation.getCustSiteCity());
        customerSite1.setCustSiteZip(customerPresentation.getCustSiteZip());
        customerSite1.setStateProvince(customerPresentation.getStateProvince());
        customerSite1.setCountry(customerPresentation.getCountry());







            customerPresentations.add(customerPresentation);


        return customerPresentations;
    }*/

    public List<CustomerPresentation> getCustomerPresentation(List<CustomerSite> customerSites){

        List<CustomerPresentation> customerPresentations = new ArrayList<>();
        StateProvince stateProvince;


        for (CustomerSite customerSite1:customerSites) {



            CustomerPresentation customerPresentation = new CustomerPresentation();
           /* String svcName = new String();
            String contactName;
            String contractors = new String();
            String location;*/

            customerPresentation.setCustSiteId(customerSite1.getCustSiteId());
            customerPresentation.setCustSiteName(customerSite1.getCustSiteName());
            customerPresentation.setCustSiteNumber(customerSite1.getCustSiteNumber());
            customerPresentation.setCustSitePhone(customerSite1.getCustSitePhone());
            customerPresentation.setCustSiteEmail(customerSite1.getCustSiteEmail());
            customerPresentation.setCustSiteCity(customerSite1.getCustSiteCity());
            customerPresentation.setStateName(customerSite1.getStateProvince().getStateName());
            customerPresentation.setCustSiteStatus(customerSite1.getCustomerSiteStatus().getCustSiteStatus());
            customerPresentation.setCustSiteStart(customerSite1.getCustSiteStart());







            customerPresentations.add(customerPresentation);

        }
        return customerPresentations;
    }


    public void saveCustomerSiteFromForm(CustomerPresentation customerPresentation){

        CustomerSite customerSite1 = new CustomerSite();
        customerSite1.setCustSiteName(customerPresentation.getCustSiteName());
        customerSite1.setCustSiteNumber(customerPresentation.getCustSiteNumber());
        customerSite1.setCustSiteEmail(customerPresentation.getCustSiteEmail());
        customerSite1.setCustSitePhone(customerPresentation.getCustSitePhone());
        customerSite1.setCustSiteAddress(customerPresentation.getCustSiteAddress());
        customerSite1.setCustSiteCity(customerPresentation.getCustSiteCity());
        customerSite1.setCustSiteZip(customerPresentation.getCustSiteZip());

        //state
        StateProvince stateProvince = stateProvinceRepository.findByStateId(customerPresentation.getStateId());
        customerSite1.setStateProvince(stateProvince);

        //country
        Country country = countryRepository.findByCountryId(customerPresentation.getCountryId());
        customerSite1.setCountry(country);

        //type
        CustomerSiteType customerSiteType = customerSiteTypeRepository.findByCustSiteTypeId(customerPresentation.getCustSiteTypeId());
        customerSite1.setCustomerSiteType(customerSiteType);

        //status
        CustomerSiteStatus customerSiteStatus = customerSiteStatusRepository.findByCustSiteStatusId(1);
        customerSite1.setCustomerSiteStatus(customerSiteStatus);

//        //contact
//        Contact contact1 = new Contact();
//        contact1.setContactFname(customerPresentation.getContactFname());
//        contact1.setContactLname(customerPresentation.getContactLname());
//        contact1.setContactPhone(customerPresentation.getContactPhone());
//        contact1.setContactEmail(customerPresentation.getContactEmail());
//
//        //contact status
//        ContactStatus contactStatus = contactStatusRepository.findByContactStatusId(1);
//        contact1.setContactStatus(contactStatus);
//
//        //contact type
//        ContactType contactType = contactTypeRepository.findByContactTypeId(1);
//        contact1.setContactType(contactType);

        //date
        java.sql.Date currentSqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        customerSite1.setCustSiteStart(currentSqlDate);

        //customerSite1.setContacts(contact1);
        customerSiteRepository.save(customerSite1);







    }


    public ServiceOrderPresentation getServiceOrderPresentationById(int customerSiteId) {


            CustomerSite customerSite = customerSiteRepository.findCustomerSiteByCustSiteId(customerSiteId);



            ServiceOrderPresentation serviceOrderPresentation = new ServiceOrderPresentation();
            serviceOrderPresentation.setCustSiteId(customerSite.getCustSiteId());
            serviceOrderPresentation.setCustSiteStart(customerSite.getCustSiteStart());
            serviceOrderPresentation.setCustSiteName(customerSite.getCustSiteName());
            serviceOrderPresentation.setCustSiteNumber(customerSite.getCustSiteNumber());
            serviceOrderPresentation.setCustSiteAddress(customerSite.getCustSiteAddress());
            serviceOrderPresentation.setCustSiteCity(customerSite.getCustSiteCity());
            serviceOrderPresentation.setCountryName(customerSite.getCountry().getCountryName());
            serviceOrderPresentation.setStateName(customerSite.getStateProvince().getStateName());
            serviceOrderPresentation.setCustSitePhone(customerSite.getCustSitePhone());

            //contacts hopefully
            Set<Contact> contacts = customerSite.getContacts();




            for(Contact contact1:contacts)
            {

                serviceOrderPresentation.setContactFname(contact1.getContactFname());
                serviceOrderPresentation.setContactLname(contact1.getContactLname());
                serviceOrderPresentation.setContactPhone(contact1.getContactPhone());


            }


            return serviceOrderPresentation;
        }

    }

