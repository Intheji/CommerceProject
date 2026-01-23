package com.example.commerce;

import java.util.Scanner;

public class InputUtil {

    // 입력값이 숫자가 아닐 때 예외처리
    public static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            String input = sc.nextLine();

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해 주세용......");
            }
        }
    }

    // 문자열 입력받는 메서드
    public static String readString(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine();
    }
}
