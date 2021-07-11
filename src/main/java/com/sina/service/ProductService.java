package com.sina.service;

import com.google.common.base.Joiner;
import com.sina.entity.Category;
import com.sina.entity.Product;
import com.sina.payload.request.CategoryRequest;
import com.sina.payload.request.ProductRequest;
import com.sina.repository.CategoryRepository;
import com.sina.repository.ProductRepository;
import com.sina.repository.ProductSpecificationsBuilder;
import com.sina.util.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product createProduct(ProductRequest productRequest){
        Category category = categoryRepository.findByName(productRequest.getCategoryName());
        Product product = new Product(productRequest.getName(),productRequest.getPrice(),category);
        return productRepository.save(product);
    }
    public Product editProduct(ProductRequest productRequest){
        Category category = categoryRepository.findByName(productRequest.getCategoryName());
        Product product = new Product(productRequest.getName(),productRequest.getPrice(),category);
        return productRepository.save(product);
    }
    public void deleteProduct(String productName){
        Product product = productRepository.findByName(productName);
        productRepository.delete(product);
    }

    public Boolean isProductExist(String name){
        return productRepository.existsByName(name);
    }
    public Boolean isProductExist(Long id){
        return productRepository.existsById(id);
    }

    public List<Product> serachProduct(String searchCriteria){
        ProductSpecificationsBuilder builder = new ProductSpecificationsBuilder();
        if (searchCriteria != null) {
            String operationSetExper = Joiner.on("|")
                    .join(SearchOperation.SIMPLE_OPERATION_SET);
            Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
            Matcher matcher = pattern.matcher(searchCriteria + ",");
            while (matcher.find()) {
                builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
            }
            Specification<Product> spec = builder.build();
            return productRepository.findAll(spec);
        }
        return new ArrayList<Product>();
    }


}
