package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CustomerSiteStatus {
    private int custSiteStatusId;
    private String custSiteStatus;
    private String custSiteStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSiteStatus that = (CustomerSiteStatus) o;
        return custSiteStatusId == that.custSiteStatusId &&
                Objects.equals(custSiteStatus, that.custSiteStatus) &&
                Objects.equals(custSiteStatusDesc, that.custSiteStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custSiteStatusId, custSiteStatus, custSiteStatusDesc);
    }
}
