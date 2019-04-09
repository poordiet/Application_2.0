package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class StateProvince {
    private int stateId;
    private String stateName;

    // 1:M with Assignment
    private Set<CustomerSite> customerSites;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "stateProvince")
    public Set<CustomerSite> getCustomerSites() {
        return customerSites;
    }

    public void setCustomerSites(Set<CustomerSite> customerSites) {
        customerSites.forEach(customerSite -> customerSite.setStateProvince(this));
        this.customerSites = customerSites;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "state_id", nullable = false)
    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @Basic
    @Column(name = "state_name", nullable = false, length = 200)
    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateProvince that = (StateProvince) o;
        return stateId == that.stateId &&
                Objects.equals(stateName, that.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stateId, stateName);
    }
}
