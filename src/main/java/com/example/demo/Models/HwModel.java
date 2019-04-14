package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class HwModel {
    private int hwModelId;
    private String hwModel;

//    // 1:M with Hw Inventory
//    private Set<HwInventory> hwInventories;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hwModel")
//    public Set<HwInventory> getHwInventories() {
//        return hwInventories;
//    }
//
//    public void setHwInventories(Set<HwInventory>hwInventories) {
//        hwInventories.forEach(hwInventory -> hwInventory.setHwModel(this));
//        this.hwInventories = hwInventories;
//    }


    // 1:M with Customer Site HW
    private Set<CustomerSiteHw> customerSiteHws;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hwModel")
    public Set<CustomerSiteHw> getCustomerSiteHws() {
        return customerSiteHws;
    }

    public void setCustomerSiteHws(Set<CustomerSiteHw> customerSiteHws) {
       //customerSiteHws.forEach(customerSiteHw -> customerSiteHw.setHwModel(this));
        this.customerSiteHws = customerSiteHws;
    }

    // M:1 with Hw Series
    private HwSeries hwSeries;

    @ManyToOne
    @JoinColumn(name="hw_series_id")
    public HwSeries getHwSeries() {
        return hwSeries;
    }

    public void setHwSeries(HwSeries hwSeries) {
        this.hwSeries = hwSeries;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_model_id", nullable = false)
    public int getHwModelId() {
        return hwModelId;
    }

    public void setHwModelId(int hwModelId) {
        this.hwModelId = hwModelId;
    }

    @Basic
    @Column(name = "hw_model", nullable = false, length = 100)
    public String getHwModel() {
        return hwModel;
    }

    public void setHwModel(String hwModel) {
        this.hwModel = hwModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwModel hwModel1 = (HwModel) o;
        return hwModelId == hwModel1.hwModelId &&
                Objects.equals(hwModel, hwModel1.hwModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwModelId, hwModel);
    }
}
