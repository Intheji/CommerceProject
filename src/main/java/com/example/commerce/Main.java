package com.example.commerce;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 프로그램 시작 출력
        System.out.println("[ 실시간 커머스 플랫폼 - 전자제품]");

        // 여러 Product를 담기 위한 List 생성
        List<Product> products = new ArrayList<>();

        // Product 객체 생성을 하고 List에 추가한다
        // new Produc()를 이용해서 실제 상품 객체를 생성한다
        products.add(new Product("Galaxy S25", 1200000, "최신 안드로이드 스마트폰", 50));
        products.add(new Product("iPhone 16", 1350000, "Apple의 최신 스마트폰", 30));
        products.add(new Product("MacBook Pro", 2400000, "M3 칩셋이 탑재된 노트북", 20));
        products.add(new Product("AirPods Pro", 350000, "노이즈 캔슬링 무선 이어폰", 100));


        // 상품 목록 출력
        for (int i = 0; i < products.size(); i++) {

            // 리스트에서 i 번째 상품을 꺼낸다
            Product product = products.get(i);
            int menuNumber = i + 1;

            // 메뉴판 출력
            System.out.println(menuNumber + ". " + product.getName()
                    + " | " + product.getPrice() + "원"
                    + " | " + product.getDescription());
        }
        
        // 종료
        System.out.println("0. 종료        | 프로그램 종료");
    }


}
