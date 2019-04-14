package com.example.demo.Presentation;

import com.example.demo.Models.*;

import javax.persistence.*;
import java.util.List;
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

    // Hw Svo Line
    // 1:M with Hw_Svo_Line
    private Set<HwSvoLine> hwSvoLines;

    public Set<HwSvoLine> getHwSvoLines() {
        return hwSvoLines;
    }

    public void setHwSvoLines(Set<HwSvoLine> hwSvoLines) {
        this.hwSvoLines = hwSvoLines;
    }

    // Hw Presentation
    private List<HwPresentation> hwPresentations;

    public List<HwPresentation> getHwPresentations() {
        return hwPresentations;
    }

    public void setHwPresentations(List<HwPresentation> hwPresentations) {
        this.hwPresentations = hwPresentations;
    }
}
