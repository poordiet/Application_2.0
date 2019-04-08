package com.example.demo;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class PhotoType {
    private int photoTypeId;
    private String photoType;
    private String photoTypeDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "photo_type_id", nullable = false)
    public int getPhotoTypeId() {
        return photoTypeId;
    }

    public void setPhotoTypeId(int photoTypeId) {
        this.photoTypeId = photoTypeId;
    }

    @Basic
    @Column(name = "photo_type", nullable = false, length = 25)
    public String getPhotoType() {
        return photoType;
    }

    public void setPhotoType(String photoType) {
        this.photoType = photoType;
    }

    @Basic
    @Column(name = "photo_type_desc", nullable = false, length = 250)
    public String getPhotoTypeDesc() {
        return photoTypeDesc;
    }

    public void setPhotoTypeDesc(String photoTypeDesc) {
        this.photoTypeDesc = photoTypeDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoType photoType1 = (PhotoType) o;
        return photoTypeId == photoType1.photoTypeId &&
                Objects.equals(photoType, photoType1.photoType) &&
                Objects.equals(photoTypeDesc, photoType1.photoTypeDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(photoTypeId, photoType, photoTypeDesc);
    }
}
