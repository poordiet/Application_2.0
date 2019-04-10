package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Contact {
    private int contactId;
    private String contactFname;
    private String contactLname;
    private String contactPhone;
    private String contactEmail;

    // M:1 with Contact Type
    private ContactType contactType;

    @ManyToOne
    @JoinColumn(name="contact_type_id")
    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

    // M:1 with Contact Status
    private ContactStatus contactStatus;

    @ManyToOne
    @JoinColumn(name="contact_status_id")
    public ContactStatus getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(ContactStatus contactStatus) {
        this.contactStatus = contactStatus;
    }

    // M:1 with Customer_Site
    private CustomerSite customerSite;

    @ManyToOne
    @JoinColumn(name="cust_site_id")
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
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return contactId == contact.contactId &&
                Objects.equals(contactFname, contact.contactFname) &&
                Objects.equals(contactLname, contact.contactLname) &&
                Objects.equals(contactPhone, contact.contactPhone) &&
                Objects.equals(contactEmail, contact.contactEmail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, contactFname, contactLname, contactPhone, contactEmail);
    }
}
