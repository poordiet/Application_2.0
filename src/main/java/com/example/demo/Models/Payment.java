package com.example.demo.Models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Payment {
    private int pmtId;
    private BigDecimal amt;
    private Date datePaid;

    // M:1 with Service Order
    private ServiceOrder serviceOrder;

    @ManyToOne
    @JoinColumn(name="svo_id")
    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "pmt_id", nullable = false)
    public int getPmtId() {
        return pmtId;
    }

    public void setPmtId(int pmtId) {
        this.pmtId = pmtId;
    }

    @Basic
    @Column(name = "amt", nullable = false, precision = 2)
    public BigDecimal getAmt() {
        return amt;
    }

    public void setAmt(BigDecimal amt) {
        this.amt = amt;
    }

    @Basic
    @Column(name = "date_paid", nullable = false)
    public Date getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(Date datePaid) {
        this.datePaid = datePaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return pmtId == payment.pmtId &&
                Objects.equals(amt, payment.amt) &&
                Objects.equals(datePaid, payment.datePaid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pmtId, amt, datePaid);
    }
}
