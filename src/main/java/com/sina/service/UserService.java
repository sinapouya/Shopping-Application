package com.sina.service;

import com.sina.entity.Category;
import com.sina.payload.request.CategoryRequest;
import com.sina.repository.CategoryRepository;
import com.sina.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Boolean isUserExist(String name){
        return userRepository.existsByUsername(name);
    }

}
