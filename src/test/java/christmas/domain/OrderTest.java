package christmas.domain;

import christmas.setting.ErrorMessage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class OrderTest {

    @Test
    @DisplayName("[예외]Order__음료수만 주문함")
    void orderDrinkOnlyException() {
        List<OrderItem> drinkOnlyItems = Arrays.asList(
                OrderItem.from(Dish.of("제로콜라"), 2),
                OrderItem.from(Dish.of("샴페인"), 1)
        );

        Assertions.assertThatThrownBy(() -> Order.of(drinkOnlyItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DRINK_ONLY.get());
    }

    @Test
    @DisplayName("[예외]Order__주문 개수 한도 초과")
    void orderMenuCountOverflowException() {
        List<OrderItem> items = Arrays.asList(
                OrderItem.from(Dish.of("양송이수프"), 18),
                OrderItem.from(Dish.of("타파스"), 1),
                OrderItem.from(Dish.of("시저샐러드"), 2)
        );

        assertThatThrownBy(() -> Order.of(items))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.OVERFLOW_MENU_COUNT.get());
    }

    @Test
    @DisplayName("[예외]Order__중복 메뉴 주문")
    void orderDuplicatedMenuException() {
        List<OrderItem> duplicatedItems = Arrays.asList(
                OrderItem.from(Dish.of("양송이수프"), 2),
                OrderItem.from(Dish.of("타파스"), 1),
                OrderItem.from(Dish.of("양송이수프"), 1)
        );

        assertThatThrownBy(() -> Order.of(duplicatedItems))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DUPLICATED_MENU.get());
    }
}