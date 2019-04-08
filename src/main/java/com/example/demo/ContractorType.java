package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContractorType {
    private int contractorTypeId;
    private String contractorType;
    private String contractorTypeDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contractor_type_id", nullable = false)
    public int getContractorTypeId() {
        return contractorTypeId;
    }

    public void setContractorTypeId(int contractorTypeId) {
        this.contractorTypeId = contractorTypeId;
    }

    @Basic
    @Column(name = "contractor_type", nullable = false, length = 24)
    public String getContractorType() {
        return contractorType;
    }

    public void setContractorType(String contractorType) {
        this.contractorType = contractorType;
    }

    @Basic
    @Column(name = "contractor_type_desc", nullable = false, length = 250)
    public String getContractorTypeDesc() {
        return contractorTypeDesc;
    }

    public void setContractorTypeDesc(String contractorTypeDesc) {
        this.contractorTypeDesc = contractorTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractorType that = (ContractorType) o;
        return contractorTypeId == that.contractorTypeId &&
                Objects.equals(contractorType, that.contractorType) &&
                Objects.equals(contractorTypeDesc, that.contractorTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorTypeId, contractorType, contractorTypeDesc);
    }
}
