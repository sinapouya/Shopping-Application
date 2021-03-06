package com.sina.payload.request;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class ProductRequest {
    Long productId;

    @NotEmpty
    @Size(max = 20)
    private String name;

    private Double price;

    @NotEmpty
    @Size(max = 20)
    private String categoryName;
}
