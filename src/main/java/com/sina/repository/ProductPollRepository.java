package com.sina.repository;

import com.sina.entity.ProductPoll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPollRepository extends JpaRepository<ProductPoll,Long> {
}
