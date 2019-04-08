package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwSeriesStatus {
    private int hwSeriesStatusId;
    private String hwSeriesStatus;
    private String hwSeriesStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_series_status_id", nullable = false)
    public int getHwSeriesStatusId() {
        return hwSeriesStatusId;
    }

    public void setHwSeriesStatusId(int hwSeriesStatusId) {
        this.hwSeriesStatusId = hwSeriesStatusId;
    }

    @Basic
    @Column(name = "hw_series_status", nullable = false, length = 50)
    public String getHwSeriesStatus() {
        return hwSeriesStatus;
    }

    public void setHwSeriesStatus(String hwSeriesStatus) {
        this.hwSeriesStatus = hwSeriesStatus;
    }

    @Basic
    @Column(name = "hw_series_status_desc", nullable = false, length = 250)
    public String getHwSeriesStatusDesc() {
        return hwSeriesStatusDesc;
    }

    public void setHwSeriesStatusDesc(String hwSeriesStatusDesc) {
        this.hwSeriesStatusDesc = hwSeriesStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwSeriesStatus that = (HwSeriesStatus) o;
        return hwSeriesStatusId == that.hwSeriesStatusId &&
                Objects.equals(hwSeriesStatus, that.hwSeriesStatus) &&
                Objects.equals(hwSeriesStatusDesc, that.hwSeriesStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwSeriesStatusId, hwSeriesStatus, hwSeriesStatusDesc);
    }
}
