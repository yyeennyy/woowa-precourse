package christmas;

public enum Unit {
    WON("원"),
    COUNT("개");

    private final String unit;

    Unit(String unit) {
        this.unit = unit;
    }

    public String get() {
        return unit;
    }
}
