package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ServiceType {
    private int svcTypeId;
    private String svcType;
    private String svcTypeDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svc_type_id", nullable = false)
    public int getSvcTypeId() {
        return svcTypeId;
    }

    public void setSvcTypeId(int svcTypeId) {
        this.svcTypeId = svcTypeId;
    }

    @Basic
    @Column(name = "svc_type", nullable = false, length = 100)
    public String getSvcType() {
        return svcType;
    }

    public void setSvcType(String svcType) {
        this.svcType = svcType;
    }

    @Basic
    @Column(name = "svc_type_desc", nullable = false, length = 250)
    public String getSvcTypeDesc() {
        return svcTypeDesc;
    }

    public void setSvcTypeDesc(String svcTypeDesc) {
        this.svcTypeDesc = svcTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceType that = (ServiceType) o;
        return svcTypeId == that.svcTypeId &&
                Objects.equals(svcType, that.svcType) &&
                Objects.equals(svcTypeDesc, that.svcTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svcTypeId, svcType, svcTypeDesc);
    }
}
