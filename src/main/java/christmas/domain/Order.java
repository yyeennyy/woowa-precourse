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
    private static final int ZERO = 0;

    private Order(List<OrderItem> orderItems) throws IllegalArgumentException {
        if (orderItems.size() == ZERO) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.get());
        }
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

    public static Order of(List<OrderItem> orderItems) throws IllegalArgumentException {
        return new Order(Collections.unmodifiableList(orderItems));
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
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            count += orderItem.getCount();
            if (count > EventConfig.MAX_MENU_COUNT.get()) {
                return true;
            }
        }
        return false;
    }

    public int getOriginAmount() {
        int sum = 0;
        for (OrderItem orderItem : this.orderItems) {
            sum += Menu.getPrice(orderItem.getMenuName()) * orderItem.getCount();
        }
        return sum;
    }
}
