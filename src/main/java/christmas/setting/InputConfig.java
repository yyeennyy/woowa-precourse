package christmas.setting;

public enum InputConfig {
    ORDER_MENU_DELIMITER(","),
    MENU_AND_COUNT_DELIMITER("-");

    private final String format;
    InputConfig(String format) {
        this.format = format;
    }

    public String get() {
        return format;
    }


}
