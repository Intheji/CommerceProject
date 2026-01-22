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

        System.out.println("관리자 모드에 진입");

    }

}

