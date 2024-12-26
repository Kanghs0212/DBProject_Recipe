package com.example.demo.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeStepService {

    private final RecipeStepRepository recipeStepRepository;

    public List<RecipeStep> findByRecipeRid(Long id){
        List<RecipeStep> recipeSteps = recipeStepRepository.findByRecipeRid(id);
        return recipeSteps;
    }
}
