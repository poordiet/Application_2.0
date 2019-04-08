package com.example.demo;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class ContractorIncident {
    private int contractorIncId;
    private Date contractorIncDate;
    private String contractorIncNotes;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contractor_inc_id", nullable = false)
    public int getContractorIncId() {
        return contractorIncId;
    }

    public void setContractorIncId(int contractorIncId) {
        this.contractorIncId = contractorIncId;
    }

    @Basic
    @Column(name = "contractor_inc_date", nullable = false)
    public Date getContractorIncDate() {
        return contractorIncDate;
    }

    public void setContractorIncDate(Date contractorIncDate) {
        this.contractorIncDate = contractorIncDate;
    }

    @Basic
    @Column(name = "contractor_inc_notes", nullable = false, length = 5000)
    public String getContractorIncNotes() {
        return contractorIncNotes;
    }

    public void setContractorIncNotes(String contractorIncNotes) {
        this.contractorIncNotes = contractorIncNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractorIncident that = (ContractorIncident) o;
        return contractorIncId == that.contractorIncId &&
                Objects.equals(contractorIncDate, that.contractorIncDate) &&
                Objects.equals(contractorIncNotes, that.contractorIncNotes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorIncId, contractorIncDate, contractorIncNotes);
    }
}
