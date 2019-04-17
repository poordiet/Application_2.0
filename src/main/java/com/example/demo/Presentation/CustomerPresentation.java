package com.example.demo.Presentation;

import com.example.demo.Models.*;
import com.example.demo.Repositories.*;
import com.example.demo.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

public class CustomerPresentation {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date start;

    //Customer
    private int custSiteId;
    private String custSiteNumber;
    private String custSiteName;
    private String custSiteAddress;
    private String custSiteCity;
    private String custSiteZip;
    private String custSitePhone;
    private String custSiteEmail;
    private Date custSiteStart;

    private CustomerSiteType customerSiteType;

    private Set<Contact> contact;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerSiteTypeRepository customerSiteTypeRepository;

    @Autowired
    CustomerSiteStatusRepository customerSiteStatusRepository;

    @Autowired
    ContactStatusRepository contactStatusRepository;

    @Autowired
    ContactTypeRepository contactTypeRepository;

    @Autowired
    CustomerSiteRepository customerSiteRepository;

    @Autowired
    ContactRepository contactRepository;


    @ManyToOne
    @JoinColumn(name = "cust_site_type_id")
    public CustomerSiteType getCustomerSiteType() {
        return customerSiteType;
    }

    public void setCustomerSiteType(CustomerSiteType customerSiteType) {
        this.customerSiteType = customerSiteType;
    }

    // M:1 with Customer Site Status
    private CustomerSiteStatus customerSiteStatus;

    @ManyToOne
    @JoinColumn(name = "cust_site_status_id")
    public CustomerSiteStatus getCustomerSiteStatus() {
        return customerSiteStatus;
    }

    public void setCustomerSiteStatus(CustomerSiteStatus customerSiteStatus) {
        this.customerSiteStatus = customerSiteStatus;
    }

    // M:1 with Country
    private Country country;

    @ManyToOne
    @JoinColumn(name = "country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }


    // M:1 with State
    private StateProvince stateProvince;

    @ManyToOne
    @JoinColumn(name = "state_id")
    public StateProvince getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(StateProvince stateProvince) {
        this.stateProvince = stateProvince;
    }

    // 1:M with Contact
    private Set<Contact> contacts;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerSite")
    public Set<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_site_id", nullable = false)
    public int getCustSiteId() {
        return custSiteId;
    }

    public void setCustSiteId(int custSiteId) {
        this.custSiteId = custSiteId;
    }

    @Basic
    @Column(name = "cust_site_number", nullable = true, length = 100)
    public String getCustSiteNumber() {
        return custSiteNumber;
    }

    public void setCustSiteNumber(String custSiteNumber) {
        this.custSiteNumber = custSiteNumber;
    }

    @Basic
    @Column(name = "cust_site_name", nullable = false, length = 50)
    public String getCustSiteName() {
        return custSiteName;
    }

    public void setCustSiteName(String custSiteName) {
        this.custSiteName = custSiteName;
    }

    @Basic
    @Column(name = "cust_site_address", nullable = false, length = 50)
    public String getCustSiteAddress() {
        return custSiteAddress;
    }

    public void setCustSiteAddress(String custSiteAddress) {
        this.custSiteAddress = custSiteAddress;
    }

    @Basic
    @Column(name = "cust_site_city", nullable = false, length = 50)
    public String getCustSiteCity() {
        return custSiteCity;
    }

    public void setCustSiteCity(String custSiteCity) {
        this.custSiteCity = custSiteCity;
    }

    @Basic
    @Column(name = "cust_site_zip", nullable = false, length = 15)
    public String getCustSiteZip() {
        return custSiteZip;
    }

    public void setCustSiteZip(String custSiteZip) {
        this.custSiteZip = custSiteZip;
    }

    @Basic
    @Column(name = "cust_site_phone", nullable = false, length = 20)
    public String getCustSitePhone() {
        return custSitePhone;
    }

    public void setCustSitePhone(String custSitePhone) {
        this.custSitePhone = custSitePhone;
    }

    @Basic
    @Column(name = "cust_site_email", nullable = true, length = 50)
    public String getCustSiteEmail() {
        return custSiteEmail;
    }

    public void setCustSiteEmail(String custSiteEmail) {
        this.custSiteEmail = custSiteEmail;
    }

    @Basic
    @Column(name = "cust_site_start", nullable = true)
    public Date getCustSiteStart() {
        return custSiteStart;
    }

    public void setCustSiteStart(Date custSiteStart) {
        this.custSiteStart = custSiteStart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, contactFname, contactLname, contactPhone, contactEmail);
    }


    //Contact
    private int contactId;
    private String contactFname;
    private String contactLname;
    private String contactPhone;
    private String contactEmail;

    // M:1 with Contact Type
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name = "contact_type_id")
    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    // M:1 with Contact Status
    private ContactStatus contactStatus;

    @ManyToOne
    @JoinColumn(name = "contact_status_id")
    public ContactStatus getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(ContactStatus contactStatus) {
        this.contactStatus = contactStatus;
    }

    // M:1 with Customer_Site
    private CustomerSite customerSite;

    @ManyToOne
    @JoinColumn(name = "cust_site_id")
    public CustomerSite getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSite customerSite) {
        this.customerSite = customerSite;
    }

/*
    // 1:M with Service Order
    private Set<ServiceOrder> serviceOrders;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contact")
    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        serviceOrders.forEach(serviceOrder -> serviceOrder.setContact(this));
        this.serviceOrders = serviceOrders;
    }
*/


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "contact_fname", nullable = false, length = 50)
    public String getContactFname() {
        return contactFname;
    }

    public void setContactFname(String contactFname) {
        this.contactFname = contactFname;
    }

    @Basic
    @Column(name = "contact_lname", nullable = false, length = 50)
    public String getContactLname() {
        return contactLname;
    }

    public void setContactLname(String contactLname) {
        this.contactLname = contactLname;
    }

    @Basic
    @Column(name = "contact_phone", nullable = true, length = 30)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "contact_email", nullable = true, length = 50)
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }


    //CustomerSiteStatus
    private int custSiteStatusId;
    private String custSiteStatus;
    private String custSiteStatusDesc;
/*
    // 1:M with CustomerSite
    private List<CustomerSite> customerSites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerSiteStatus")
    public List<CustomerSite> getCustomerSites() {
        return customerSites;
    }

    public void setCustomerSites(List<CustomerSite> customerSites) {
        customerSites.forEach(customerSite -> customerSite.setCustomerSiteStatus(this));
        this.customerSites = customerSites;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_site_status_id", nullable = false)
    public int getCustSiteStatusId() {
        return custSiteStatusId;
    }

    public void setCustSiteStatusId(int custSiteStatusId) {
        this.custSiteStatusId = custSiteStatusId;
    }

    @Basic
    @Column(name = "cust_site_status", nullable = false, length = 50)
    public String getCustSiteStatus() {
        return custSiteStatus;
    }

    public void setCustSiteStatus(String custSiteStatus) {
        this.custSiteStatus = custSiteStatus;
    }

    @Basic
    @Column(name = "cust_site_status_desc", nullable = false, length = 250)
    public String getCustSiteStatusDesc() {
        return custSiteStatusDesc;
    }

    public void setCustSiteStatusDesc(String custSiteStatusDesc) {
        this.custSiteStatusDesc = custSiteStatusDesc;
    }


    //CustomerSiteType
    private int custSiteTypeId;
    private String custSiteType;
    private String custSiteTypeDesc;

    /*
    // 1:M with CustomerSite
    private Set<CustomerSite> customerSites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerSiteStatus")
    public Set<CustomerSite> getCustomerSites() {
        return customerSites;
    }

    public void setCustomerSites(Set<CustomerSite> customerSites) {
        customerSites.forEach(customerSite -> customerSite.setCustomerSiteType(this));
        this.customerSites = customerSites;
    }*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_site_type_id", nullable = false)
    public int getCustSiteTypeId() {
        return custSiteTypeId;
    }

    public void setCustSiteTypeId(int custSiteTypeId) {
        this.custSiteTypeId = custSiteTypeId;
    }

    @Basic
    @Column(name = "cust_site_type", nullable = false, length = 50)
    public String getCustSiteType() {
        return custSiteType;
    }

    public void setCustSiteType(String custSiteType) {
        this.custSiteType = custSiteType;
    }

    @Basic
    @Column(name = "cust_site_type_desc", nullable = false, length = 250)
    public String getCustSiteTypeDesc() {
        return custSiteTypeDesc;
    }

    public void setCustSiteTypeDesc(String custSiteTypeDesc) {
        this.custSiteTypeDesc = custSiteTypeDesc;
    }


    //Country


        private int countryId;
        private String countryName;

    /*
    // 1:M with customer site
    private Set<CustomerSite> customerSites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    public Set<CustomerSite> getCustomerSites() {
        return customerSites;
    }

    public void setCustomerSites(Set<CustomerSite> customerSites) {
        customerSites.forEach(customerSite -> customerSite.setCountry(this));
        this.customerSites = customerSites;
    }*/

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "country_id", nullable = false)
        public int getCountryId() {
            return countryId;
        }

        public void setCountryId(int countryId) {
            this.countryId = countryId;
        }

        @Basic
        @Column(name = "country_name", nullable = false, length = 200)
        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }





    //State

        private int stateId;
        private String stateName;

    /*
    // 1:M with customer site
    private Set<CustomerSite> customerSites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stateProvince")
    public Set<CustomerSite> getCustomerSites() {
        return customerSites;
    }

    public void setCustomerSites(Set<CustomerSite> customerSites) {
        customerSites.forEach(customerSite -> customerSite.setStateProvince(this));
        this.customerSites = customerSites;
    }*/

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "state_id", nullable = false)
        public int getStateId() {
            return stateId;
        }

        public void setStateId(int stateId) {
            this.stateId = stateId;
        }

        @Basic
        @Column(name = "state_name", nullable = false, length = 200)
        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

}
