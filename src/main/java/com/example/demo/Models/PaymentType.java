package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PaymentType {
    private int pmtTypeId;
    private String pmtType;
    private String pmtTypeDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "pmt_type_id", nullable = false)
    public int getPmtTypeId() {
        return pmtTypeId;
    }

    public void setPmtTypeId(int pmtTypeId) {
        this.pmtTypeId = pmtTypeId;
    }

    @Basic
    @Column(name = "pmt_type", nullable = false, length = 50)
    public String getPmtType() {
        return pmtType;
    }

    public void setPmtType(String pmtType) {
        this.pmtType = pmtType;
    }

    @Basic
    @Column(name = "pmt_type_desc", nullable = false, length = 250)
    public String getPmtTypeDesc() {
        return pmtTypeDesc;
    }

    public void setPmtTypeDesc(String pmtTypeDesc) {
        this.pmtTypeDesc = pmtTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentType that = (PaymentType) o;
        return pmtTypeId == that.pmtTypeId &&
                Objects.equals(pmtType, that.pmtType) &&
                Objects.equals(pmtTypeDesc, that.pmtTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pmtTypeId, pmtType, pmtTypeDesc);
    }
}
