package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class AssignmentStatus {
    private int asgmtStatusId;
    private String asgmtStatus;
    private String asgmtStatusDesc;

    // 1:M with Assignment
    private Set<Assignment> assignments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "assignmentStatus")
    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        assignments.forEach(assignment -> assignment.setAssignmentStatus(this));
        this.assignments = assignments;
    }

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
