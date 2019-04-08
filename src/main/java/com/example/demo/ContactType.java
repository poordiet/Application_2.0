package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ContactType {
    private int contactTypeId;
    private String contactType;
    private String contactTypeDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "contact_type_id", nullable = false)
    public int getContactTypeId() {
        return contactTypeId;
    }

    public void setContactTypeId(int contactTypeId) {
        this.contactTypeId = contactTypeId;
    }

    @Basic
    @Column(name = "contact_type", nullable = false, length = 25)
    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    @Basic
    @Column(name = "contact_type_desc", nullable = false, length = 250)
    public String getContactTypeDesc() {
        return contactTypeDesc;
    }

    public void setContactTypeDesc(String contactTypeDesc) {
        this.contactTypeDesc = contactTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactType that = (ContactType) o;
        return contactTypeId == that.contactTypeId &&
                Objects.equals(contactType, that.contactType) &&
                Objects.equals(contactTypeDesc, that.contactTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactTypeId, contactType, contactTypeDesc);
    }
}
