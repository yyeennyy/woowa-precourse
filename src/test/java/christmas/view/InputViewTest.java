package christmas.view;

import christmas.domain.Order;
import christmas.setting.ErrorMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;

import static org.assertj.core.api.Assertions.*;

class InputViewTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private InputView inputView;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        inputView = new InputView();
    }

    @Test
    @DisplayName("[기능]orderMenu__정상 입력")
    void orderMenu() {
        Order order = inputView.getOrder("크리스마스파스타-1,제로콜라-2");
        assertThat(order.getOrderItems().toString()).contains("[크리스마스파스타 1개, 제로콜라 2개]");
    }

    @Test
    @DisplayName("[예외]orderMenu__중복 메뉴 입력")
    void orderMenuDuplicated() {
        assertThatThrownBy(() -> inputView.getOrder("해산물파스타-1,아이스크림-2,해산물파스타-3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_MENU.get());
    }

    @Test
    @DisplayName("[예외]orderMenu__메뉴 수량 초과 입력")
    void orderMenuOverflow() {
        assertThatThrownBy(() -> inputView.getOrder("해산물파스타-1,아이스크림-19,제로콜라-1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OVERFLOW_MENU_COUNT.get());
    }

    @Test
    @DisplayName("[예외]orderMenu__개수 0개")
    void orderMenuZeroCount() {
        assertThatThrownBy(() -> inputView.getOrder("해산물파스타-0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MENU_FORMAT.get());
    }

    @Test
    @DisplayName("[예외]orderMenu__이상한 입력")
    void orderMenuWeired1() {
        assertThatThrownBy(() -> inputView.getOrder("-----"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MENU_FORMAT.get());
    }

    @Test
    @DisplayName("[예외]orderMenu__이상한 입력")
    void orderMenuWeired2() {
        assertThatThrownBy(() -> inputView.getOrder(",,,,,"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_MENU_FORMAT.get());

    }
}