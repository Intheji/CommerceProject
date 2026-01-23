package com.example.commerce;

import java.util.ArrayList;
import java.util.stream.Collectors;

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

    public ArrayList<CartItem> getItems() {
        return new ArrayList<>(items);
    }

    public void clear() {
        items.clear();
    }


    // 장바구니에서 찾아서 제거
    public boolean removeProduct(Product product) {

        if (product == null) {
            return false;
        }

        // removeIf 조건에 맞는 요소를 리스트에서 삭제
        return items.removeIf(item -> item.getProduct() == product);
    }

    public boolean removeCartItemName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        String keyword = name.toLowerCase();

        int before = items.size();

        items = items.stream()
                .filter(item -> !item.getProduct().getName(). toLowerCase().equals(keyword))
                .collect(Collectors.toCollection(ArrayList::new));

        int after = items.size();

        return after < before;
    }
}
