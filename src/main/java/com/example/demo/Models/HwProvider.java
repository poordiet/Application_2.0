package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class HwProvider {
    private int hwProviderId;
    private String hwProviderName;
    private String hwProviderAddress;
    private String hwProviderCity;
    private String hwProviderZip;
    private String hwProviderPhone;
    private String hwProviderEmail;
    private String hwProviderWeb;

    // 1:M with Hardware Inventory
    private Set<HwInventory> hwInventories;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "hwProvider")
    public Set<HwInventory> getHwInventories() {
        return hwInventories;
    }

    public void setHwInventories(Set<HwInventory> hwInventories) {
        this.hwInventories = hwInventories;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HwProvider that = (HwProvider) o;
        return hwProviderId == that.hwProviderId &&
                Objects.equals(hwProviderName, that.hwProviderName) &&
                Objects.equals(hwProviderAddress, that.hwProviderAddress) &&
                Objects.equals(hwProviderCity, that.hwProviderCity) &&
                Objects.equals(hwProviderZip, that.hwProviderZip) &&
                Objects.equals(hwProviderPhone, that.hwProviderPhone) &&
                Objects.equals(hwProviderEmail, that.hwProviderEmail) &&
                Objects.equals(hwProviderWeb, that.hwProviderWeb);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hwProviderId, hwProviderName, hwProviderAddress, hwProviderCity, hwProviderZip, hwProviderPhone, hwProviderEmail, hwProviderWeb);
    }
}
