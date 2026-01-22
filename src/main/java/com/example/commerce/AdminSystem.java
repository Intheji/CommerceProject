package com.example.commerce;

import javax.swing.text.Utilities;
import java.util.List;
import java.util.Scanner;

public class AdminSystem {

    private final List<Category> categories;

    public AdminSystem(List<Category> categories) {
        this.categories = categories;
    }

    public void start(Scanner sc) {

        // 비밀번호를 최대 3번까지 입력받음
        for (int passwordAttempt = 1; passwordAttempt <= 3; passwordAttempt++) {
            System.out.print("관리자 비밀번호를 입력하세요: ");
            String password = sc.nextLine();

            if ("1234".equals(password)) {
                System.out.println("관리자 인증 성공");
                adminMenu(sc);
                return;
            }
            System.out.println("비밀번호가 틀렸습니다. 님 지금 (" + passwordAttempt + "번 틀림.");
        }
        System.out.println("님 3회 틀렸고 메인으로 갑니다");
    }



    private void adminMenu(Scanner sc) {

        boolean isAdmin = true;
        while (isAdmin) {

            System.out.println("\n[관리자 모드]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");

            int adminChoice = InputUtil.readInt(sc, "선택>");

            if (adminChoice == 0) {
                System.out.println("메인으로 돌아갑니다.");
                return;
            }

            if (adminChoice == 1) {
                addProduct(sc);
            } else {
                System.out.println();
            }
        }
    }



    private void addProduct(Scanner sc) {

        Category category = selectCategory(sc);

        if (category == null) {
            System.out.println("상품 추가를 취소했습니다.");
            return;
        }

        System.out.println("\n[상품 추가]");

        System.out.println("상품명: ");
        String name = sc.nextLine();

        int price = InputUtil.readInt(sc, "가격: ");

        System.out.println("설명: ");
        String description = sc.nextLine();

        int stock = InputUtil.readInt(sc, "재고: ");

        Product product = new Product(name, price, description, stock);
        category.addProduct(product);

        System.out.println("상품이 추가되었습니다");
    }



    private Category selectCategory(Scanner sc) {
        System.out.println("\n[카테고리 선택]");

        for (int i = 0; i < this.categories.size(); i++) {
            int menuNumber = i + 1;
            System.out.println(menuNumber + ". " + categories.get(i).getName());
        }

        System.out.println("0. 취소");

        int choice = InputUtil.readInt(sc, "선택> ");

        if (choice == 0) {
            return null;
        }

        int index = choice - 1;

        if (index < 0 || index >= this.categories.size()) {
            System.out.println("잘못된 번호입니다.");
            return null;
        }
        return this.categories.get(index);
    }

}

