package com.example.demo.user;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BookmarkId implements Serializable {

    @Column(name = "id", nullable = false)
    private String id; // User의 ID

    @Column(name = "rid", nullable = false)
    private Long rid; // Recipe의 ID

    // 기본 생성자
    public BookmarkId() {}

    // 매개변수 생성자
    public BookmarkId(String id, Long rid) {
        this.id = id;
        this.rid = rid;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookmarkId that = (BookmarkId) o;
        return Objects.equals(id, that.id) && Objects.equals(rid, that.rid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rid);
    }
}