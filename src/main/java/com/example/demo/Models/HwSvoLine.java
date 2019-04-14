package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwSvoLine {
    private int hwSvoLineId;

    // M:1 with Cust Site HW
    private CustomerSiteHw customerSiteHw;

    @ManyToOne
    @JoinColumn(name="cust_site_hw_id")
    public CustomerSiteHw getCustomerSiteHw() {
        return customerSiteHw;
    }

    public void setCustomerSiteHw(CustomerSiteHw customerSiteHw) {
        this.customerSiteHw = customerSiteHw;
    }

    // M:1 with Service Order Line
    private ServiceOrderLine serviceOrderLine;

    @ManyToOne
    @JoinColumn(name="svo_line_id")
    public ServiceOrderLine getServiceOrderLine() {
        return serviceOrderLine;
    }

    public void setServiceOrderLine(ServiceOrderLine serviceOrderLine) {
        this.serviceOrderLine = serviceOrderLine;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_svo_line_id", nullable = false)
    public int getHwSvoLineId() {
        return hwSvoLineId;
    }

    public void setHwSvoLineId(int hwSvoLineId) {
        this.hwSvoLineId = hwSvoLineId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwSvoLine hwSvoLine = (HwSvoLine) o;
        return hwSvoLineId == hwSvoLine.hwSvoLineId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwSvoLineId);
    }
}
