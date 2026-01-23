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
        clothingProducts.add(new Product("롱패딩", 300000, "겨울 필수 김밥",15));
        clothingProducts.add(new Product("후드티", 59000, "질 좋은 후드티!",50));
        clothingProducts.add(new Product("청바지", 79000, "기모가 들어있는 따뜻한 청바지",25));
        clothingProducts.add(new Product("운동화", 159000, "아주 편한 운동화!",30));


        // 식품 카테고리용 상품 리스트 만들기
        List<Product> foodProducts = new ArrayList<>();
        foodProducts.add(new Product("치킨", 21000, "반반치킨", 100));
        foodProducts.add(new Product("라면", 4500, "틈새라면! 매운데 맛있어~", 300));
        foodProducts.add(new Product("삼겹살", 15000, "싸다 싸 삼겹살", 30));
        foodProducts.add(new Product("우유", 2900, "저지방 우유", 140));


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
