package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class IncidentType {
    private int incidentTypeId;
    private String incidentType;
    private String incidentTypeDesc;

    // 1:M with Incidents

    private Set<Incident> incidents;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceOrder")
    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "incident_type_id", nullable = false)
    public int getIncidentTypeId() {
        return incidentTypeId;
    }

    public void setIncidentTypeId(int incidentTypeId) {
        this.incidentTypeId = incidentTypeId;
    }

    @Basic
    @Column(name = "incident_type", nullable = false, length = 24)
    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    @Basic
    @Column(name = "incident_type_desc", nullable = false, length = 250)
    public String getIncidentTypeDesc() {
        return incidentTypeDesc;
    }

    public void setIncidentTypeDesc(String incidentTypeDesc) {
        this.incidentTypeDesc = incidentTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidentType that = (IncidentType) o;
        return incidentTypeId == that.incidentTypeId &&
                Objects.equals(incidentType, that.incidentType) &&
                Objects.equals(incidentTypeDesc, that.incidentTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidentTypeId, incidentType, incidentTypeDesc);
    }
}
