package com.example.commerce;

public class Customer {
    
    // 고객명
    private String name;
    
    // 이메일 
    private String email;
    
    // 누적 주문 금액
    private long totalOrderAmount;
    
    // 등급
    private String grade;
    
    // 고객 생성 시 이름/이메일을 받고 초기값 세팅
    public Customer(String name, String email) {
        this.name = name;
        this.email = email;
        this.totalOrderAmount = 0L;
        this.grade = "브론즈";
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public long getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public String getGrade() {
        return grade;
    }

    // 주문 금액을 누적하고 등급 업데이트 메서드
    public void addOrderAmount(long orderAmount) {

        // 0 이하 주문 금액 누적 안 함
        if (orderAmount <= 0) {
            return;
        }

        // 누적 금액 갱신
        totalOrderAmount += orderAmount;

        // 누적 금액 기준으로 등급 갱신
        updateGrade();
    }

    // 누적 금액 기준으로 등급 계산
    private void updateGrade() {

        if (totalOrderAmount >= 2000000) {
            grade = "플래티넘";
        } else if (totalOrderAmount >= 1000000) {
            grade = "골드";
        } else if (totalOrderAmount >= 500000) {
            grade = "실버";
        } else {
            grade = "브론즈";
        }
    }
}
