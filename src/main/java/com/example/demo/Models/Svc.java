package com.example.demo.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Svc {
    private int svcId;
    private String svcName;

    /*
    // M:M with Service Order
    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "svcs")
    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }


    private List<ServiceOrder> serviceOrders;

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public void addServiceOrder(ServiceOrder serviceOrder){serviceOrders.add(serviceOrder);}
*/

    // M:1 with svc Type
    private ServiceType serviceType;

    @ManyToOne
    @JoinColumn(name="svc_type_id")
    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
    }

    // M:1 with svc Status
    private ServiceStatus serviceStatus;

    @ManyToOne
    @JoinColumn(name="svc_status_id")
    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    // One to Many with Service order Line

    private Set<ServiceOrderLine> serviceOrderLines;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "svc")
    public Set<ServiceOrderLine> getServiceOrderLines() {
        return serviceOrderLines;
    }

    public void setServiceOrderLines(Set<ServiceOrderLine> serviceOrderLines) {
//        serviceOrderLines.forEach(serviceOrderLine -> serviceOrderLine.setSvc(this));
        this.serviceOrderLines = serviceOrderLines;
    }


    // 1:M with Required Skill
    private Set<RequiredSkill> requiredSkills;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "svc")
    public Set<RequiredSkill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<RequiredSkill> requiredSkills) {
        this.requiredSkills = requiredSkills;
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
