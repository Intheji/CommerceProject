package com.example.commerce;

import java.util.ArrayList;
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

    // 상품 1개를 카테고리에 추가하는 메서드
    public void addProduct(Product product) {

        if (products == null) {
            return;
        }

        products.add(product); // 원본 리스트에 추가
    }

    // 상품 1개를 카테고리에서 삭제하는 메서드
    public void removeProduct(Product product) {

        // null이면 아무 것도 하지 않음
        if (products == null) {
            return;
        }

        products.remove(product);   // 원본 리스트에서 제거
    }


    public String getName() {
        return name;
    }


    public List<Product> getProducts() {
        // 원본을 그대로 주지 않고, 원본을 복사한 "새 리스트"를 만들어서 준다
        return new ArrayList<>(products);
    }

}
