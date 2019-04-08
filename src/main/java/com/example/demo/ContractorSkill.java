package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContractorSkill {
    private int contractorSkillId;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contractor_skill_id", nullable = false)
    public int getContractorSkillId() {
        return contractorSkillId;
    }

    public void setContractorSkillId(int contractorSkillId) {
        this.contractorSkillId = contractorSkillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContractorSkill that = (ContractorSkill) o;
        return contractorSkillId == that.contractorSkillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorSkillId);
    }
}
