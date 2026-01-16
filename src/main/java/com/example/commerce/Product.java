package com.example.commerce;

// 상품 1개의 정보를 담는 클래스(설계도)
public class Product {
    
    // 필드 - 이 객체가 가지고 있어야 하는 정보를 저장하는 변수
    String name;        // 상품명
    int price;          // 가격
    String description;  // 설명
    int stock;          // 재고 수량


    // 생성자
    public Product(String name, int price, String description, int stock) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // 상품명을 외부에서 읽을 수 있게 해 주는 getter
    public String getName() {
        return name;
    }

    // 가격을 외부에서 읽을 수 있게 해 주는 getter
    public int getPrice() {
        return price;
    }

    // 설명을 외부에서 읽을 수 있게 해 주는 getter
    public String getDescription() {
        return description;
    }


}
