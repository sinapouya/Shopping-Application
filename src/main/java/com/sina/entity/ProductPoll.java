package com.sina.entity;

import com.sina.enums.ProductRankConverter;
import com.sina.enums.ProductRankEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "poll")
@Data
@ToString
@EqualsAndHashCode
public class ProductPoll implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert( converter = ProductRankConverter.class)
    private ProductRankEnum rate;

    @Column(name = "comment",length = 200)
    private String comment;

    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Product product;

    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    public ProductPoll() {
    }

    public ProductPoll(String comment, Product product, User user) {
        this.comment = comment;
        this.product = product;
        this.user = user;
    }

    public ProductPoll(ProductRankEnum rate, String comment) {
        this.rate = rate;
        this.comment = comment;
    }

    public ProductPoll(ProductRankEnum rate, String comment, Product product, User user) {
        this.rate = rate;
        this.comment = comment;
        this.product = product;
        this.user = user;
    }
}
