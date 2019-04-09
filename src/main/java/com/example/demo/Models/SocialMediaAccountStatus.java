package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SocialMediaAccountStatus {
    private int smAcctStatusId;
    private String smAcctStatus;
    private String smAcctStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "sm_acct_status_id", nullable = false)
    public int getSmAcctStatusId() {
        return smAcctStatusId;
    }

    public void setSmAcctStatusId(int smAcctStatusId) {
        this.smAcctStatusId = smAcctStatusId;
    }

    @Basic
    @Column(name = "sm_acct_status", nullable = false, length = 50)
    public String getSmAcctStatus() {
        return smAcctStatus;
    }

    public void setSmAcctStatus(String smAcctStatus) {
        this.smAcctStatus = smAcctStatus;
    }

    @Basic
    @Column(name = "sm_acct_status_desc", nullable = false, length = 250)
    public String getSmAcctStatusDesc() {
        return smAcctStatusDesc;
    }

    public void setSmAcctStatusDesc(String smAcctStatusDesc) {
        this.smAcctStatusDesc = smAcctStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialMediaAccountStatus that = (SocialMediaAccountStatus) o;
        return smAcctStatusId == that.smAcctStatusId &&
                Objects.equals(smAcctStatus, that.smAcctStatus) &&
                Objects.equals(smAcctStatusDesc, that.smAcctStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smAcctStatusId, smAcctStatus, smAcctStatusDesc);
    }
}
