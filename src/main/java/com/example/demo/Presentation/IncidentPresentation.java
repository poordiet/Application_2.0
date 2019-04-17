package com.example.demo.Presentation;

import com.example.demo.Models.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

public class IncidentPresentation {

    private int incidentId;
    private String incident;
    private Date incidentDate;
    private int svoId;


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

    // Incident Status

    private int incidentStatusId;
    private String incidentStatus;
    private String incidentStatusDesc;

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


    // Incident Type

    private int incidentTypeId;
    private String incidentType;
    private String incidentTypeDesc;


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

    // Service Order

    private Date dateRequested;
    private Timestamp dateScheduled;
    private Timestamp dateStarted;
    private Timestamp dateFinished;
    private String workRequest;
    private String workSummary;
    private BigDecimal total;
    private int serviceOrderStatusId;


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


    // Set Customer Site

    private int custSiteId;
    private String custSiteNumber;
    private String custSiteName;
    private String custSiteAddress;
    private String custSiteCity;
    private String custSiteZip;
    private String custSitePhone;
    private String custSiteEmail;
    private Date custSiteStart;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "cust_site_id", nullable = false)
    public int getCustSiteId() {
        return custSiteId;
    }

    public void setCustSiteId(int custSiteId) {
        this.custSiteId = custSiteId;
    }

    @Basic
    @Column(name = "cust_site_number", nullable = true, length = 100)
    public String getCustSiteNumber() {
        return custSiteNumber;
    }

    public void setCustSiteNumber(String custSiteNumber) {
        this.custSiteNumber = custSiteNumber;
    }

    @Basic
    @Column(name = "cust_site_name", nullable = false, length = 50)
    public String getCustSiteName() {
        return custSiteName;
    }

    public void setCustSiteName(String custSiteName) {
        this.custSiteName = custSiteName;
    }

    @Basic
    @Column(name = "cust_site_address", nullable = false, length = 50)
    public String getCustSiteAddress() {
        return custSiteAddress;
    }

    public void setCustSiteAddress(String custSiteAddress) {
        this.custSiteAddress = custSiteAddress;
    }

    @Basic
    @Column(name = "cust_site_city", nullable = false, length = 50)
    public String getCustSiteCity() {
        return custSiteCity;
    }

    public void setCustSiteCity(String custSiteCity) {
        this.custSiteCity = custSiteCity;
    }

    @Basic
    @Column(name = "cust_site_zip", nullable = false, length = 15)
    public String getCustSiteZip() {
        return custSiteZip;
    }

    public void setCustSiteZip(String custSiteZip) {
        this.custSiteZip = custSiteZip;
    }

    @Basic
    @Column(name = "cust_site_phone", nullable = false, length = 20)
    public String getCustSitePhone() {
        return custSitePhone;
    }

    public void setCustSitePhone(String custSitePhone) {
        this.custSitePhone = custSitePhone;
    }

    @Basic
    @Column(name = "cust_site_email", nullable = true, length = 50)
    public String getCustSiteEmail() {
        return custSiteEmail;
    }

    public void setCustSiteEmail(String custSiteEmail) {
        this.custSiteEmail = custSiteEmail;
    }

    @Basic
    @Column(name = "cust_site_start", nullable = true)
    public Date getCustSiteStart() {
        return custSiteStart;
    }

    public void setCustSiteStart(Date custSiteStart) {
        this.custSiteStart = custSiteStart;
    }

}
