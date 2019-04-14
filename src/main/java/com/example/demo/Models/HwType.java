package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class HwType {
    private int hwTypeId;
    private String hwType;
    private String hwTypeDesc;

    // 1:M with Hardware Series
    private Set<HwSeries> hwSeriesSet;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hwType")
    public Set<HwSeries> getHwSeriesSet() {
        return hwSeriesSet;
    }

    public void setHwSeriesSet(Set<HwSeries> hwSeriesSet) {
        hwSeriesSet.forEach(hwSeries -> hwSeries.setHwType(this));
        this.hwSeriesSet = hwSeriesSet;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_type_id", nullable = false)
    public int getHwTypeId() {
        return hwTypeId;
    }

    public void setHwTypeId(int hwTypeId) {
        this.hwTypeId = hwTypeId;
    }

    @Basic
    @Column(name = "hw_type", nullable = false, length = 50)
    public String getHwType() {
        return hwType;
    }

    public void setHwType(String hwType) {
        this.hwType = hwType;
    }

    @Basic
    @Column(name = "hw_type_desc", nullable = false, length = 250)
    public String getHwTypeDesc() {
        return hwTypeDesc;
    }

    public void setHwTypeDesc(String hwTypeDesc) {
        this.hwTypeDesc = hwTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwType hwType1 = (HwType) o;
        return hwTypeId == hwType1.hwTypeId &&
                Objects.equals(hwType, hwType1.hwType) &&
                Objects.equals(hwTypeDesc, hwType1.hwTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwTypeId, hwType, hwTypeDesc);
    }
}
