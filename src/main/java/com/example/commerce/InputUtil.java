package com.example.commerce;

import java.util.Scanner;

public class InputUtil {

    // 입력값이 숫자가 아닐 때 예외처리
    public static int readInt(Scanner sc, String message) {
        System.out.print(message);
        String input = sc.nextLine();

        while (true) {
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자만 입력해 주세용......");
            }
        }
    }
}
