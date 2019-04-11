package com.example.demo.Presentation;

import com.example.demo.Models.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ServiceOrderPresentation {

    // Service Order
    private int svoId;
    private Date dateRequested;
    private Timestamp dateScheduled;
    private Timestamp dateStarted;
    private Timestamp dateFinished;
    private String workRequest;
    private String workSummary;
    private BigDecimal total;

    private Set<ServiceOrderLine> serviceOrderLines;

    public Set<ServiceOrderLine> getServiceOrderLines() {
        return serviceOrderLines;
    }

    public void setServiceOrderLines(Set<ServiceOrderLine> serviceOrderLines) {
        this.serviceOrderLines = serviceOrderLines;
    }


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
    public int hashCode() {
        return Objects.hash(svoId, dateRequested, dateScheduled, dateStarted, dateFinished, workRequest, workSummary, total);
    }


    // Service Order Lines
    private int svoLineId;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svo_line_id", nullable = false)
    public int getSvoLineId() {
        return svoLineId;
    }

    public void setSvoLineId(int svoLineId) {
        this.svoLineId = svoLineId;
    }



    // Service
    private int svcId;
    private String svcName;

    private List<Svc> svcs;


    public List<Svc> getSvcs() {
        return svcs;
    }

    public void setSvcs(List<Svc> svcs) {
        this.svcs = svcs;
    }

    public void addSvc(Svc svc){
        svcs.add(svc);
    }

    @Id
    @Column(name = "svc_id", nullable = false)
    public int getSvcId() {
        return svcId;
    }

    public void setSvcId(int svcId) {
        this.svcId = svcId;
    }

    @Basic
    @Column(name = "svc_name", nullable = false, length = 150)
    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName;
    }

    // Customer
    private int custSiteId;
    private String custSiteNumber;
    private String custSiteName;
    private String custSiteAddress;
    private String custSiteCity;
    private String custSiteZip;
    private String custSitePhone;
    private String custSiteEmail;
    private Date custSiteStart;

    private String custSiteLocation;

    public String getCustSiteLocation() {
        return custSiteLocation;
    }

    public void setCustSiteLocation(String custSiteLocation) {
        this.custSiteLocation = custSiteLocation;
    }

    private Set<ServiceOrder> serviceOrders;

    public Set<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(Set<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

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


    // Contact

    private int contactId;
    private String contactFname;
    private String contactLname;
    private String contactPhone;
    private String contactEmail;
    private String contactName;


    public void setContactName(String contactName){
        this.contactName = contactName;
    }

    public String getContactName(){
        return contactName;
    }


    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contact_id", nullable = false)
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "contact_fname", nullable = false, length = 50)
    public String getContactFname() {
        return contactFname;
    }

    public void setContactFname(String contactFname) {
        this.contactFname = contactFname;
    }

    @Basic
    @Column(name = "contact_lname", nullable = false, length = 50)
    public String getContactLname() {
        return contactLname;
    }

    public void setContactLname(String contactLname) {
        this.contactLname = contactLname;
    }

    @Basic
    @Column(name = "contact_phone", nullable = true, length = 30)
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    @Basic
    @Column(name = "contact_email", nullable = true, length = 50)
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    // Service Order Status

    private int svoStatusId;
    private String svoStatus;
    private String svoStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svo_status_id", nullable = false)
    public int getSvoStatusId() {
        return svoStatusId;
    }

    public void setSvoStatusId(int svoStatusId) {
        this.svoStatusId = svoStatusId;
    }

    @Basic
    @Column(name = "svo_status", nullable = false, length = 50)
    public String getSvoStatus() {
        return svoStatus;
    }

    public void setSvoStatus(String svoStatus) {
        this.svoStatus = svoStatus;
    }

    @Basic
    @Column(name = "svo_status_desc", nullable = false, length = 250)
    public String getSvoStatusDesc() {
        return svoStatusDesc;
    }

    public void setSvoStatusDesc(String svoStatusDesc) {
        this.svoStatusDesc = svoStatusDesc;
    }


    // Assignment
    private int asgmtId;
    private Date asgmtDate;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "asgmt_id", nullable = false)
    public int getAsgmtId() {
        return asgmtId;
    }

    public void setAsgmtId(int asgmtId) {
        this.asgmtId = asgmtId;
    }

    @Basic
    @Column(name = "asgmt_date", nullable = false)
    public Date getAsgmtDate() {
        return asgmtDate;
    }

    public void setAsgmtDate(Date asgmtDate) {
        this.asgmtDate = asgmtDate;
    }


    // Contractor
    private int contractorId;
    private String contractorFname;
    private String contractorLname;
    private Date contractorHireDate;
    private Date contractorLeaveDate;
    private String contractorAddress;
    private String contractorCity;
    private String contractorZip;
    private String contractorPhone;
    private String contractorEmail;
    private String contractorUsername;
    private String contractorPassword;
    private String contractorAvailability;
    private String contractorName;

    private String contractors;

    public String getContractors() {
        return contractors;
    }

    public void setContractors(String contractors) {
        this.contractors = contractors;
    }


    public String getContractorName() {
        return contractorName;
    }

    public void setContractorName(String contractorName) {
        this.contractorName = contractorName;
    }

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contractor_id", nullable = false)
    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    @Basic
    @Column(name = "contractor_fname", nullable = false, length = 24)
    public String getContractorFname() {
        return contractorFname;
    }

    public void setContractorFname(String contractorFname) {
        this.contractorFname = contractorFname;
    }

    @Basic
    @Column(name = "contractor_lname", nullable = false, length = 24)
    public String getContractorLname() {
        return contractorLname;
    }

    public void setContractorLname(String contractorLname) {
        this.contractorLname = contractorLname;
    }

    @Basic
    @Column(name = "contractor_hire_date", nullable = false)
    public Date getContractorHireDate() {
        return contractorHireDate;
    }

    public void setContractorHireDate(Date contractorHireDate) {
        this.contractorHireDate = contractorHireDate;
    }

    @Basic
    @Column(name = "contractor_leave_date", nullable = true)
    public Date getContractorLeaveDate() {
        return contractorLeaveDate;
    }

    public void setContractorLeaveDate(Date contractorLeaveDate) {
        this.contractorLeaveDate = contractorLeaveDate;
    }

    @Basic
    @Column(name = "contractor_address", nullable = false, length = 50)
    public String getContractorAddress() {
        return contractorAddress;
    }

    public void setContractorAddress(String contractorAddress) {
        this.contractorAddress = contractorAddress;
    }

    @Basic
    @Column(name = "contractor_city", nullable = false, length = 50)
    public String getContractorCity() {
        return contractorCity;
    }

    public void setContractorCity(String contractorCity) {
        this.contractorCity = contractorCity;
    }

    @Basic
    @Column(name = "contractor_zip", nullable = false, length = 15)
    public String getContractorZip() {
        return contractorZip;
    }

    public void setContractorZip(String contractorZip) {
        this.contractorZip = contractorZip;
    }

    @Basic
    @Column(name = "contractor_phone", nullable = false, length = 25)
    public String getContractorPhone() {
        return contractorPhone;
    }

    public void setContractorPhone(String contractorPhone) {
        this.contractorPhone = contractorPhone;
    }

    @Basic
    @Column(name = "contractor_email", nullable = true, length = 50)
    public String getContractorEmail() {
        return contractorEmail;
    }

    public void setContractorEmail(String contractorEmail) {
        this.contractorEmail = contractorEmail;
    }

    @Basic
    @Column(name = "contractor_username", nullable = true, length = 50)
    public String getContractorUsername() {
        return contractorUsername;
    }

    public void setContractorUsername(String contractorUsername) {
        this.contractorUsername = contractorUsername;
    }

    @Basic
    @Column(name = "contractor_password", nullable = true, length = 50)
    public String getContractorPassword() {
        return contractorPassword;
    }

    public void setContractorPassword(String contractorPassword) {
        this.contractorPassword = contractorPassword;
    }

    @Basic
    @Column(name = "contractor_availability", nullable = true, length = 150)
    public String getContractorAvailability() {
        return contractorAvailability;
    }

    public void setContractorAvailability(String contractorAvailability) {
        this.contractorAvailability = contractorAvailability;
    }

    //Country

    private int countryId;
    private String countryName;

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

    // State

    private int stateId;
    private String stateName;

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

}
