package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CustomerSiteHw {
    private int custSiteHwId;
    private String custSiteSerialNumber;
    private String custSiteMacAddress;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "cust_site_hw_id", nullable = false)
    public int getCustSiteHwId() {
        return custSiteHwId;
    }

    public void setCustSiteHwId(int custSiteHwId) {
        this.custSiteHwId = custSiteHwId;
    }

    @Basic
    @Column(name = "cust_site_serial_number", nullable = false, length = 24)
    public String getCustSiteSerialNumber() {
        return custSiteSerialNumber;
    }

    public void setCustSiteSerialNumber(String custSiteSerialNumber) {
        this.custSiteSerialNumber = custSiteSerialNumber;
    }

    @Basic
    @Column(name = "cust_site_mac_address", nullable = true, length = 24)
    public String getCustSiteMacAddress() {
        return custSiteMacAddress;
    }

    public void setCustSiteMacAddress(String custSiteMacAddress) {
        this.custSiteMacAddress = custSiteMacAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerSiteHw that = (CustomerSiteHw) o;
        return custSiteHwId == that.custSiteHwId &&
                Objects.equals(custSiteSerialNumber, that.custSiteSerialNumber) &&
                Objects.equals(custSiteMacAddress, that.custSiteMacAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(custSiteHwId, custSiteSerialNumber, custSiteMacAddress);
    }
}
