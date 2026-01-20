package com.example.commerce;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 전자제품 카테고리용 상품 리스트 만들기
        List<Product> electronicsProducts = new ArrayList<>();
        electronicsProducts.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50));
        electronicsProducts.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30));
        electronicsProducts.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 20));
        electronicsProducts.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 100));

        // 의류 카테고리용 상품 리스트 만들기
        List<Product> clothingProducts = new ArrayList<>();

        // 식품 카테고리용 상품 리스트 만들기
        List<Product> foodProducts = new ArrayList<>();

        // Category 객체 만듦
        Category electronics = new Category("전자제품", electronicsProducts);
        Category clothing = new Category("의류", clothingProducts);
        Category food = new Category("식품", foodProducts);

        List<Category> categories = new ArrayList<>();
        categories.add(electronics);
        categories.add(clothing);
        categories.add(food);


        // CommerceSystem에 categories customer 넘김
        Customer customer = new Customer("박현지", "park@test.com");

        CommerceSystem commerceSystem = new CommerceSystem(categories, customer);
        commerceSystem.start();

    }
}
