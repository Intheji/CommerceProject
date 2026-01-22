package com.example.commerce;

import javax.swing.*;
import javax.swing.text.Utilities;
import java.util.List;
import java.util.Scanner;

public class AdminSystem {

    private final List<Category> categories;
    private final Cart cart;

    public AdminSystem(List<Category> categories, Cart cart) {
        this.categories = categories;
        this.cart = cart;
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
            System.out.println("비밀번호가 틀렸습니다. 님 지금 " + passwordAttempt + "번 틀림. 3번 틀리면 메인으로 감.");
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

            int adminChoice = InputUtil.readInt(sc, "선택> ");

            if (adminChoice == 0) {
                System.out.println("메인으로 돌아갑니다.");
                return;
            }

            if (adminChoice == 1) {
                addProduct(sc);
            } else if (adminChoice == 3) {
                removeProduct(sc);
            } else {
                System.out.println("잘못된 입력입니다.");
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

        String name;

        // 중복 상품명 검사
        while (true) {
            // 상품명 입력
            System.out.print("0 입력 시에 취소됩니다.\n상품명: ");
            name = sc.nextLine();

            if ("0".equals(name)) {
                System.out.println("상품 추가를 취소함");
                return;
            }

            if (category.hasProduct(name)) {
                System.out.println("이미 등록된 상품입니다.");
                continue;
            }

            break;

        }

        int price = InputUtil.readInt(sc, "가격: ");

        System.out.print("설명: ");
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

    private void removeProduct(Scanner sc) {

        Category category = selectCategory(sc);

        if (category == null) {
            System.out.println("상품 삭제를 취소했습니다.");
            return;
        }

        System.out.print("삭제할 상품명(0 입력 시 취소): ");
        String name = sc.nextLine();

        if ("0".equals(name)) {
            System.out.println("상품 삭제 취소");
            return;
        }

        Product target = category.findProduct(name);

        if (target == null) {
            System.out.println("이런 것 없음");
            return;
        }

        System.out.print("\n삭제 대상 상품: ");
        System.out.println(target.getName() + " | " + String.format("%,d", target.getPrice()) + "원"
                + " | " + target.getDescription()
                + " | 재고: " + target.getStock() + "개");

        System.out.println("\n님 정말 삭제하실 거예요?");
        System.out.println("1. 삭제     2. 취소");

        int confirm = InputUtil.readInt(sc, "선택> ");

        if (confirm != 1) {
            System.out.println("삭제를 취소함.");
            return;
        }

        category.removeProduct(target);
        cart.removeProduct(target);
        System.out.println("상품이 삭제되었음.");
    }

}

