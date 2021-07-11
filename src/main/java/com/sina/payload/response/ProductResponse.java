package com.sina.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private Double price;
    private String categoryName;
}
