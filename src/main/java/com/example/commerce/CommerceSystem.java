package com.example.commerce;

import java.util.List;
import java.util.Scanner;

public class CommerceSystem {

    // Main에서 받은 상품 리스트를 저장할 필드
    private List<Category> categories;

    // 생성자: Main이 만든 상품 리스트를 받음
    public CommerceSystem(List<Category> categories) {
        this.categories = categories;
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

            // 입력 받음
            System.out.print("선택> ");
            int choice = sc.nextInt();

            // 입력 처리
            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                run = false;
            } else {
                System.out.println("아직 안 돼요 ㅜ");
            }

            System.out.println();

        }

        sc.close();
    }
}
