package com.example.commerce;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ProductFilter {

    private String formatPrice(int price) {
        return String.format("%,d", price);
    }

    // 전달받은 상품 리스트를 출력해 줌
    public void printProducts(List<Product> products) {

        // 비어 있으면 안내
        if (products.isEmpty()) {
            System.out.println("조건에 맞는 상품이 없어요 ㅜㅜ");
            return;
        }

        // 출력
        IntStream.range(0, products.size()).forEach(i -> {
            Product product = products.get(i);
            int menuNumber = i + 1;

            System.out.println(menuNumber + ". " + product.getName()
                    + " | " + formatPrice(product.getPrice()) + "원"
                    + " | " + product.getDescription());
        });
    }

    public List<Product> all(List<Product> allProducts) {
        return allProducts;
    }

    public List<Product> underMillion(List<Product> products) {
        return  products.stream()
                .filter(product -> product.getPrice() <= 1000000)
                .collect(Collectors.toList());
    }

    public List<Product> overMillion(List<Product> products) {
        return products.stream()
                .filter(product -> product.getPrice() > 1000000)
                .collect(Collectors.toList());
    }
}
