package com.example.demo.recipe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeStepRepository extends JpaRepository<RecipeStep, Long> {
    // rid를 통해 레시피 단계 테이블들을 가져온다.
    List<RecipeStep> findByRecipeRid(Long rid);

}
