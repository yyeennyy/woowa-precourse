package christmas.constant;

public enum Config {
    MAX_MENU_COUNT(20),
    BENEFIT_THRESHOLD(10000);

    private final int value;

    Config(int value) {
        this.value = value;
    }

    public int get() {
        return value;
    }
}
