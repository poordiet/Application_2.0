package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ServiceStatus {
    private int svcStatusId;
    private String svcStatus;
    private String svcStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svc_status_id", nullable = false)
    public int getSvcStatusId() {
        return svcStatusId;
    }

    public void setSvcStatusId(int svcStatusId) {
        this.svcStatusId = svcStatusId;
    }

    @Basic
    @Column(name = "svc_status", nullable = false, length = 75)
    public String getSvcStatus() {
        return svcStatus;
    }

    public void setSvcStatus(String svcStatus) {
        this.svcStatus = svcStatus;
    }

    @Basic
    @Column(name = "svc_status_desc", nullable = false, length = 250)
    public String getSvcStatusDesc() {
        return svcStatusDesc;
    }

    public void setSvcStatusDesc(String svcStatusDesc) {
        this.svcStatusDesc = svcStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceStatus that = (ServiceStatus) o;
        return svcStatusId == that.svcStatusId &&
                Objects.equals(svcStatus, that.svcStatus) &&
                Objects.equals(svcStatusDesc, that.svcStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svcStatusId, svcStatus, svcStatusDesc);
    }
}
