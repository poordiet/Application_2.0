package com.example.demo.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class ServiceOrderLine {

    private int svoLineId;



    // 1:M with Hw_Svo_Line
    private Set<HwSvoLine> hwSvoLines;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerSiteHw")
    public Set<HwSvoLine> getHwSvoLines() {
        return hwSvoLines;
    }

    public void setHwSvoLines(Set<HwSvoLine> hwSvoLines) {
        this.hwSvoLines = hwSvoLines;
    }


    // 1:M with Assignment
    private Set<Assignment> assignments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceOrderLine")
    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
//        assignments.forEach(assignment -> assignment.setServiceOrderLine(this));
        this.assignments = assignments;
    }

    // M:1 with service Order
    private ServiceOrder serviceOrder;

    @ManyToOne
    @JoinColumn(name="svo_id")
    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }


    // M:1 with service
    private Svc svc;

    @ManyToOne
    @JoinColumn(name="svc_id")
    public Svc getSvc() {
        return svc;
    }

    public void setSvc(Svc svc) {
        this.svc = svc;
    }




    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svo_line_id", nullable = false)
    public int getSvoLineId() {
        return svoLineId;
    }

    public void setSvoLineId(int svoLineId) {
        this.svoLineId = svoLineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrderLine that = (ServiceOrderLine) o;
        return svoLineId == that.svoLineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(svoLineId);
    }


}
