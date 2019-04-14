package com.example.demo.Models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class ServiceOrder {
    private int svoId;
    private Date dateRequested;
    private Timestamp dateScheduled;
    private Timestamp dateStarted;
    private Timestamp dateFinished;
    private String workRequest;
    private String workSummary;
    private BigDecimal total;
    private int serviceOrderStatusId;
/*
    // M:M with Service
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SERVICE_ORDER_LINE",
            joinColumns = { @JoinColumn(name = "svo_id") },
            inverseJoinColumns = { @JoinColumn(name = "svc_id") })
    public List<Svc> getSvcs() {
        return svcs;
    }

    private List<Svc> svcs;

    public void setSvcs(List<Svc> svcs) {
        this.svcs = svcs;
    }

    public void addSvc(Svc svc){
        svcs.add(svc);
    }

    */

    // 1:M with Payments

    private Set<Payment> payments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceOrder")
    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
//        payments.forEach(payment -> payment.setServiceOrder(this));
        this.payments = payments;
    }


    // 1:M with Incidents

    private Set<Incident> incidents;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceOrder")
    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
//        incidents.forEach(incident -> incident.setServiceOrder(this));
        this.incidents = incidents;
    }

    // 1:M with Service Order Lines

    private Set<ServiceOrderLine> serviceOrderLines;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "serviceOrder")
    public Set<ServiceOrderLine> getServiceOrderLines() {
        return serviceOrderLines;
    }

    public void setServiceOrderLines(Set<ServiceOrderLine> serviceOrderLines) {
//        serviceOrderLines.forEach(serviceOrderLine -> serviceOrderLine.setServiceOrder(this));
        this.serviceOrderLines = serviceOrderLines;
    }



    // M:1 with Contact
    private Contact contact;

    @ManyToOne
    @JoinColumn(name="contact_id")
    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    // M:1 with CustomerSite
    private CustomerSite customerSite;

    @ManyToOne
    @JoinColumn(name="cust_site_id")
    public CustomerSite getCustomerSite() {
        return customerSite;
    }

    public void setCustomerSite(CustomerSite customerSite) {
        this.customerSite = customerSite;
    }

    // M:1 with Service Order Status
    private ServiceOrderStatus serviceOrderStatus;

    @ManyToOne
    @JoinColumn(name="svo_status_id")
    public ServiceOrderStatus getServiceOrderStatus() {
        return serviceOrderStatus;
    }

    public void setServiceOrderStatus(ServiceOrderStatus serviceOrderStatus) {
        this.serviceOrderStatus = serviceOrderStatus;
    }


    // Getters and setters
    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svo_id", nullable = false)
    public int getSvoId() {
        return svoId;
    }

    public void setSvoId(int svoId) {
        this.svoId = svoId;
    }

    @Basic
    @Column(name = "date_requested", nullable = true)
    public Date getDateRequested() {
        return dateRequested;
    }

    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    @Basic
    @Column(name = "date_scheduled", nullable = true)
    public Timestamp getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Timestamp dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    @Basic
    @Column(name = "date_started", nullable = true)
    public Timestamp getDateStarted() {
        return dateStarted;
    }

    public void setDateStarted(Timestamp dateStarted) {
        this.dateStarted = dateStarted;
    }

    @Basic
    @Column(name = "date_finished", nullable = true)
    public Timestamp getDateFinished() {
        return dateFinished;
    }

    public void setDateFinished(Timestamp dateFinished) {
        this.dateFinished = dateFinished;
    }

    @Basic
    @Column(name = "work_request", nullable = false, length = 5000)
    public String getWorkRequest() {
        return workRequest;
    }

    public void setWorkRequest(String workRequest) {
        this.workRequest = workRequest;
    }

    @Basic
    @Column(name = "work_summary", nullable = true, length = 5000)
    public String getWorkSummary() {
        return workSummary;
    }

    public void setWorkSummary(String workSummary) {
        this.workSummary = workSummary;
    }

    @Basic
    @Column(name = "total", nullable = true, precision = 2)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrder that = (ServiceOrder) o;
        return svoId == that.svoId &&
                Objects.equals(dateRequested, that.dateRequested) &&
                Objects.equals(dateScheduled, that.dateScheduled) &&
                Objects.equals(dateStarted, that.dateStarted) &&
                Objects.equals(dateFinished, that.dateFinished) &&
                Objects.equals(workRequest, that.workRequest) &&
                Objects.equals(workSummary, that.workSummary) &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(svoId, dateRequested, dateScheduled, dateStarted, dateFinished, workRequest, workSummary, total);
    }
}
