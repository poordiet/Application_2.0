package com.example.demo.Models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class KnowledgeBaseEntry {
    private int kbId;
    private String kbTitle;
    private String kbSources;
    private String kbContent;

    @Id
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    @Column(name = "kb_id", nullable = false)
    public int getKbId() {
        return kbId;
    }

    public void setKbId(int kbId) {
        this.kbId = kbId;
    }

    @Basic
    @Column(name = "kb_title", nullable = false, length = 400)
    public String getKbTitle() {
        return kbTitle;
    }

    public void setKbTitle(String kbTitle) {
        this.kbTitle = kbTitle;
    }

    @Basic
    @Column(name = "kb_sources", nullable = true, length = 500)
    public String getKbSources() {
        return kbSources;
    }

    public void setKbSources(String kbSources) {
        this.kbSources = kbSources;
    }

    @Basic
    @Column(name = "kb_content", nullable = true, length = 5000)
    public String getKbContent() {
        return kbContent;
    }

    public void setKbContent(String kbContent) {
        this.kbContent = kbContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KnowledgeBaseEntry that = (KnowledgeBaseEntry) o;
        return kbId == that.kbId &&
                Objects.equals(kbTitle, that.kbTitle) &&
                Objects.equals(kbSources, that.kbSources) &&
                Objects.equals(kbContent, that.kbContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kbId, kbTitle, kbSources, kbContent);
    }
}
