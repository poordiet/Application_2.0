package com.example.demo.Presentation;

import javax.persistence.*;

public class DbMgmtPresentation {

    private int hwTypeId;
    private String hwType;
    private String hwTypeDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_type_id", nullable = false)
    public int getHwTypeId() {
        return hwTypeId;
    }

    public void setHwTypeId(int hwTypeId) {
        this.hwTypeId = hwTypeId;
    }

    @Basic
    @Column(name = "hw_type", nullable = false, length = 50)
    public String getHwType() {
        return hwType;
    }

    public void setHwType(String hwType) {
        this.hwType = hwType;
    }

    @Basic
    @Column(name = "hw_type_desc", nullable = false, length = 250)
    public String getHwTypeDesc() {
        return hwTypeDesc;
    }

    public void setHwTypeDesc(String hwTypeDesc) {
        this.hwTypeDesc = hwTypeDesc;
    }
}
