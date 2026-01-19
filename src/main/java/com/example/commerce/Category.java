package com.example.commerce;

import java.util.List;

public class Category {

    // 카테고리 이름
    private String name;

    // 이 카테고리에 속한 상품 목록
    private List<Product> products;

    // 생성자: 카테고리 이름과 상품 목록 받아서 저장
    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Product> getProducts() {
        return products;
    }

}
