package com.sina.service;

import com.sina.entity.Product;
import com.sina.entity.ProductPoll;
import com.sina.entity.User;
import com.sina.payload.request.ProductPollRequest;
import com.sina.repository.ProductPollRepository;
import com.sina.repository.ProductRepository;
import com.sina.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductPollService {
    @Autowired
    private ProductPollRepository productPollRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductPoll createProductPoll(ProductPollRequest productPollRequest){
        Optional<User> user = userRepository.findByUsername(productPollRequest.getUserName());
        Product product = productRepository.findByName(productPollRequest.getProductName());
        ProductPoll poll = new ProductPoll(productPollRequest.getComment(),product,user.get());
        return productPollRepository.save(poll);
    }
}
