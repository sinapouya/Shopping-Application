package com.sina.enums;

public enum ProductRankEnum {
    UNKNOWN(0),
    oneStar(1),
    twoStar(2),
    threeStar(3),
    fourStar(4),
    fiveStar(5);

    private final int value;

    ProductRankEnum(int value) {
        this.value = value;
    }

    public static ProductRankEnum fromValue(Integer value) {
        if(value == null) {
            return UNKNOWN;
        }
        switch (value) {
            case 1:
                return ProductRankEnum.oneStar;
            case 2:
                return ProductRankEnum.twoStar;
            case 3:
                return ProductRankEnum.threeStar;
            case 4:
                return ProductRankEnum.fourStar;
            case 5:
                return ProductRankEnum.fiveStar;
            default:
                return ProductRankEnum.UNKNOWN;
        }
    }
    public static ProductRankEnum fromType(String type) {
        if (type.equals(oneStar.name())) {
            return oneStar;

        } else if (type.equals(twoStar.name())) {
            return twoStar;

        } else if (type.equals(threeStar.name())) {
            return threeStar;

        } else if (type.equals(fourStar.name())) {
            return fourStar;

        } else if (type.equals(fiveStar.name())) {
            return fiveStar;

        } else if (type.equals(UNKNOWN.name())) {
            return UNKNOWN;

        } else {
            return UNKNOWN;
        }
    }
    public Integer toValue() {
        return value;
    }

}
