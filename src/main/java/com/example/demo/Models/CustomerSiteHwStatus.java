package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CustomerSiteHwStatus {
    private int custSiteHwStatusId;
    private String custSiteHwStatus;
    private String custSiteHwStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "cust_site_hw_status_id", nullable = false)
    public int getCustSiteHwStatusId() {
        return custSiteHwStatusId;
    }

    public void setCustSiteHwStatusId(int custSiteHwStatusId) {
        this.custSiteHwStatusId = custSiteHwStatusId;
    }

    @Basic
    @Column(name = "cust_site_hw_status", nullable = false, length = 50)
    public String getCustSiteHwStatus() {
        return custSiteHwStatus;
    }

    public void setCustSiteHwStatus(String custSiteHwStatus) {
        this.custSiteHwStatus = custSiteHwStatus;
    }

    @Basic
    @Column(name = "cust_site_hw_status_desc", nullable = false, length = 250)
    public String getCustSiteHwStatusDesc() {
        return custSiteHwStatusDesc;
    }

    public void setCustSiteHwStatusDesc(String custSiteHwStatusDesc) {
        this.custSiteHwStatusDesc = custSiteHwStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSiteHwStatus that = (CustomerSiteHwStatus) o;
        return custSiteHwStatusId == that.custSiteHwStatusId &&
                Objects.equals(custSiteHwStatus, that.custSiteHwStatus) &&
                Objects.equals(custSiteHwStatusDesc, that.custSiteHwStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custSiteHwStatusId, custSiteHwStatus, custSiteHwStatusDesc);
    }
}
