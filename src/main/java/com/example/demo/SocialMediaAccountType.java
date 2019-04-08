package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SocialMediaAccountType {
    private int smAcctTypeId;
    private String smAcctType;
    private String smAcctTypeDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "sm_acct_type_id", nullable = false)
    public int getSmAcctTypeId() {
        return smAcctTypeId;
    }

    public void setSmAcctTypeId(int smAcctTypeId) {
        this.smAcctTypeId = smAcctTypeId;
    }

    @Basic
    @Column(name = "sm_acct_type", nullable = false, length = 50)
    public String getSmAcctType() {
        return smAcctType;
    }

    public void setSmAcctType(String smAcctType) {
        this.smAcctType = smAcctType;
    }

    @Basic
    @Column(name = "sm_acct_type_desc", nullable = false, length = 250)
    public String getSmAcctTypeDesc() {
        return smAcctTypeDesc;
    }

    public void setSmAcctTypeDesc(String smAcctTypeDesc) {
        this.smAcctTypeDesc = smAcctTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialMediaAccountType that = (SocialMediaAccountType) o;
        return smAcctTypeId == that.smAcctTypeId &&
                Objects.equals(smAcctType, that.smAcctType) &&
                Objects.equals(smAcctTypeDesc, that.smAcctTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smAcctTypeId, smAcctType, smAcctTypeDesc);
    }
}
