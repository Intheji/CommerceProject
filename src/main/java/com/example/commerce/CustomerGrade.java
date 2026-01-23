package com.example.commerce;

// 고객 등급
public enum CustomerGrade {

    BRONZE(0, "브론즈"),
    SLIVER(5, "실버"),
    GOLD(10, "골드"),
    PLATINUM(15, "플래티넘");

    private final  int discountPercentage;
    private final String description;

    CustomerGrade(int discountPercentage, String description) {
        this.discountPercentage = discountPercentage;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public long calculateDiscount(long amount) {
        return amount * discountPercentage / 100;
    }
}
