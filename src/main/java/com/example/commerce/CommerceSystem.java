package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    private String formatPrice(int price) {
        return String.format("%,d", price);
    }

    // Main에서 받은 상품 리스트를 저장할 필드
    private List<Category> categories;
    private Customer customer;
    private Cart cart = new Cart();


    public CommerceSystem(List<Category> categories, Customer customer) {

        // categories 원본을 그대로 들고 있지 않고 복사본 저장
        this.categories = new ArrayList<>(categories);

        // customer가 null일 때 예외처리
        if (customer == null) {
            throw new IllegalArgumentException("Customer는 null일 수 없습니다.");
        }

        // 필드 private
        this.customer = customer;
    }

    // 프로그램 시작 메서드
    public void start() {

        // 사용자 입력을 받기 위한 Scanner
        Scanner sc = new Scanner(System.in);


        // 프로그램을 계속 돌릴지 결정하는 변수
        boolean run = true;

        // run이 true인 동안 계속 반복
        while (run) {

            // 프로그램 시작 출력
            System.out.println("[실시간 커머스 플랫폼 메인]");

            // 카테고리 목록 출력
            for (int i = 0; i < categories.size(); i++) {

                // 리스트에서 i 번째 상품을 꺼낸다
                int menuNumber = i + 1;
                Category category = categories.get(i);
                System.out.println(menuNumber + ". " + category.getName());

            }

            // 종료
            System.out.println("0. 종료        | 프로그램 종료");

            // readInt 메서드로 입력받아서 예외처리
            int choice = readInt(sc, "선택> ");

            // 입력 처리
            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                run = false;
            } else {

                // 사용자가 입력한 번호를 인덱스로 바꿈
                int categoryIndex = choice - 1;

                // 사용자가 입력한 번호가 잘못되면 돌아가기
                if (categoryIndex < 0 || categoryIndex >= categories.size()) {
                    System.out.println("잘못된 입력입니다: " + choice);
                    continue;
                }

                // categoryIndex에 해당하는 카테고리를 리스트에서 꺼냄
                Category selectedCategory = categories.get(categoryIndex);

                // 카테고리용
                boolean inCategory = true;

                while (inCategory) {
                    System.out.println("[ " + selectedCategory.getName() + "카테고리 ]");

                    // 상품 목록 가져오기
                    List<Product> products = selectedCategory.getProducts();

                    // 상품 목록 출력
                    for (int i = 0; i < products.size(); i++) {
                        Product product = products.get(i);
                        int menuNumber = i + 1;

                        System.out.println(menuNumber + ". " + product.getName()
                                + " | " + formatPrice(product.getPrice()) + "원"
                                + " | " + product.getDescription());

                    }

                    System.out.println("0. 뒤로가기");

                    // readInt 메서드로 입력받아서 예외처리
                    int productChoice = readInt(sc, "선택> ");
                    if (productChoice == 0) {
                        inCategory = false;         // 이건 메인으로 복귀
                    } else {

                        // 사용자가 입력한 상품 번호를 인덱스로 변환
                        int productIndex = productChoice - 1;

                        // 상품 번호 범위 벗어났나요?
                        if (productIndex < 0 || productIndex >= products.size()) {
                            System.out.println("잘못된 상품 번호입니다: " + productChoice);
                            continue;
                        }

                        // 선택한 상품 가져옴
                        Product selectedProduct = products.get(productIndex);

                        // 장바구니 추가 여부 질문
                        System.out.println();
                        System.out.println("\"" + selectedProduct.getName()
                                + " | " + formatPrice(selectedProduct.getPrice()) + "원"
                                + " | " + selectedProduct.getDescription()
                                + " | " + selectedProduct.getStock() + "\"");
                        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인       2. 취소");

                        // 입력 받기
                        int addChoice = readInt(sc, "선택> ");

                        if (addChoice == 1) {

                            int quantity = readInt(sc, "수량: ");

                            // 수량이 1 이상인지 확인
                            if (quantity <= 0) {
                                System.out.println("수량은 1개 이상 입력하세요.");
                                continue;
                            }

                            // 재고 체크
                            if (quantity > selectedProduct.getStock()) {
                                System.out.println("재고가 부족합니다. 현재 재고: " + selectedProduct.getStock() + "개");
                                continue;
                            }

                            // 장바구니 추가
                            cart.addItem(selectedProduct, quantity);
                            System.out.println(selectedProduct.getName() + "가 " + quantity + "개 장바구니에 추가되었습니다.");

                        } else if (addChoice == 2) {
                            System.out.println("장바구니 추가를 취소했습니다.");
                        } else {
                            System.out.println("잘못된 입력입니다.");
                        }

                        // 선택한 상품 가격을 이번 주문 금액으로
                        customer.addOrderAmount(selectedProduct.getPrice());

                        // 고객 상태 출력(누적 금액 + 등급)
                        System.out.println(
                                "고객: " + customer.getName()
                                + " | 누적 주문 금액: " + String.format("%,d", customer.getTotalOrderAmount())
                                + " | 등급: " + customer.getGrade()
                        );

                    }

                    System.out.println();

                }

            }

            System.out.println();

        }

        sc.close();

    }


    // 입력값이 숫자가 아닐 시에 예외처리
    private int readInt(Scanner sc, String message) {

        while (true) {

            // 안내 문구 출력
            System.out.print(message);

            String input = sc.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("보기에 보이는 숫자만 입력해 주세요! ㅜㅜ");
            }

        }

    }

}
