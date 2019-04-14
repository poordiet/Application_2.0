package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class HwSeries {
    private int hwSeriesId;
    private String hwSeriesName;

    // 1:M with Hardware Model
    private Set<HwModel> hwModels;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hwSeries")
    public Set<HwModel> getHwModels() {
        return hwModels;
    }

    public void setHwModels(Set<HwModel> hwModels) {
        hwModels.forEach(hwModel -> hwModel.setHwSeries(this));
        this.hwModels = hwModels;
    }

    // M:1 with Hw Type
    private HwType hwType;

    @ManyToOne
    @JoinColumn(name="hw_type_id")
    public HwType getHwType() {
        return hwType;
    }

    public void setHwType(HwType hwType) {
        this.hwType = hwType;
    }


    // M:1 with Hw Manu
    private HwManufacturer hwManufacturer;

    @ManyToOne
    @JoinColumn(name="hw_manu_id")
    public HwManufacturer getHwManufacturer() {
        return hwManufacturer;
    }

    public void setHwManufacturer(HwManufacturer hwManufacturer) {
        this.hwManufacturer = hwManufacturer;
    }


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_series_id", nullable = false)
    public int getHwSeriesId() {
        return hwSeriesId;
    }

    public void setHwSeriesId(int hwSeriesId) {
        this.hwSeriesId = hwSeriesId;
    }

    @Basic
    @Column(name = "hw_series_name", nullable = false, length = 100)
    public String getHwSeriesName() {
        return hwSeriesName;
    }

    public void setHwSeriesName(String hwSeriesName) {
        this.hwSeriesName = hwSeriesName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwSeries hwSeries = (HwSeries) o;
        return hwSeriesId == hwSeries.hwSeriesId &&
                Objects.equals(hwSeriesName, hwSeries.hwSeriesName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwSeriesId, hwSeriesName);
    }
}
