package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class AdminSystem {

    private final List<Category> categories;

    public AdminSystem(List<Category> categories) {
        this.categories = categories;
    }

    public void start(Scanner sc) {

        System.out.print("관리자 비밀번호를 입력하세요: ");
        String password = sc.nextLine();

        if (!"1234".equals(password)) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }

        boolean isAdmin = true;
        while (isAdmin) {

            System.out.println("\n[관리자 모드]");
            System.out.println("1. 상품 추가");
            System.out.println("2. 상품 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 전체 상품 현황");
            System.out.println("0. 메인으로 돌아가기");

            int adminChoice = InputUtil.readInt(sc, "선택>");
        }

    }

}

