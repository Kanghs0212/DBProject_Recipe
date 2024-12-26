package com.example.demo.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User {

    // id
    @Id
    @Column(nullable = false, unique = true)
    private String id;

    // 비밀번호
    @Column(nullable = false)
    private String password;

    // 전화번호
    @Column(nullable = false)
    private String phoneNumber;

    // 북마크와는 다대다 관계 매핑
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Override
    public String toString() {
        return "Userid{id=" + id + "}";
    }
}
