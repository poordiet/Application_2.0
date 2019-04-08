package com.example.demo;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class ServiceOrderPhoto {
    private int svoPhotoId;
    private String svoPhotoTitle;
    private byte[] svoPhoto;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "svo_photo_id", nullable = false)
    public int getSvoPhotoId() {
        return svoPhotoId;
    }

    public void setSvoPhotoId(int svoPhotoId) {
        this.svoPhotoId = svoPhotoId;
    }

    @Basic
    @Column(name = "svo_photo_title", nullable = false, length = 150)
    public String getSvoPhotoTitle() {
        return svoPhotoTitle;
    }

    public void setSvoPhotoTitle(String svoPhotoTitle) {
        this.svoPhotoTitle = svoPhotoTitle;
    }

    @Basic
    @Column(name = "svo_photo", nullable = false)
    public byte[] getSvoPhoto() {
        return svoPhoto;
    }

    public void setSvoPhoto(byte[] svoPhoto) {
        this.svoPhoto = svoPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceOrderPhoto that = (ServiceOrderPhoto) o;
        return svoPhotoId == that.svoPhotoId &&
                Objects.equals(svoPhotoTitle, that.svoPhotoTitle) &&
                Arrays.equals(svoPhoto, that.svoPhoto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(svoPhotoId, svoPhotoTitle);
        result = 31 * result + Arrays.hashCode(svoPhoto);
        return result;
    }
}
