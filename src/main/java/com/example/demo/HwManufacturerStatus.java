package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwManufacturerStatus {
    private int hwManuStatusId;
    private String hwManuStatus;
    private String hwManuStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_manu_status_id", nullable = false)
    public int getHwManuStatusId() {
        return hwManuStatusId;
    }

    public void setHwManuStatusId(int hwManuStatusId) {
        this.hwManuStatusId = hwManuStatusId;
    }

    @Basic
    @Column(name = "hw_manu_status", nullable = false, length = 50)
    public String getHwManuStatus() {
        return hwManuStatus;
    }

    public void setHwManuStatus(String hwManuStatus) {
        this.hwManuStatus = hwManuStatus;
    }

    @Basic
    @Column(name = "hw_manu_status_desc", nullable = false, length = 250)
    public String getHwManuStatusDesc() {
        return hwManuStatusDesc;
    }

    public void setHwManuStatusDesc(String hwManuStatusDesc) {
        this.hwManuStatusDesc = hwManuStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwManufacturerStatus that = (HwManufacturerStatus) o;
        return hwManuStatusId == that.hwManuStatusId &&
                Objects.equals(hwManuStatus, that.hwManuStatus) &&
                Objects.equals(hwManuStatusDesc, that.hwManuStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwManuStatusId, hwManuStatus, hwManuStatusDesc);
    }
}
