package com.sina.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ProductRankConverter implements AttributeConverter<ProductRankEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ProductRankEnum e) {

        return e != null ? e.toValue() : ProductRankEnum.UNKNOWN.toValue();
    }

    @Override
    public ProductRankEnum convertToEntityAttribute(Integer i) {
        return ProductRankEnum.fromValue(i);
    }
}
