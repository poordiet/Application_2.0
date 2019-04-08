package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwModel {
    private int hwModelId;
    private String hwModel;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_model_id", nullable = false)
    public int getHwModelId() {
        return hwModelId;
    }

    public void setHwModelId(int hwModelId) {
        this.hwModelId = hwModelId;
    }

    @Basic
    @Column(name = "hw_model", nullable = false, length = 100)
    public String getHwModel() {
        return hwModel;
    }

    public void setHwModel(String hwModel) {
        this.hwModel = hwModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwModel hwModel1 = (HwModel) o;
        return hwModelId == hwModel1.hwModelId &&
                Objects.equals(hwModel, hwModel1.hwModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwModelId, hwModel);
    }
}
