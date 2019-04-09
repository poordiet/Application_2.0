package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContactStatus {
    private int contactStatusId;
    private String contactStatus;
    private String contactStatusDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contact_status_id", nullable = false)
    public int getContactStatusId() {
        return contactStatusId;
    }

    public void setContactStatusId(int contactStatusId) {
        this.contactStatusId = contactStatusId;
    }

    @Basic
    @Column(name = "contact_status", nullable = false, length = 50)
    public String getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(String contactStatus) {
        this.contactStatus = contactStatus;
    }

    @Basic
    @Column(name = "contact_status_desc", nullable = false, length = 250)
    public String getContactStatusDesc() {
        return contactStatusDesc;
    }

    public void setContactStatusDesc(String contactStatusDesc) {
        this.contactStatusDesc = contactStatusDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactStatus that = (ContactStatus) o;
        return contactStatusId == that.contactStatusId &&
                Objects.equals(contactStatus, that.contactStatus) &&
                Objects.equals(contactStatusDesc, that.contactStatusDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactStatusId, contactStatus, contactStatusDesc);
    }
}
