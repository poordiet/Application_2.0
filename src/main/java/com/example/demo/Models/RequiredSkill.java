package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class RequiredSkill {
    private int reqSkillId;

    // M:1 with Svc
    private Svc svc;

    @ManyToOne
    @JoinColumn(name="svc_id")
    public Svc getSvc() {
        return svc;
    }

    public void setSvc(Svc svc) {
        this.svc = svc;
    }

    // M:1 with Skill
    private Skill skill;

    @ManyToOne
    @JoinColumn(name="skill_id")
    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "req_skill_id", nullable = false)
    public int getReqSkillId() {
        return reqSkillId;
    }

    public void setReqSkillId(int reqSkillId) {
        this.reqSkillId = reqSkillId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequiredSkill that = (RequiredSkill) o;
        return reqSkillId == that.reqSkillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reqSkillId);
    }
}
