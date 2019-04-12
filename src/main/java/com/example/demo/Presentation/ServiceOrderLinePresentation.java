package com.example.demo.Presentation;

import com.example.demo.Models.Assignment;
import com.example.demo.Models.ServiceOrder;
import com.example.demo.Models.ServiceOrderLine;
import com.example.demo.Models.Svc;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

public class ServiceOrderLinePresentation {

    // SVC
    private int svcId;
    private String svcName;

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

    // ServiceOrderLine
    private int svoLineId;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svo_line_id", nullable = false)
    public int getSvoLineId() {
        return svoLineId;
    }

    public void setSvoLineId(int svoLineId) {
        this.svoLineId = svoLineId;
    }


}
