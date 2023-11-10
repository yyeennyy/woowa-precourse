//package christmas;
//
//import camp.nextstep.edu.missionutils.test.NsTest;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class InputViewTest {
//
//    @Test
//    void reserveDate() {
//    }
//
//    @DisplayName("[예외] 중복 메뉴 주문")
//    @Test
//    void orderMenu() {
//        InputView inputView = new InputView();
//        String input = "제로콜라-2,아이스크림-1,제로콜라-3";
//
//        assertThrows(IllegalArgumentException.class, () -> {
//            inputView.orderMenu();
//        });
//    }
//
//}