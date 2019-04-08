package com.example.demo;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Svc {
    private int svcId;
    private String svcName;

    private Set<ServiceOrderLine> serviceOrderLines;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "svc")
    public Set<ServiceOrderLine> getServiceOrderLines() {
        return serviceOrderLines;
    }

    public void setServiceOrderLines(Set<ServiceOrderLine> serviceOrderLines) {
        serviceOrderLines.forEach(serviceOrderLine -> serviceOrderLine.setSvc(this));
        this.serviceOrderLines = serviceOrderLines;
    }

    @Id
    @Column(name = "svc_id", nullable = false)
    public int getSvcId() {
        return svcId;
    }

    public void setSvcId(int svcId) {
        this.svcId = svcId;
    }

    @Basic
    @Column(name = "svc_name", nullable = false, length = 150)
    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Svc svc = (Svc) o;
        return svcId == svc.svcId &&
                Objects.equals(svcName, svc.svcName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svcId, svcName);
    }
}
