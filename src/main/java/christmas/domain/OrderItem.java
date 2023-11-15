package christmas.domain;

import christmas.setting.ErrorMessage;
import java.util.Objects;

public class OrderItem {
    private final Dish dish;
    private final int count;

    private OrderItem(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    public static OrderItem from(Dish dish, int count) {
        return new OrderItem(dish, count);
    }

    public String getMenu() {
        return dish.get();
    }

    public int getCount() {
        return count;
    }

    public Dish getDish() {
        return dish;
    }

    public static int checkOrderCount(String countInput) throws IllegalArgumentException {
        int count;
        try {
            count = Integer.parseInt(countInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_COUNT.get());
        }
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return dish.equals(orderItem.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dish);
    }
}