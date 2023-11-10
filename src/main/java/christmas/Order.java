package christmas;

public class Order {
    private final Dish dish;
    private final int count;

    public Order(Dish dish, int count) {
        this.dish = dish;
        this.count = count;
    }

    public String getMenu() {
        return dish.getName();
    }

    public int getCount() {
        return count;
    }
}
