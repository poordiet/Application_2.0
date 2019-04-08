package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwSeries {
    private int hwSeriesId;
    private String hwSeriesName;

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
