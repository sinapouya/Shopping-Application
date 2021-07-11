package com.sina.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class ProductPollResponse {

    private String comment;

    private String userName;

    private String productName;

    private Integer rate;

}
