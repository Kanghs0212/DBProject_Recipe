package com.example.demo.recipe;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "recipe_step")
public class RecipeStep {

    // recipe_step id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rsid;

    // 단계 번호
    @Column(nullable = false)
    private Integer stepNumber;

    // 설명
    private String description;

    // 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rid", nullable = false)
    private Recipe recipe;

    // toString
    @Override
    public String toString() {
        return "RecipeStep{id=" + rsid + "}";
    }
}
