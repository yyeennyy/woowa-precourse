package christmas.domain;

import christmas.setting.Menu;

import java.util.Objects;

public class Dish {
    private final Menu menu;

    private Dish(String dishName) throws IllegalArgumentException {
        this.menu = Menu.get(dishName);
    }

    public static Dish of(String dishName) {
        return new Dish(dishName);
    }

    public String getName() {
        return menu.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish1 = (Dish) o;
        return Objects.equals(menu, dish1.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu);
    }
}