package com.example.demo.user;


import com.example.demo.recipe.Recipe;
import com.example.demo.recipe.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class BookmarkController {

    private final UserService userService;
    private final RecipeService recipeService;
    private final BookmarkService bookmarkService;


    // 유저의 북마크 목록 조회
    @GetMapping("/mymenu")
    public String mymenu(Model model, Authentication auth) {
        Optional<User> result = userService.findById(auth.getName());
        User user = result.get();

        List<Bookmark> bookmarkList = bookmarkService.findAllByUser(user);

        List<Recipe> recipeList = new ArrayList<>();
        for (int i = 0; i < bookmarkList.size(); i++) {
            Optional<Recipe> recipeResult = recipeService.findById(bookmarkList.get(i).getId().getRid());
            if(recipeResult.isPresent()){
                Recipe recipe = recipeResult.get();
                recipeList.add(recipe);
            }
        }

        model.addAttribute("recipes", recipeList);
        return "mymenu.html";
    }

    @GetMapping("/addBookmark/{rid}")
    public String addBookmark(@PathVariable Long rid, Authentication auth){

        Optional<Recipe> recipeResult = recipeService.findById(rid);
        Optional<User> userResult = userService.findById(auth.getName());

        if(recipeResult.isPresent() && userResult.isPresent()){
            User user = userResult.get();
            Recipe recipe = recipeResult.get();

            if(!bookmarkService.existByUserAndRecipe(user,recipe)){
                bookmarkService.saveBookmark(user, recipe);
            }
        }

        return "redirect:/mymenu";
    }

    @DeleteMapping("/deleteBookmark/{rid}")
    @Transactional
    public ResponseEntity<Void> deleteBookmark(@PathVariable Long rid, Authentication auth) {
        Optional<Recipe> recipeResult = recipeService.findById(rid);
        Optional<User> userResult = userService.findById(auth.getName());

        if (recipeResult.isPresent() && userResult.isPresent()) {
            User user = userResult.get();
            Recipe recipe = recipeResult.get();

            bookmarkService.deleteByUserAndRecipe(user,recipe);
        }
        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }

}

