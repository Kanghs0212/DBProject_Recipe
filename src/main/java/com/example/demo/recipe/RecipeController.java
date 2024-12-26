package com.example.demo.recipe;


import com.example.demo.ingredient.Ingredient;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.Collections;

@Controller
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;
    private final RecipeStepService recipeStepService;

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable Long id) {
        Optional<Recipe> recipeResult = recipeService.findById(id);

        if (recipeResult==null) {
            Recipe recipe = recipeResult.get();

            // 해당 recipe에 대한 요리 단계들을 조회
            List<RecipeStep> recipeSteps = recipeStepService.findByRecipeRid(id);

            // Recipe와 RecipeStep을 모델에 추가
            model.addAttribute("recipe", recipe);
            model.addAttribute("recipeSteps", recipeSteps.isEmpty() ? Collections.emptyList() : recipeSteps);

            // 주재료 정보 추가 (주재료와 구매 링크)
            Ingredient ingredient = recipe.getIngredient(); // Recipe 객체에 있는 주재료 정보 가져오기
            if (ingredient != null && ingredient.getPurchaseLink() != null) {
                model.addAttribute("ingredientName", ingredient.getIngredientName()); // 재료명
                model.addAttribute("sellerName", ingredient.getPurchaseLink().getSeller()); // 판매자명
                model.addAttribute("purchaseLink", ingredient.getPurchaseLink().getLink()); // 구매 링크
            } else {
                model.addAttribute("ingredientName", null); // 재료명이 없을 경우 처리
                model.addAttribute("purchaseLink", null); // 구매 링크가 없을 경우 처리
            }

            return "detail.html"; // 레시피 상세 페이지로 이동
        } else {
            // 해당 recipe가 없으면 "메인화면"으로 리다이렉트
            return "redirect:/";
        }
    }

    // 구매 페이지로 이동
    @GetMapping("/detail/{id}/purchase")
    public String purchase(Model model, @PathVariable Long id) {
        Optional<Recipe> recipeResult = recipeService.findById(id);

        if (recipeResult.isPresent()) {
            Recipe recipe = recipeResult.get();

            // 해당 레시피의 주재료 정보 가져오기
            Ingredient ingredient = recipe.getIngredient();
            String ingredientName = ingredient != null ? ingredient.getIngredientName() : "정보 없음";
            String sellerName = ingredient != null && ingredient.getPurchaseLink() != null ? ingredient.getPurchaseLink().getSeller() : "정보 없음";
            String purchaseLink = ingredient != null && ingredient.getPurchaseLink() != null ? ingredient.getPurchaseLink().getLink() : "#"; // 링크가 없으면 '#'으로 표시

            // 구매 정보 모델에 추가
            model.addAttribute("ingredientName", ingredientName);
            model.addAttribute("seller", sellerName);
            model.addAttribute("purchaseLink", purchaseLink);

            return "purchase.html"; // 주재료 구매 페이지로 이동
        } else {
            return "redirect:/"; // 레시피가 없으면 메인화면으로 리다이렉트
        }
    }

    @GetMapping("/")
    public String index(Model model){
        
        // 메인화면
        Page<Recipe> recipes = recipeService.findPageBy(0,4);
        model.addAttribute("recipes", recipes);
        return "index.html";
    }

    @GetMapping("/search")
    public String search(@RequestParam("keyword") String keyword, Model model) {
        // 검색 키워드가 비어 있는 경우 빈 리스트를 반환
        if (keyword == null || keyword.trim().isEmpty()) {
            model.addAttribute("recipes", Collections.emptyList());
            model.addAttribute("keyword", keyword);
            return "search.html";
        }

        // 키워드로 레시피 검색
        List<Recipe> recipes = recipeService.findByNameContainingIgnoreCase(keyword);

        model.addAttribute("recipes", recipes);
        model.addAttribute("keyword", keyword);
        return "search.html";

    }
}
