package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContractorSkill {
    private int contractorSkillId;

    //M:1 with Contractor
    private Contractor contractor;

    @ManyToOne
    @JoinColumn(name="contractor_id")
    public Contractor getContractor(){return contractor;}

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    //M:1 with Skill
    private Skill skill;

    @ManyToOne
    @JoinColumn(name="skill_id")
    public Skill getSkill(){return skill;}

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

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
