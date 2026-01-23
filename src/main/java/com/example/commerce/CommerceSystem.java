package com.example.commerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CommerceSystem {

    private String formatPrice(int price) {
        return String.format("%,d", price);
    }

    // Main에서 받은 상품 리스트를 저장할 필드
    private List<Category> categories;
    private Customer customer;
    private Cart cart = new Cart();
    private AdminSystem adminSystem;


    public CommerceSystem(List<Category> categories, Customer customer) {

        // categories 원본을 그대로 들고 있지 않고 복사본 저장
        this.categories = new ArrayList<>(categories);

        // customer가 null일 때 예외처리
        if (customer == null) {
            throw new IllegalArgumentException("Customer는 null일 수 없습니다.");
        }

        this.customer = customer;
        this.adminSystem = new AdminSystem(categories, this.cart);
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
//            for (int i = 0; i < categories.size(); i++) {
//                // 리스트에서 i 번째 상품을 꺼낸다
//                int menuNumber = i + 1;
//                Category category = categories.get(i);
//                System.out.println(menuNumber + ". " + category.getName());
//
//            }
            IntStream.range(0, categories.size()).forEach(i -> {
                Category category = categories.get(i);
                int menuNumber = i + 1;
                System.out.println(menuNumber + ". " + category.getName());
            });

            if (!cart.isEmpty()) {
                System.out.println("\n[주문 관리]");
                System.out.println("4. 장바구니 확인   | 장바구니를 확인 후 주문");
                System.out.println("5. 주문 취소      | 진행중인 주문 취소");
            }
            
            System.out.println("0. 종료        | 프로그램 종료");
            System.out.println("6. 관리자 모드  | 상품 추가/수정/삭제");

            // readInt 메서드로 입력받아서 예외처리
            int choice = InputUtil.readInt(sc, "선택> ");


            // 4번 장바구니 확인
            if (choice == 4) {
                if (cart.isEmpty()) {
                    System.out.println("장바구니가 비어있어용.");
                    continue;
                }

                System.out.println("[장바구니 내역]");

                int totalPrice = 0;

                for (CartItem item : cart.getItems()) {
                    Product p = item.getProduct();
                    int quantity = item.getQuantity();
                    System.out.println(p.getName() + " | " + formatPrice(p.getPrice()) + "원" 
                            + " | " + p.getDescription() + " | " + quantity + "개");
                    totalPrice += p.getPrice() * quantity;
                }

                System.out.println("\n[총 주문 금액]");
                System.out.println(String.format("%,d", totalPrice) + "원");

                System.out.println("\n1. 주문 확정    2. 메인으로 돌아가기");

                int confirm = InputUtil.readInt(sc, "선택> ");

                if (confirm == 1) {

                    CustomerGrade gradeBeforeOrder = customer.getGradeEnum();

                    totalPrice = 0;
                    for (CartItem item : cart.getItems()) {
                        Product p = item.getProduct();      // 장바구니에서 상품 꺼냄
                        int quantity = item.getQuantity();  // 장바구니에서 수량 꺼냄
                        totalPrice += p.getPrice() * quantity; // 총액

                        int beforeStock = p.getStock(); // 재고 차감 전에 재고 저장.....
                        p.decreaseStock(quantity);  // 재고 차감
                        int afterStock = p.getStock();  // 재고 차감 후 저장

                        System.out.println(p.getName() + "\n 재고가 " + beforeStock + "개에서 " + afterStock + "개로 업데이트되었습니다.");
                    }

                    long originalPrice = totalPrice;   // 얘가 할인 전 금액
                    long discountPrice = gradeBeforeOrder.calculateDiscount(originalPrice); // 할인을 하는 금액
                    long finalPrice = totalPrice - discountPrice;   // 최종 결제 금액
                    customer.addOrderAmount(originalPrice);
                    cart.clear();
                    System.out.println("\n주문이 완료되었습니다...! 총 금액: " + String.format("%,d", finalPrice) + "원\n");

                } else if (confirm == 2) {
                    System.out.println("메인으로 돌아갑니다.");

                } else {
                    System.out.println("잘못된 입력입니다. 메인으로 돌아갑니다.");
                }
                continue;
            }

            // 5번: 주문 취소 장바구니 비움
            if (choice == 5) {

                if (cart.isEmpty()) {
                    System.out.println("취소할 주문이 없습니다. 장바구니가 비어있습니다.");
                    continue;
                }

                cart.clear();
                System.out.println("진행중인 주문이 취소되었습니다. 장바구니를 비웠습니다.");
            }

            // 0번: 프로그램 종료
            if (choice == 0) {
                System.out.println("커머스 플랫폼을 종료합니다");
                run = false;
            }

            // 9번: 관리자 모드
            if (choice == 6) {

                adminSystem.start(sc);
                continue;
            }
            
            
            else {
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
                ProductFilter productFilter = new ProductFilter();

                while (inCategory) {
                    System.out.println("\n[ " + selectedCategory.getName() + "카테고리 ]");

                    System.out.println("1. 전체 상품 보기");
                    System.out.println("2. 100만원 이하 상품이 궁금해요?");
                    System.out.println("3. 100만원 초과 상품이 궁금해요?");
                    System.out.println("0. 뒤로가기");

                    int viewChoice = InputUtil.readInt(sc, "선택> ");

                    // 카테고리에서 나가기
                    if (viewChoice == 0) {
                        break;
                    }

                    // 상품 목록 가져오기
                    List<Product> allProducts = selectedCategory.getProducts();

                    // 필터하고 보여줌
                    List<Product> showProducts;

                    if (viewChoice == 1) {
                        showProducts = productFilter.all(allProducts);
                    } else if (viewChoice == 2) {
                        showProducts = productFilter.underMillion(allProducts);
                        System.out.println("\n[100만원 이하 상품 목록]");
                    } else if (viewChoice == 3) {
                        showProducts = productFilter.overMillion(allProducts);
                        System.out.println("\n[100만원 초과 상품 목록]");
                    } else {
                        System.out.println("잘못된 입력이에염");
                        continue;
                    }

                    productFilter.printProducts(showProducts);

                    System.out.println("0. 뒤로가기");

                    // readInt 메서드로 입력받아서 예외처리
                    int productChoice = InputUtil.readInt(sc, "선택> ");
                    if (productChoice == 0) {
                        inCategory = false;         // 이건 메인으로 복귀
                    } else {

                        // 사용자가 입력한 상품 번호를 인덱스로 변환
                        int productIndex = productChoice - 1;

                        // 상품 번호 범위 벗어났나요?
                        if (productIndex < 0 || productIndex >= showProducts.size()) {
                            System.out.println("잘못된 상품 번호입니다: " + productChoice);
                            continue;
                        }

                        // 선택한 상품 가져옴
                        Product selectedProduct = showProducts.get(productIndex);

                        // 장바구니 추가 여부 질문
                        System.out.println();
                        System.out.println("\"" + selectedProduct.getName()
                                + " | " + formatPrice(selectedProduct.getPrice()) + "원"
                                + " | " + selectedProduct.getDescription()
                                + " | " + selectedProduct.getStock() + "\"");
                        System.out.println("위 상품을 장바구니에 추가하시겠습니까?");
                        System.out.println("1. 확인       2. 취소");

                        // 입력 받기
                        int addChoice = InputUtil.readInt(sc, "선택> ");

                        if (addChoice == 1) {

                            int quantity = InputUtil.readInt(sc, "수량: ");

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

                        // 고객 상태 출력(누적 금액 + 등급)
                        System.out.println(
                                "고객: " + customer.getName()
                                + " | 누적 주문 금액: " + String.format("%,d", customer.getTotalOrderAmount())
                                + " | 등급: " + customer.getGrade()
                        );
                    }
                }
            }
        }
        sc.close();
    }
}
