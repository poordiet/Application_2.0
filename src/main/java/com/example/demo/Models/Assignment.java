package com.example.demo.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Assignment {
    private int asgmtId;
    private Date asgmtDate;

    // M:1 with AssignmentStatus
    private AssignmentStatus assignmentStatus;

    @ManyToOne
    @JoinColumn(name="asgmt_status_id")
    public AssignmentStatus getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(AssignmentStatus assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }

    // M:1 with Service Order Line
    private ServiceOrderLine serviceOrderLine;

    @ManyToOne
    @JoinColumn(name="svo_line_id")
    public ServiceOrderLine getServiceOrderLine() {
        return serviceOrderLine;
    }

    public void setServiceOrderLine(ServiceOrderLine serviceOrderLine) {
        this.serviceOrderLine = serviceOrderLine;
    }

    // M:1 with Contractor
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name="contractor_id")
    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "asgmt_id", nullable = false)
    public int getAsgmtId() {
        return asgmtId;
    }

    public void setAsgmtId(int asgmtId) {
        this.asgmtId = asgmtId;
    }

    @Basic
    @Column(name = "asgmt_date", nullable = false)
    public Date getAsgmtDate() {
        return asgmtDate;
    }

    public void setAsgmtDate(Date asgmtDate) {
        this.asgmtDate = asgmtDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return asgmtId == that.asgmtId &&
                Objects.equals(asgmtDate, that.asgmtDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(asgmtId, asgmtDate);
    }
}
