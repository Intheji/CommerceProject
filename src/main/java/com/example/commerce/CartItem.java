package com.example.commerce;

public class CartItem {

    private Product product;
    private int quantity;

    // 상품과 수량 받아서 저장
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // 수량을 더해줌
    public void addQuantity(int quantity) {
        this.quantity += quantity;
    }
}
