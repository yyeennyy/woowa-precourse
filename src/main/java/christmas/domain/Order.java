package christmas.domain;

import christmas.setting.Category;
import christmas.setting.EventConfig;
import christmas.setting.ErrorMessage;
import christmas.setting.Menu;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private final List<OrderItem> orderItems;

    private Order(List<OrderItem> orderItems) throws IllegalArgumentException {

        if (isOnlyDrinkMenu(orderItems)) {
            throw new IllegalArgumentException(ErrorMessage.DRINK_ONLY.get());
        }
        if (isOrderCountOverflow(orderItems)) {
            throw new IllegalArgumentException(ErrorMessage.OVERFLOW_MENU_COUNT.get());
        }
        if (hasDuplicatedItem(orderItems)) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_MENU.get());
        }

        this.orderItems = Collections.unmodifiableList(orderItems);
    }

    private boolean hasDuplicatedItem(List<OrderItem> orderItems) {
        Set<Dish> uniqueDishes = new HashSet<>();
        for (OrderItem orderItem : orderItems) {
            uniqueDishes.add(orderItem.getDish());
        }
        return orderItems.size() != uniqueDishes.size();
    }

    public static Order of(List<OrderItem> orderItems) {
        return new Order(orderItems);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public int countMenuByCategory(Category category) {
        int count = 0;
        for (OrderItem orderItem : this.orderItems) {
            if (Menu.getCategory(orderItem.getMenuName()).equals(category)) {
                count += orderItem.getCount();
            }
        }
        return count;
    }

    private static boolean isOnlyDrinkMenu(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            if (!Menu.getCategory(orderItem.getMenuName()).equals(Category.음료)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isOrderCountOverflow(List<OrderItem> orderItems) {
        return orderItems.size() > EventConfig.MAX_MENU_COUNT.get();
    }

}
