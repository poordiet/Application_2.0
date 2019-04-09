package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwModelStatus {
    private int hwModelStatusId;
    private String hwModelStatus;
    private String hwModelStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_model_status_id", nullable = false)
    public int getHwModelStatusId() {
        return hwModelStatusId;
    }

    public void setHwModelStatusId(int hwModelStatusId) {
        this.hwModelStatusId = hwModelStatusId;
    }

    @Basic
    @Column(name = "hw_model_status", nullable = false, length = 50)
    public String getHwModelStatus() {
        return hwModelStatus;
    }

    public void setHwModelStatus(String hwModelStatus) {
        this.hwModelStatus = hwModelStatus;
    }

    @Basic
    @Column(name = "hw_model_status_desc", nullable = false, length = 250)
    public String getHwModelStatusDesc() {
        return hwModelStatusDesc;
    }

    public void setHwModelStatusDesc(String hwModelStatusDesc) {
        this.hwModelStatusDesc = hwModelStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwModelStatus that = (HwModelStatus) o;
        return hwModelStatusId == that.hwModelStatusId &&
                Objects.equals(hwModelStatus, that.hwModelStatus) &&
                Objects.equals(hwModelStatusDesc, that.hwModelStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwModelStatusId, hwModelStatus, hwModelStatusDesc);
    }
}
