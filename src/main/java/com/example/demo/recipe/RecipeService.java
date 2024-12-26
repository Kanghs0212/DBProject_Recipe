package com.example.demo.recipe;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public Optional<Recipe> findById(Long id){
        Optional<Recipe> recipeResult = recipeRepository.findById(id);
        return recipeResult;
    }

    public Page<Recipe> findPageBy(int start, int size){
        Page<Recipe> recipes = recipeRepository.findPageBy(PageRequest.of(start, size));
        return recipes;
    }

    public List<Recipe> findByNameContainingIgnoreCase(String keyword){
        List<Recipe> recipes = recipeRepository.findByNameContainingIgnoreCase(keyword);
        return recipes;
    }
}
