package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PaymentStatus {
    private int pmtStatusId;
    private String pmtStatus;
    private String pmtStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "pmt_status_id", nullable = false)
    public int getPmtStatusId() {
        return pmtStatusId;
    }

    public void setPmtStatusId(int pmtStatusId) {
        this.pmtStatusId = pmtStatusId;
    }

    @Basic
    @Column(name = "pmt_status", nullable = false, length = 150)
    public String getPmtStatus() {
        return pmtStatus;
    }

    public void setPmtStatus(String pmtStatus) {
        this.pmtStatus = pmtStatus;
    }

    @Basic
    @Column(name = "pmt_status_desc", nullable = false, length = 250)
    public String getPmtStatusDesc() {
        return pmtStatusDesc;
    }

    public void setPmtStatusDesc(String pmtStatusDesc) {
        this.pmtStatusDesc = pmtStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentStatus that = (PaymentStatus) o;
        return pmtStatusId == that.pmtStatusId &&
                Objects.equals(pmtStatus, that.pmtStatus) &&
                Objects.equals(pmtStatusDesc, that.pmtStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pmtStatusId, pmtStatus, pmtStatusDesc);
    }
}
