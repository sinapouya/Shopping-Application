package com.sina.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductPollRequest {
    @NotBlank
    @Size(min = 3, max = 200)
    private String comment;

    private String userName;

    private String productName;

    private Integer rate;

}
