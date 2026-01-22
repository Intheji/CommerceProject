package com.example.commerce;

import java.util.ArrayList;

public class Cart {

    // 장바구니에 담긴 것들
    private ArrayList<CartItem> items = new ArrayList<>();

    // 장바구니에 상품 추가
    public void addItem(Product product, int quantity) {

        // 같은 상품이면 수량 더함
        for (CartItem item : items) {
            if (item.getProduct() == product) {
                item.addQuantity(quantity);
                return;
            }
        }

        items.add(new CartItem(product, quantity));
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void printCart() {
        for (CartItem item : items) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();

            System.out.println(product.getName() + " | " + product.getPrice() + "원"
                    + " | " + product.getDescription()
                    + " | 수량: " + quantity + "개 장바구니에 추가되었습니다." );
        }
    }

    public ArrayList<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public void clear() {
        items.clear();
    }
}
