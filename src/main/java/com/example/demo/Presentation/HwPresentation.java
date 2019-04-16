package com.example.demo.Presentation;

import com.example.demo.Models.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

public class HwPresentation {

    // HW Manu
    private int hwManuId;
    private String hwManuName;
    private String hwManuPhone;


    // 1:M with Hardware Series
    private Set<HwSeries> hwSeriesSet;

    public Set<HwSeries> getHwSeriesSet() {
        return hwSeriesSet;
    }

    public void setHwSeriesSet(Set<HwSeries> hwSeriesSet) {
        this.hwSeriesSet = hwSeriesSet;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_manu_id", nullable = false)
    public int getHwManuId() {
        return hwManuId;
    }

    public void setHwManuId(int hwManuId) {
        this.hwManuId = hwManuId;
    }

    @Basic
    @Column(name = "hw_manu_name", nullable = false, length = 50)
    public String getHwManuName() {
        return hwManuName;
    }

    public void setHwManuName(String hwManuName) {
        this.hwManuName = hwManuName;
    }

    @Basic
    @Column(name = "hw_manu_phone", nullable = false, length = 15)
    public String getHwManuPhone() {
        return hwManuPhone;
    }

    public void setHwManuPhone(String hwManuPhone) {
        this.hwManuPhone = hwManuPhone;
    }



    // HW Series
    private int hwSeriesId;
    private String hwSeriesName;

    // 1:M with Hardware Model
    private Set<HwModel> hwModels;

    public Set<HwModel> getHwModels() {
        return hwModels;
    }

    public void setHwModels(Set<HwModel> hwModels) {
        this.hwModels = hwModels;
    }


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_series_id", nullable = false)
    public int getHwSeriesId() {
        return hwSeriesId;
    }

    public void setHwSeriesId(int hwSeriesId) {
        this.hwSeriesId = hwSeriesId;
    }

    @Basic
    @Column(name = "hw_series_name", nullable = false, length = 100)
    public String getHwSeriesName() {
        return hwSeriesName;
    }

    public void setHwSeriesName(String hwSeriesName) {
        this.hwSeriesName = hwSeriesName;
    }


    // HW Model


    private int hwModelId;
    private String hwModel;

    // 1:M with Hw Inventory
    private Set<HwInventory> hwInventories;

    public Set<HwInventory> getHwInventories() {
        return hwInventories;
    }

    public void setHwInventories(Set<HwInventory>hwInventories) {
        this.hwInventories = hwInventories;
    }



    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_model_id", nullable = false)
    public int getHwModelId() {
        return hwModelId;
    }

    public void setHwModelId(int hwModelId) {
        this.hwModelId = hwModelId;
    }

    @Basic
    @Column(name = "hw_model", nullable = false, length = 100)
    public String getHwModel() {
        return hwModel;
    }

    public void setHwModel(String hwModel) {
        this.hwModel = hwModel;
    }


    // HW Type

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


    // HwInventory

    private int hwInvId;
    private BigDecimal hwCost;
    private String hwSerialNumber;
    private String hwMacAddress;
    private Date hwPurchaseDate;
    private BigDecimal hwSalePrice;


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_inv_id", nullable = false)
    public int getHwInvId() {
        return hwInvId;
    }

    public void setHwInvId(int hwInvId) {
        this.hwInvId = hwInvId;
    }

    @Basic
    @Column(name = "hw_cost", nullable = true, precision = 2)
    public BigDecimal getHwCost() {
        return hwCost;
    }

    public void setHwCost(BigDecimal hwCost) {
        this.hwCost = hwCost;
    }

    @Basic
    @Column(name = "hw_serial_number", nullable = false, length = 24)
    public String getHwSerialNumber() {
        return hwSerialNumber;
    }

    public void setHwSerialNumber(String hwSerialNumber) {
        this.hwSerialNumber = hwSerialNumber;
    }

    @Basic
    @Column(name = "hw_mac_address", nullable = true, length = 24)
    public String getHwMacAddress() {
        return hwMacAddress;
    }

    public void setHwMacAddress(String hwMacAddress) {
        this.hwMacAddress = hwMacAddress;
    }

    @Basic
    @Column(name = "hw_purchase_date", nullable = true)
    public Date getHwPurchaseDate() {
        return hwPurchaseDate;
    }

    public void setHwPurchaseDate(Date hwPurchaseDate) {
        this.hwPurchaseDate = hwPurchaseDate;
    }

    @Basic
    @Column(name = "hw_sale_price", nullable = true, precision = 2)
    public BigDecimal getHwSalePrice() {
        return hwSalePrice;
    }

    public void setHwSalePrice(BigDecimal hwSalePrice) {
        this.hwSalePrice = hwSalePrice;
    }


    // CustSiteHW

    private int custSiteHwId;
    private String custSiteSerialNumber;
    private String custSiteMacAddress;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "cust_site_hw_id", nullable = false)
    public int getCustSiteHwId() {
        return custSiteHwId;
    }

    public void setCustSiteHwId(int custSiteHwId) {
        this.custSiteHwId = custSiteHwId;
    }

    @Basic
    @Column(name = "cust_site_serial_number", nullable = false, length = 24)
    public String getCustSiteSerialNumber() {
        return custSiteSerialNumber;
    }

    public void setCustSiteSerialNumber(String custSiteSerialNumber) {
        this.custSiteSerialNumber = custSiteSerialNumber;
    }

    @Basic
    @Column(name = "cust_site_mac_address", nullable = true, length = 24)
    public String getCustSiteMacAddress() {
        return custSiteMacAddress;
    }

    public void setCustSiteMacAddress(String custSiteMacAddress) {
        this.custSiteMacAddress = custSiteMacAddress;
    }

    // HW Inventory Status
    private int hwInvStatusId;
    private String hwInvStatus;
    private String hwInvStatusDesc;


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_inv_status_id", nullable = false)
    public int getHwInvStatusId() {
        return hwInvStatusId;
    }

    public void setHwInvStatusId(int hwInvStatusId) {
        this.hwInvStatusId = hwInvStatusId;
    }

    @Basic
    @Column(name = "hw_inv_status", nullable = false, length = 50)
    public String getHwInvStatus() {
        return hwInvStatus;
    }

    public void setHwInvStatus(String hwInvStatus) {
        this.hwInvStatus = hwInvStatus;
    }

    @Basic
    @Column(name = "hw_inv_status_desc", nullable = false, length = 250)
    public String getHwInvStatusDesc() {
        return hwInvStatusDesc;
    }

    public void setHwInvStatusDesc(String hwInvStatusDesc) {
        this.hwInvStatusDesc = hwInvStatusDesc;
    }

    private HwInventoryStatus hwInventoryStatus;

    public HwInventoryStatus getHwInventoryStatus() {
        return hwInventoryStatus;
    }

    public void setHwInventoryStatus(HwInventoryStatus hwInventoryStatus) {
        this.hwInventoryStatus = hwInventoryStatus;
    }

    // SVC

    private int svcId;
    private String svcName;

    public int getSvcId() {
        return svcId;
    }

    public void setSvcId(int svcId) {
        this.svcId = svcId;
    }


    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }


    // Hardware Provider

    private int hwProviderId;
    private String hwProviderName;
    private String hwProviderAddress;
    private String hwProviderCity;
    private String hwProviderZip;
    private String hwProviderPhone;
    private String hwProviderEmail;
    private String hwProviderWeb;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "hw_provider_id", nullable = false)
    public int getHwProviderId() {
        return hwProviderId;
    }

    public void setHwProviderId(int hwProviderId) {
        this.hwProviderId = hwProviderId;
    }

    @Basic
    @Column(name = "hw_provider_name", nullable = false, length = 50)
    public String getHwProviderName() {
        return hwProviderName;
    }

    public void setHwProviderName(String hwProviderName) {
        this.hwProviderName = hwProviderName;
    }

    @Basic
    @Column(name = "hw_provider_address", nullable = true, length = 50)
    public String getHwProviderAddress() {
        return hwProviderAddress;
    }

    public void setHwProviderAddress(String hwProviderAddress) {
        this.hwProviderAddress = hwProviderAddress;
    }

    @Basic
    @Column(name = "hw_provider_city", nullable = true, length = 50)
    public String getHwProviderCity() {
        return hwProviderCity;
    }

    public void setHwProviderCity(String hwProviderCity) {
        this.hwProviderCity = hwProviderCity;
    }

    @Basic
    @Column(name = "hw_provider_zip", nullable = true, length = 15)
    public String getHwProviderZip() {
        return hwProviderZip;
    }

    public void setHwProviderZip(String hwProviderZip) {
        this.hwProviderZip = hwProviderZip;
    }

    @Basic
    @Column(name = "hw_provider_phone", nullable = true, length = 20)
    public String getHwProviderPhone() {
        return hwProviderPhone;
    }

    public void setHwProviderPhone(String hwProviderPhone) {
        this.hwProviderPhone = hwProviderPhone;
    }

    @Basic
    @Column(name = "hw_provider_email", nullable = true, length = 50)
    public String getHwProviderEmail() {
        return hwProviderEmail;
    }

    public void setHwProviderEmail(String hwProviderEmail) {
        this.hwProviderEmail = hwProviderEmail;
    }

    @Basic
    @Column(name = "hw_provider_web", nullable = true, length = 100)
    public String getHwProviderWeb() {
        return hwProviderWeb;
    }

    public void setHwProviderWeb(String hwProviderWeb) {
        this.hwProviderWeb = hwProviderWeb;
    }



}
