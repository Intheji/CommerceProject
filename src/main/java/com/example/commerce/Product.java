package com.example.commerce;

// 상품 1개의 정보를 담는 클래스(설계도)
public class Product {
    
    // 필드 - 이 객체가 가지고 있어야 하는 정보를 저장하는 변수
    String name;        // 상품명
    int price;          // 가격
    String descrption;  // 설명
    int stock;          // 재고 수량


    // 생성자
    public Product(String name, int price, String descrption, int stock) {
        this.name = name;
        this.price = price;
        this.descrption = descrption;
        this.stock = stock;
    }


}
