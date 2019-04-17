package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class IncidentStatus {
    private int incidentStatusId;
    private String incidentStatus;
    private String incidentStatusDesc;

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
    @Column(name = "incident_status_id", nullable = false)
    public int getIncidentStatusId() {
        return incidentStatusId;
    }

    public void setIncidentStatusId(int incidentStatusId) {
        this.incidentStatusId = incidentStatusId;
    }

    @Basic
    @Column(name = "incident_status", nullable = false, length = 50)
    public String getIncidentStatus() {
        return incidentStatus;
    }

    public void setIncidentStatus(String incidentStatus) {
        this.incidentStatus = incidentStatus;
    }

    @Basic
    @Column(name = "incident_status_desc", nullable = false, length = 250)
    public String getIncidentStatusDesc() {
        return incidentStatusDesc;
    }

    public void setIncidentStatusDesc(String incidentStatusDesc) {
        this.incidentStatusDesc = incidentStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IncidentStatus that = (IncidentStatus) o;
        return incidentStatusId == that.incidentStatusId &&
                Objects.equals(incidentStatus, that.incidentStatus) &&
                Objects.equals(incidentStatusDesc, that.incidentStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidentStatusId, incidentStatus, incidentStatusDesc);
    }
}
