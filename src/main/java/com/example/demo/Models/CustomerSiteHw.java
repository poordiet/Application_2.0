package com.example.demo.Models;

import net.bytebuddy.implementation.ToStringMethod;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class CustomerSiteHw {
    private int custSiteHwId;
    private String custSiteSerialNumber;
    private String custSiteMacAddress;

//   @Override
//   public String toString(int custSiteHwId){
//        CustomerSiteHw customerSiteHw = new CustomerSiteHw();
//
//       return
//   }

    // 1:M with Hw_Svo_Line
    private Set<HwSvoLine> hwSvoLines;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerSiteHw")
    public Set<HwSvoLine> getHwSvoLines() {
        return hwSvoLines;
    }

    public void setHwSvoLines(Set<HwSvoLine> hwSvoLines) {
        this.hwSvoLines = hwSvoLines;
    }

    // M:1 with Hw Model
    private HwModel hwModel;

    @ManyToOne
    @JoinColumn(name="hw_model_id")
    public HwModel getHwModel() {
        return hwModel;
    }

    public void setHwModel(HwModel hwModel) {
        this.hwModel = hwModel;
    }


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
