package com.sina.controllers;

import com.sina.entity.Category;
import com.sina.entity.Product;
import com.sina.payload.request.CategoryRequest;
import com.sina.payload.request.ProductRequest;
import com.sina.payload.response.CategoryResponse;
import com.sina.payload.response.MessageResponse;
import com.sina.payload.response.ProductResponse;
import com.sina.service.CategoryService;
import com.sina.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@Validated
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryService categoryService;
    @PostMapping("/admin/product/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> insertProduct(@Valid @RequestBody ProductRequest productRequest){
        if (productService.isProductExist(productRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: product name is already taken!"));
        }
        if (!categoryService.isCategoryExist(productRequest.getCategoryName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: category name is not exist!"));
        }
        Product product = productService.createProduct(productRequest);
        return ResponseEntity.ok(new ProductResponse(product.getName(), product.getPrice()));
    }
    @GetMapping(value = "/search/product")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> findAllBySpecification(@Valid @NotBlank @RequestParam(value = "search") String search) {
        return ResponseEntity.ok(productService.serachProduct(search));
    }
}
