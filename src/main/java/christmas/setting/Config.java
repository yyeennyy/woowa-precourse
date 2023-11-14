package christmas.setting;

public enum Config {
    MAX_MENU_COUNT(20),
    BENEFIT_THRESHOLD(10000),
    GIFT_THRESHOLD(120000),
    ZERO_PRICE(0);

    private final int value;

    Config(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
