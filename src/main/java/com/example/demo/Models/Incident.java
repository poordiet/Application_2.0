package com.example.demo.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Incident {
    private int incidentId;
    private String incident;
    private Date incidentDate;
    private int svoId;

    // M:1 with Service Order
    private ServiceOrder serviceOrder;

    @ManyToOne
    @JoinColumn(name="svo_id")
    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "incident_id", nullable = false)
    public int getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(int incidentId) {
        this.incidentId = incidentId;
    }

    @Basic
    @Column(name = "incident", nullable = false, length = 5000)
    public String getIncident() {
        return incident;
    }

    public void setIncident(String incident) {
        this.incident = incident;
    }

    @Basic
    @Column(name = "incident_date", nullable = false)
    public Date getIncidentDate() {
        return incidentDate;
    }

    public void setIncidentDate(Date incidentDate) {
        this.incidentDate = incidentDate;
    }

    /* -- Should this be here?
    @Basic
    @Column(name = "svo_id", nullable = false)
    public int getSvoId() {
        return svoId;
    }

    public void setSvoId(int svoId) {
        this.svoId = svoId;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident1 = (Incident) o;
        return incidentId == incident1.incidentId &&
                svoId == incident1.svoId &&
                Objects.equals(incident, incident1.incident) &&
                Objects.equals(incidentDate, incident1.incidentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incidentId, incident, incidentDate, svoId);
    }
}
