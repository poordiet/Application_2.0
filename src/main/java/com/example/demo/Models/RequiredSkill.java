package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RequiredSkill {
    private int reqSkillId;

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
