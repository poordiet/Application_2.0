package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class KnowledgeBaseCategory {
    private int kbCatId;
    private String kbCat;
    private String kbCatDesc;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "kb_cat_id", nullable = false)
    public int getKbCatId() {
        return kbCatId;
    }

    public void setKbCatId(int kbCatId) {
        this.kbCatId = kbCatId;
    }

    @Basic
    @Column(name = "kb_cat", nullable = false, length = 50)
    public String getKbCat() {
        return kbCat;
    }

    public void setKbCat(String kbCat) {
        this.kbCat = kbCat;
    }

    @Basic
    @Column(name = "kb_cat_desc", nullable = false, length = 250)
    public String getKbCatDesc() {
        return kbCatDesc;
    }

    public void setKbCatDesc(String kbCatDesc) {
        this.kbCatDesc = kbCatDesc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeBaseCategory that = (KnowledgeBaseCategory) o;
        return kbCatId == that.kbCatId &&
                Objects.equals(kbCat, that.kbCat) &&
                Objects.equals(kbCatDesc, that.kbCatDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kbCatId, kbCat, kbCatDesc);
    }
}
