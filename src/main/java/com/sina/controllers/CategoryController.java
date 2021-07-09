package com.sina.controllers;

import com.sina.entity.Category;
import com.sina.payload.request.CategoryRequest;
import com.sina.payload.response.CategoryResponse;
import com.sina.payload.response.JwtResponse;
import com.sina.payload.response.MessageResponse;
import com.sina.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/category")
@Validated
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        if (categoryService.isCategoryExist(categoryRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Category name is already taken!"));
        }
        Category category = categoryService.createCategory(categoryRequest);
        return ResponseEntity.ok(new CategoryResponse(category.getName()));
    }
}
