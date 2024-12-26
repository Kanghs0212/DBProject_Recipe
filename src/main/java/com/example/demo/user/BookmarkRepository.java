package com.example.demo.user;

import com.example.demo.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, BookmarkId> {
    // User ID를 기반으로 북마크를 검색
    List<Bookmark> findByIdId(String id);

    // Recipe ID를 기반으로 북마크를 검색
    List<Bookmark> findByIdRid(Long rid);
    // 유저와 레시피의 각 주키를 통해 해당 북마크가 존재하는지 리턴
    boolean existsByUserAndRecipe(User user, Recipe recipe);
    
    // 특정 유저가 북마크한 리스트를 리턴
    List<Bookmark> findAllByUser(User user);
    
    // 유저와 레시피에 해당하는 중간 테이블을 삭제
    void deleteByUserAndRecipe(User user, Recipe recipe);
}
