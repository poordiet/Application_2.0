package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwProviderStatus {
    private int hwProviderStatusId;
    private String hwProviderStatus;
    private String hwProviderStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_provider_status_id", nullable = false)
    public int getHwProviderStatusId() {
        return hwProviderStatusId;
    }

    public void setHwProviderStatusId(int hwProviderStatusId) {
        this.hwProviderStatusId = hwProviderStatusId;
    }

    @Basic
    @Column(name = "hw_provider_status", nullable = false, length = 50)
    public String getHwProviderStatus() {
        return hwProviderStatus;
    }

    public void setHwProviderStatus(String hwProviderStatus) {
        this.hwProviderStatus = hwProviderStatus;
    }

    @Basic
    @Column(name = "hw_provider_status_desc", nullable = false, length = 250)
    public String getHwProviderStatusDesc() {
        return hwProviderStatusDesc;
    }

    public void setHwProviderStatusDesc(String hwProviderStatusDesc) {
        this.hwProviderStatusDesc = hwProviderStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwProviderStatus that = (HwProviderStatus) o;
        return hwProviderStatusId == that.hwProviderStatusId &&
                Objects.equals(hwProviderStatus, that.hwProviderStatus) &&
                Objects.equals(hwProviderStatusDesc, that.hwProviderStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwProviderStatusId, hwProviderStatus, hwProviderStatusDesc);
    }
}
