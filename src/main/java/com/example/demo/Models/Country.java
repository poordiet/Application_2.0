package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Country {
    private int countryId;
    private String countryName;

    // 1:M with customer site
    private Set<CustomerSite> customerSites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "country")
    public Set<CustomerSite> getCustomerSites() {
        return customerSites;
    }

    public void setCustomerSites(Set<CustomerSite> customerSites) {
        customerSites.forEach(customerSite -> customerSite.setCountry(this));
        this.customerSites = customerSites;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Basic
    @Column(name = "country_name", nullable = false, length = 200)
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return countryId == country.countryId &&
                Objects.equals(countryName, country.countryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryId, countryName);
    }
}
