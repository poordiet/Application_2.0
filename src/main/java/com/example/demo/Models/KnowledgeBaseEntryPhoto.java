package com.example.demo.Models;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class KnowledgeBaseEntryPhoto {
    private int kbPhotoId;
    private String kbPhotoTitle;
    private byte[] kbPhoto;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "kb_photo_id", nullable = false)
    public int getKbPhotoId() {
        return kbPhotoId;
    }

    public void setKbPhotoId(int kbPhotoId) {
        this.kbPhotoId = kbPhotoId;
    }

    @Basic
    @Column(name = "kb_photo_title", nullable = false, length = 150)
    public String getKbPhotoTitle() {
        return kbPhotoTitle;
    }

    public void setKbPhotoTitle(String kbPhotoTitle) {
        this.kbPhotoTitle = kbPhotoTitle;
    }

    @Basic
    @Column(name = "kb_photo", nullable = true)
    public byte[] getKbPhoto() {
        return kbPhoto;
    }

    public void setKbPhoto(byte[] kbPhoto) {
        this.kbPhoto = kbPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeBaseEntryPhoto that = (KnowledgeBaseEntryPhoto) o;
        return kbPhotoId == that.kbPhotoId &&
                Objects.equals(kbPhotoTitle, that.kbPhotoTitle) &&
                Arrays.equals(kbPhoto, that.kbPhoto);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(kbPhotoId, kbPhotoTitle);
        result = 31 * result + Arrays.hashCode(kbPhoto);
        return result;
    }
}
