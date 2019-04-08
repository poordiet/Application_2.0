package com.example.demo;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Objects;

@Entity
public class HwInventory {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwInventory that = (HwInventory) o;
        return hwInvId == that.hwInvId &&
                Objects.equals(hwCost, that.hwCost) &&
                Objects.equals(hwSerialNumber, that.hwSerialNumber) &&
                Objects.equals(hwMacAddress, that.hwMacAddress) &&
                Objects.equals(hwPurchaseDate, that.hwPurchaseDate) &&
                Objects.equals(hwSalePrice, that.hwSalePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwInvId, hwCost, hwSerialNumber, hwMacAddress, hwPurchaseDate, hwSalePrice);
    }
}
