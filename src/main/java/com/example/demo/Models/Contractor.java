package com.example.demo.Models;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
public class Contractor {
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

    // Country
    private Country country;

    @ManyToOne
    @JoinColumn(name="country_id")
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    // M:1 with State
    private StateProvince stateProvince;

    @ManyToOne
    @JoinColumn(name="state_id")
    public StateProvince getStateProvince() {
        return stateProvince;
    }

    public void setStateProvince(StateProvince stateProvince) {
        this.stateProvince = stateProvince;
    }

    // 1:M with Assignment
    private Set<Assignment> assignments;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contractor")
    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        assignments.forEach(assignment -> assignment.setContractor(this));
        this.assignments = assignments;
    }

    // M:1 with ContractorType
    private ContractorType contractorType;

    @ManyToOne
    @JoinColumn(name="contractor_type_id")
    public ContractorType getContractorType(){return contractorType;}

    public void setContractorType(ContractorType contractorType) {
        this.contractorType = contractorType;
    }

    // M:1 with ContractorStatus
    private ContractorStatus contractorStatus;

    @ManyToOne
    @JoinColumn(name="contractor_status_id")
    public ContractorStatus getContractorStatus(){return contractorStatus;}

    public void setContractorStatus(ContractorStatus contractorStatus) {
        this.contractorStatus = contractorStatus;
    }


    // 1:M with Contractor Skill

    private Set<ContractorSkill> contractorSkills;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "contractor")
    public Set<ContractorSkill> getContractorSkills() {
        return contractorSkills;
    }

    public void setContractorSkills(Set<ContractorSkill> contractorSkills) {

        this.contractorSkills = contractorSkills;
    }

    //M:1 with Access Level
    private AccessLevel accessLevel;
    @ManyToOne
    @JoinColumn(name="acc_level_id")
    public AccessLevel getAccessLevel(){return accessLevel;}

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contractor that = (Contractor) o;
        return contractorId == that.contractorId &&
                Objects.equals(contractorFname, that.contractorFname) &&
                Objects.equals(contractorLname, that.contractorLname) &&
                Objects.equals(contractorHireDate, that.contractorHireDate) &&
                Objects.equals(contractorLeaveDate, that.contractorLeaveDate) &&
                Objects.equals(contractorAddress, that.contractorAddress) &&
                Objects.equals(contractorCity, that.contractorCity) &&
                Objects.equals(contractorZip, that.contractorZip) &&
                Objects.equals(contractorPhone, that.contractorPhone) &&
                Objects.equals(contractorEmail, that.contractorEmail) &&
                Objects.equals(contractorUsername, that.contractorUsername) &&
                Objects.equals(contractorPassword, that.contractorPassword) &&
                Objects.equals(contractorAvailability, that.contractorAvailability);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractorId, contractorFname, contractorLname, contractorHireDate, contractorLeaveDate, contractorAddress, contractorCity, contractorZip, contractorPhone, contractorEmail, contractorUsername, contractorPassword, contractorAvailability);
    }


}
