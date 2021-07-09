package com.sina.service;

import com.sina.entity.Category;
import com.sina.payload.request.CategoryRequest;
import com.sina.payload.response.CategoryResponse;
import com.sina.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category createCategory(CategoryRequest categoryRequest){
        Category category = new Category(categoryRequest.getName());
        Category save = categoryRepository.save(category);
        return save;
    }
    public Boolean isCategoryExist(String name){
        return categoryRepository.existsByName(name);
    }

}
