package com.example.demo.user;

import com.example.demo.recipe.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServie {

    private final BookmarkRepository bookmarkRepository;


    public List<Bookmark> findAllByUser(User user){
        List<Bookmark> bookmarkList = bookmarkRepository.findAllByUser(user);
        return bookmarkList;
    }

    public boolean existByUserAndRecipe(User user, Recipe recipe){
        boolean flag = bookmarkRepository.existsByUserAndRecipe(user,recipe);
        return flag;
    }

    public void saveBookmark(User user, Recipe recipe){
        Bookmark bookmark = new Bookmark(user, recipe);
        bookmarkRepository.save(bookmark);
    }

    public void deleteByUserAndRecipe(User user, Recipe recipe){
        bookmarkRepository.deleteByUserAndRecipe(user, recipe);
    }
}
