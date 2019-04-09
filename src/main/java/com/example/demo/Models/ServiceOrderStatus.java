package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ServiceOrderStatus {
    private int svoStatusId;
    private String svoStatus;
    private String svoStatusDesc;

/* Returns Error
    // 1:M with Service Order
    private Set<ServiceOrder> serviceOrders;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceOrderStatus")
    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        serviceOrders.forEach(serviceOrder -> serviceOrder.setServiceOrderStatus(this));
        this.serviceOrders = serviceOrders;
    }*/

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svo_status_id", nullable = false)
    public int getSvoStatusId() {
        return svoStatusId;
    }

    public void setSvoStatusId(int svoStatusId) {
        this.svoStatusId = svoStatusId;
    }

    @Basic
    @Column(name = "svo_status", nullable = false, length = 50)
    public String getSvoStatus() {
        return svoStatus;
    }

    public void setSvoStatus(String svoStatus) {
        this.svoStatus = svoStatus;
    }

    @Basic
    @Column(name = "svo_status_desc", nullable = false, length = 250)
    public String getSvoStatusDesc() {
        return svoStatusDesc;
    }

    public void setSvoStatusDesc(String svoStatusDesc) {
        this.svoStatusDesc = svoStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrderStatus that = (ServiceOrderStatus) o;
        return svoStatusId == that.svoStatusId &&
                Objects.equals(svoStatus, that.svoStatus) &&
                Objects.equals(svoStatusDesc, that.svoStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svoStatusId, svoStatus, svoStatusDesc);
    }
}
