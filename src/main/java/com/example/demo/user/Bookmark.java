package com.example.demo.user;

import com.example.demo.recipe.Recipe;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "bookmark")
public class Bookmark {

    // 북마크 테이블의 EmbeddedId인 id
    // 해당 테이블은 유저와 요리간 중간 테이블로써, rid와 유저테이블의 id를 복합키이자 외래키로 사용한다.
    @EmbeddedId
    private BookmarkId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rid") // 복합 키의 rid 부분 매핑
    @JoinColumn(name = "rid", nullable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("id") // 복합 키의 id 부분 매핑
    @JoinColumn(name = "id", nullable = false)
    private User user;

    // Constructors (기본 생성자 포함)
    public Bookmark() {}

    // 키 설정 메소드
    public Bookmark(User user, Recipe recipe) {
        this.user = user;
        this.recipe = recipe;
        this.id = new BookmarkId(user.getId(), recipe.getRid());
    }

    @Override
    public String toString() {
        return "bookmark{id=" + id + "}";
    }
}