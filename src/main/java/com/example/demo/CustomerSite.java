package com.example.demo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class CustomerSite {

    private int custSiteId;
    private String custSiteNumber;
    private String custSiteName;
    private String custSiteAddress;
    private String custSiteCity;
    private String custSiteZip;
    private String custSitePhone;
    private String custSiteEmail;
    private Date custSiteStart;

    private Set<ServiceOrder> serviceOrders;

/* Returns Null Pointer Exception for some reason
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerSite")
    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        serviceOrders.forEach(serviceOrder -> serviceOrder.setCustomerSite(this));
        this.serviceOrders = serviceOrders;
    }*/

    // M:1 with State
    private StateProvince stateProvince;

    @ManyToOne
    @JoinColumn(name="state_id")
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
        contacts.forEach(contact -> contact.setCustomerSite(this));
        this.contacts = contacts;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSite that = (CustomerSite) o;
        return custSiteId == that.custSiteId &&
                Objects.equals(custSiteNumber, that.custSiteNumber) &&
                Objects.equals(custSiteName, that.custSiteName) &&
                Objects.equals(custSiteAddress, that.custSiteAddress) &&
                Objects.equals(custSiteCity, that.custSiteCity) &&
                Objects.equals(custSiteZip, that.custSiteZip) &&
                Objects.equals(custSitePhone, that.custSitePhone) &&
                Objects.equals(custSiteEmail, that.custSiteEmail) &&
                Objects.equals(custSiteStart, that.custSiteStart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custSiteId, custSiteNumber, custSiteName, custSiteAddress, custSiteCity, custSiteZip, custSitePhone, custSiteEmail, custSiteStart);
    }
}
