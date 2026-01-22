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


    // 장바구니에서 찾아서 제거
    public boolean removeProduct(Product product) {

        if (product == null) {
            return false;
        }

//        boolean removed = false;

        // 역방향 for문: 리스트에서 삭제를 하면서 앞으로 가면 인덱스가 당겨져서 건너뛰는 문제가 생길 수 있다.....
//        for (int i = items.size() - 1; i >= 0; i--) {
//
//            CartItem item = items.get(i);
//
//            if (item.getProduct() == product) {
//                items.remove(i);
//                removed = true;
//            }
//        }

        // removeIf 조건에 맞는 요소를 리스트에서 삭제
        return items.removeIf(item -> item.getProduct() == product);
    }
}
