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

        // 동일한 걸 받았는지 확인하는 메서드
        products.add(product); // 원본 리스트에 추가
    }


    public String getName() {
        return name;
    }


    public List<Product> getProducts() {
        // 원본을 그대로 주지 않고, 원본을 복사한 "새 리스트"를 만들어서 준다 복사 시점의 데이터만 받게 됨
        // Collections.unmodifiableList(products) < 읽기 전용으로 불러오지만 리스트에 변화가 생겼을 때 즉각적으로 반영해 줌
        return new ArrayList<>(products);
    }

    public boolean hasProduct(String name) {

        if (name == null || name.isBlank()) {
            return false;
        }

        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }

        return false;
    }

    public Product findProduct(String name) {
        String keyword = name.trim();

        if (keyword.isEmpty()) {
            return null;
        }

        for (Product product : products) {

            if (product.getName().equalsIgnoreCase(keyword)) {
                return product;
            }
        }
        return null;
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

}
