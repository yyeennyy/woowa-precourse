package christmas.domain;

import christmas.setting.ErrorMessage;
import christmas.setting.Menu;

import java.util.Objects;

public class Dish {
    private final String dish;

    private Dish(String dishName) {
        if (!Menu.exist(dishName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_NAME.get());
        }
        this.dish = dishName;
    }

    public static Dish of(String dishName) {
        return new Dish(dishName);
    }

    public String get() {
        return dish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish1 = (Dish) o;
        return Objects.equals(dish, dish1.dish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dish);
    }
}