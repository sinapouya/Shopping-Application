package com.sina.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CategoryRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

}
