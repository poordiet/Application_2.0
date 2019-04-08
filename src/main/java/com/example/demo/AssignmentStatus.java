package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AssignmentStatus {
    private int asgmtStatusId;
    private String asgmtStatus;
    private String asgmtStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "asgmt_status_id", nullable = false)
    public int getAsgmtStatusId() {
        return asgmtStatusId;
    }

    public void setAsgmtStatusId(int asgmtStatusId) {
        this.asgmtStatusId = asgmtStatusId;
    }

    @Basic
    @Column(name = "asgmt_status", nullable = false, length = 25)
    public String getAsgmtStatus() {
        return asgmtStatus;
    }

    public void setAsgmtStatus(String asgmtStatus) {
        this.asgmtStatus = asgmtStatus;
    }

    @Basic
    @Column(name = "asgmt_status_desc", nullable = false, length = 250)
    public String getAsgmtStatusDesc() {
        return asgmtStatusDesc;
    }

    public void setAsgmtStatusDesc(String asgmtStatusDesc) {
        this.asgmtStatusDesc = asgmtStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentStatus that = (AssignmentStatus) o;
        return asgmtStatusId == that.asgmtStatusId &&
                Objects.equals(asgmtStatus, that.asgmtStatus) &&
                Objects.equals(asgmtStatusDesc, that.asgmtStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asgmtStatusId, asgmtStatus, asgmtStatusDesc);
    }
}
