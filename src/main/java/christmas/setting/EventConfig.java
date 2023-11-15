package christmas.setting;

public enum EventConfig {
    MAX_MENU_COUNT(20),
    BENEFIT_THRESHOLD(10000),
    GIFT_THRESHOLD(120000),
    ZERO_PRICE(0),
    NEGATIVE_SIGN(-1);


    private final int value;

    EventConfig(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
