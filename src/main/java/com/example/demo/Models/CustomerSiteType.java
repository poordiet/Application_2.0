package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class CustomerSiteType {
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
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSiteType that = (CustomerSiteType) o;
        return custSiteTypeId == that.custSiteTypeId &&
                Objects.equals(custSiteType, that.custSiteType) &&
                Objects.equals(custSiteTypeDesc, that.custSiteTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custSiteTypeId, custSiteType, custSiteTypeDesc);
    }
}
