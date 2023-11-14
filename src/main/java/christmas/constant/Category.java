package christmas.constant;

public enum Category {
    애피타이저("애피타이저"),
    메인("메인"),
    디저트("디저트"),
    음료("음료");

    private String category;

    Category(String category) {
        this.category = category;
    }
}
