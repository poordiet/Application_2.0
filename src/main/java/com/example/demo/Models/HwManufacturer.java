package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class HwManufacturer {
    private int hwManuId;
    private String hwManuName;
    private String hwManuPhone;


    // 1:M with Hardware Series
    private Set<HwSeries> hwSeriesSet;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hwManufacturer")
    public Set<HwSeries> getHwSeriesSet() {
        return hwSeriesSet;
    }

    public void setHwSeriesSet(Set<HwSeries> hwSeriesSet) {
        hwSeriesSet.forEach(hwSeries -> hwSeries.setHwManufacturer(this));
        this.hwSeriesSet = hwSeriesSet;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_manu_id", nullable = false)
    public int getHwManuId() {
        return hwManuId;
    }

    public void setHwManuId(int hwManuId) {
        this.hwManuId = hwManuId;
    }

    @Basic
    @Column(name = "hw_manu_name", nullable = false, length = 50)
    public String getHwManuName() {
        return hwManuName;
    }

    public void setHwManuName(String hwManuName) {
        this.hwManuName = hwManuName;
    }

    @Basic
    @Column(name = "hw_manu_phone", nullable = false, length = 15)
    public String getHwManuPhone() {
        return hwManuPhone;
    }

    public void setHwManuPhone(String hwManuPhone) {
        this.hwManuPhone = hwManuPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwManufacturer that = (HwManufacturer) o;
        return hwManuId == that.hwManuId &&
                Objects.equals(hwManuName, that.hwManuName) &&
                Objects.equals(hwManuPhone, that.hwManuPhone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwManuId, hwManuName, hwManuPhone);
    }
}
