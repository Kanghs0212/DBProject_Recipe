package com.example.demo.recipe;

import com.example.demo.ingredient.Ingredient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "recipe")
public class Recipe {

    // recipe id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;

    // 요리 이름
    @Column(nullable = false)
    private String recipeName;

    // 간단한 설명
    private String description;

    // 영양정보
    private Double calories;

    // Ingredient 테이블의 외래키 miid
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "miid", nullable = false)
    private Ingredient ingredient;

    // 레시피 단계 테이블과 일대다 매핑, 외래키는 recipe_step에 존재
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RecipeStep> recipeSteps;

    // toString
    @Override
    public String toString() {
        return "Recipe{id=" + rid + ", name : " + recipeName + "}";
    }

}
