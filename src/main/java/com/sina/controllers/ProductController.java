package com.sina.controllers;

import com.sina.entity.Category;
import com.sina.entity.Product;
import com.sina.entity.ProductPoll;
import com.sina.payload.request.CategoryRequest;
import com.sina.payload.request.ProductPollRequest;
import com.sina.payload.request.ProductRequest;
import com.sina.payload.response.CategoryResponse;
import com.sina.payload.response.MessageResponse;
import com.sina.payload.response.ProductPollResponse;
import com.sina.payload.response.ProductResponse;
import com.sina.service.CategoryService;
import com.sina.service.ProductPollService;
import com.sina.service.ProductService;
import com.sina.service.UserService;
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
    ProductPollService productPollService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

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
        return ResponseEntity.ok(new ProductResponse(product.getName(), product.getPrice(),product.getCategory().getName()));
    }
    @PostMapping("/admin/product/edit")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editProduct(@Valid @RequestBody ProductRequest productRequest){
        if (!productService.isProductExist(productRequest.getProductId())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: product does not exist!"));
        }
        if (!categoryService.isCategoryExist(productRequest.getCategoryName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: category name is not exist!"));
        }
        Product product = productService.editProduct(productRequest);
        return ResponseEntity.ok(new ProductResponse(product.getName(), product.getPrice(),product.getCategory().getName()));
    }
    @PostMapping("/admin/product/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@Valid @RequestBody String name){
        if (!productService.isProductExist(name)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: product does not exist!"));
        }
        productService.deleteProduct(name);
        return ResponseEntity.ok("product deleted");
    }
    @GetMapping(value = "/search/product")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Product>> findAllBySpecification(@Valid @NotBlank @RequestParam(value = "search") String search) {
        return ResponseEntity.ok(productService.serachProduct(search));
    }
    @PostMapping("/productpoll/create")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> insertProductPoll(@Valid @RequestBody ProductPollRequest productPollRequest){
        if (!productService.isProductExist(productPollRequest.getProductName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: product name is not exist!"));
        }
        if (!userService.isUserExist(productPollRequest.getUserName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: user name is not exist!"));
        }
        ProductPoll productPoll = productPollService.createProductPoll(productPollRequest);
        return ResponseEntity.ok(new ProductPollResponse(productPoll.getComment(),
                productPoll.getUser().getUsername(),
                productPoll.getProduct().getName(),
                productPollRequest.getRate()));
    }
}
