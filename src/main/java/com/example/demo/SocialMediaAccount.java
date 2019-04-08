package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class SocialMediaAccount {
    private int smAcctId;
    private String smUrl;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "sm_acct_id", nullable = false)
    public int getSmAcctId() {
        return smAcctId;
    }

    public void setSmAcctId(int smAcctId) {
        this.smAcctId = smAcctId;
    }

    @Basic
    @Column(name = "sm_url", nullable = false, length = 100)
    public String getSmUrl() {
        return smUrl;
    }

    public void setSmUrl(String smUrl) {
        this.smUrl = smUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SocialMediaAccount that = (SocialMediaAccount) o;
        return smAcctId == that.smAcctId &&
                Objects.equals(smUrl, that.smUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(smAcctId, smUrl);
    }
}
