package christmas.setting;

public enum Unit {
    WON("원"),
    COUNT("개"),
    SPACE(" "),
    EMPTY_STRING(""),
    NEW_LINE("\n");

    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String get() {
        return unit;
    }

}