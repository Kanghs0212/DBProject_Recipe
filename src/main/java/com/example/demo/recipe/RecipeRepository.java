package com.example.demo.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    // 키워드로 검색하는 SELECT문
    @Query("SELECT r FROM Recipe r WHERE LOWER(r.recipeName) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Recipe> findByNameContainingIgnoreCase(@Param("keyword") String keyword);

    // page 개수 만큼 불러온다.
    Page<Recipe> findPageBy(Pageable page);
}
