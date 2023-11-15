package christmas.setting;

public enum Badge {
    SANTA("산타", 20000),
    TREE("트리", 10000),
    STAR("별", 5000);

    private final String badge;
    private final int threshold;

    Badge(String badge, int threshold) {
        this.badge = badge;
        this.threshold = threshold;
    }

    public int getThreshold() {
        return threshold;
    }

    public String get() {
        return this.badge;
    }
}