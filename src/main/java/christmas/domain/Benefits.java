package christmas.domain;

import christmas.Util;
import christmas.setting.*;

public class Benefits {
    private int christmas;
    private int weekdayDessert;
    private int weekendMain;
    private int special;
    private OrderItem freeMenu;
    private Badge badge;
    private static final String colon = ": ";

    Benefits() {
    }

    public static Benefits from(Order order, int date) {
        Benefits benefits = new Benefits();
        if (order.getOriginAmount() >= EventConfig.BENEFIT_THRESHOLD.get()) {
            benefits.setChristmas(date);
            benefits.setFreeMenu(order.getOriginAmount());
            benefits.setWeekdayDessert(date, order);
            benefits.setWeekendMain(date, order);
            benefits.setSpecial(date);
            benefits.setBadge();
        }
        return benefits;
    }

    public String getBenefitsSummary() {
        String summary = getDiscountSummary(DiscountPolicy.CHRISTMAS, christmas) +
                getDiscountSummary(DiscountPolicy.WEEKDAY, weekdayDessert) +
                getDiscountSummary(DiscountPolicy.WEEKEND, weekendMain) +
                getDiscountSummary(DiscountPolicy.SPECIAL, special) +
                getDiscountSummary(DiscountPolicy.GIFT, freeMenu);
        return summary.trim();
    }

    private String getDiscountSummary(DiscountPolicy policy, int discountAmount) {
        StringBuilder sb = new StringBuilder();
        if (discountAmount != EventConfig.ZERO_PRICE.get()) {
            sb.append(policy.get()).append(colon);
            sb.append(Util.toMoneyFormat(discountAmount * EventConfig.NEGATIVE_SIGN.get()));
            sb.append(Unit.NEW_LINE.get());
        }
        return sb.toString();
    }

    private String getDiscountSummary(DiscountPolicy policy, OrderItem giftItem) {
        StringBuilder sb = new StringBuilder();
        if (giftItem != null) {
            sb.append(policy.get()).append(colon);
            sb.append(Util.toMoneyFormat(giftItem.getDishPrice() * EventConfig.NEGATIVE_SIGN.get()));
            sb.append(Unit.NEW_LINE.get());
        }
        return sb.toString();
    }

    public int getAllBenefits() {
        if (freeMenu != null) {
            return (christmas + weekdayDessert + weekendMain + special + freeMenu.getDishPrice());
        }
        return getEffectiveDiscount();
    }

    public int getEffectiveDiscount() {
        return (christmas + weekdayDessert + weekendMain + special);
    }

    public String getFreeMenu() {
        if (freeMenu != null) {
            return freeMenu.getMenuName() + Unit.SPACE.get() + freeMenu.getCount() + Unit.COUNT.get();
        }
        return Unit.EMPTY_STRING.get();
    }

    public Badge getBadge() {
        return this.badge;
    }

    private void setChristmas(int date) {
        int discount = DiscountPolicy.CHRISTMAS_INIT.getAmount();
        if (date == DateConfig.FIRST_DAY) {
            this.christmas = discount; return;
        }
        for (int i = DateConfig.SECOND_DAY; i <= DateConfig.CHRISTMAS; i++) {
            discount += DiscountPolicy.CHRISTMAS.getAmount();
            if (i == date) {
                break;
            }
        }

        this.christmas = discount;
    }

    private void setWeekdayDessert(int date, Order order) {
        // 가정: 모든 디저트 메뉴는 할인액 이상이다.
        int count = order.countMenuByCategory(Category.디저트);
        if (!DateConfig.isWeekend(date)) {
            this.weekdayDessert = DiscountPolicy.WEEKDAY.getAmount() * count;
            return;
        }
        this.weekdayDessert = 0;
    }

    private void setWeekendMain(int date, Order order) {
        // 가정: 모든 메인 메뉴는 할인액 이상이다.
        int count = order.countMenuByCategory(Category.메인);
        if (DateConfig.isWeekend(date)) {
            this.weekendMain = DiscountPolicy.WEEKEND.getAmount() * count;
            return;
        }
        this.weekendMain = 0;
    }

    private void setSpecial(int date) {
        if (DateConfig.isSpecial(date)) {
            this.special = DiscountPolicy.SPECIAL.getAmount();
            return;
        }
        this.special = 0;
    }

    private void setFreeMenu(int originAmount) {
        if (originAmount >= EventConfig.GIFT_THRESHOLD.get()) {
            this.freeMenu = OrderItem.getSpecialMenu();
            return;
        }
        this.freeMenu = null;
    }

    private void setBadge() {
        int benefitAmount = getAllBenefits();
        if (canBadge(benefitAmount, Badge.SANTA)) return;
        if (canBadge(benefitAmount, Badge.TREE)) return;
        if (canBadge(benefitAmount, Badge.STAR)) return;
        this.badge = null;
    }

    boolean canBadge(int benefitAmount, Badge badge) {
        if (benefitAmount >= badge.getThreshold()) {
            this.badge = badge;
            return true;
        }
        return false;
    }
}