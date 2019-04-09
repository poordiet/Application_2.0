package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContractorStatus {
    private int contractorStatusId;
    private String contractorStatus;
    private String contractorStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contractor_status_id", nullable = false)
    public int getContractorStatusId() {
        return contractorStatusId;
    }

    public void setContractorStatusId(int contractorStatusId) {
        this.contractorStatusId = contractorStatusId;
    }

    @Basic
    @Column(name = "contractor_status", nullable = false, length = 50)
    public String getContractorStatus() {
        return contractorStatus;
    }

    public void setContractorStatus(String contractorStatus) {
        this.contractorStatus = contractorStatus;
    }

    @Basic
    @Column(name = "contractor_status_desc", nullable = false, length = 250)
    public String getContractorStatusDesc() {
        return contractorStatusDesc;
    }

    public void setContractorStatusDesc(String contractorStatusDesc) {
        this.contractorStatusDesc = contractorStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractorStatus that = (ContractorStatus) o;
        return contractorStatusId == that.contractorStatusId &&
                Objects.equals(contractorStatus, that.contractorStatus) &&
                Objects.equals(contractorStatusDesc, that.contractorStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorStatusId, contractorStatus, contractorStatusDesc);
    }
}
