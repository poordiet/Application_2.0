package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class HwSvoLine {
    private int hwSvoLineId;

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
