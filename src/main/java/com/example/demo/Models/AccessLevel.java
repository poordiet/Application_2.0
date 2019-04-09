package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AccessLevel {
    private int accLevelId;
    private String accLevelName;
    private String accLevelDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "acc_level_id", nullable = false)
    public int getAccLevelId() {
        return accLevelId;
    }

    public void setAccLevelId(int accLevelId) {
        this.accLevelId = accLevelId;
    }

    @Basic
    @Column(name = "acc_level_name", nullable = false, length = 50)
    public String getAccLevelName() {
        return accLevelName;
    }

    public void setAccLevelName(String accLevelName) {
        this.accLevelName = accLevelName;
    }

    @Basic
    @Column(name = "acc_level_desc", nullable = false, length = 500)
    public String getAccLevelDesc() {
        return accLevelDesc;
    }

    public void setAccLevelDesc(String accLevelDesc) {
        this.accLevelDesc = accLevelDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessLevel that = (AccessLevel) o;
        return accLevelId == that.accLevelId &&
                Objects.equals(accLevelName, that.accLevelName) &&
                Objects.equals(accLevelDesc, that.accLevelDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accLevelId, accLevelName, accLevelDesc);
    }
}
