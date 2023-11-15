package christmas.domain;

import christmas.setting.ErrorMessage;
import christmas.setting.EventConfig;
import christmas.setting.Menu;
import christmas.setting.Unit;
import java.util.Objects;

public class OrderItem {
    private final Dish dish;
    private final int count;
    private static final int ZERO = 0;

    private OrderItem(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    public static OrderItem from(Dish dish, int count) {
        return new OrderItem(dish, count);
    }

    public static OrderItem getSpecialMenu() {
        Menu special = Menu.SPECIAL_MENU;
        int giftCount = EventConfig.GIFT_COUNT.get();
        return new OrderItem(Dish.of(special.get()), giftCount);
    }

    public String getMenuName() {
        return dish.getName();
    }

    public int getCount() {
        return count;
    }

    public Dish getDish() {
        return dish;
    }

    public int getDishPrice() {
        return Menu.getPrice(dish.getName());
    }

    public static int checkOrderCount(String countInput) throws IllegalArgumentException {
        int count;
        try {
            count = Integer.parseInt(countInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_COUNT.get());
        }
        if (count <= ZERO) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_COUNT.get());
        }
        return count;
    }

    @Override
    public String toString() {
        return dish.getName() + Unit.SPACE.get() + count + Unit.COUNT.get();
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