package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class HwInventoryStatus {
    private int hwInvStatusId;
    private String hwInvStatus;
    private String hwInvStatusDesc;


    // 1:M with Hw Inventory
    private Set<HwInventory> hwInventories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hwInventoryStatus")
    public Set<HwInventory> getHwInventories() {
        return hwInventories;
    }

    public void setHwInventories(Set<HwInventory> hwInventories) {
        this.hwInventories = hwInventories;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_inv_status_id", nullable = false)
    public int getHwInvStatusId() {
        return hwInvStatusId;
    }

    public void setHwInvStatusId(int hwInvStatusId) {
        this.hwInvStatusId = hwInvStatusId;
    }

    @Basic
    @Column(name = "hw_inv_status", nullable = false, length = 50)
    public String getHwInvStatus() {
        return hwInvStatus;
    }

    public void setHwInvStatus(String hwInvStatus) {
        this.hwInvStatus = hwInvStatus;
    }

    @Basic
    @Column(name = "hw_inv_status_desc", nullable = false, length = 250)
    public String getHwInvStatusDesc() {
        return hwInvStatusDesc;
    }

    public void setHwInvStatusDesc(String hwInvStatusDesc) {
        this.hwInvStatusDesc = hwInvStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwInventoryStatus that = (HwInventoryStatus) o;
        return hwInvStatusId == that.hwInvStatusId &&
                Objects.equals(hwInvStatus, that.hwInvStatus) &&
                Objects.equals(hwInvStatusDesc, that.hwInvStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwInvStatusId, hwInvStatus, hwInvStatusDesc);
    }
}
