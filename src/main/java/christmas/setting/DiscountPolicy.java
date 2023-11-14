package christmas.setting;

public enum DiscountPolicy {
    CHRISTMAS_INIT("크리스마스 최초 할인", 1000),
    CHRISTMAS("크리스마스 디데이 할인", 100),
    WEEKDAY("평일 할인", 2023),
    WEEKEND("주말 할인", 2023),
    SPECIAL("특별 할인", 1000),
    GIFT("증정 이벤트", 0);

    private final String policy;
    private final int discount;

    DiscountPolicy(String policy, int discount) {
        this.policy = policy;
        this.discount = discount;
    }

    public int getDiscount() {
        return discount;
    }

    public String get() {
        return policy;
    }
}
