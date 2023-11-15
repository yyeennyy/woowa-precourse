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

    public void setChristmas(int date) {
        int discount = DiscountPolicy.CHRISTMAS_INIT.getAmount();

        for (int i = SpecialDates.SECOND_DAY; i <= SpecialDates.CHRISTMAS; i++) {
            discount += DiscountPolicy.CHRISTMAS.getAmount();
            if (i == date) {
                break;
            }
        }

        this.christmas = discount;
    }

    public void setWeekdayDessert(int date, Order order) {
        int count = order.countMenuByCategory(Category.디저트);
        if (!Util.isWeekend(date)) {
            this.weekdayDessert = DiscountPolicy.WEEKDAY.getAmount() * count;
            return;
        }
        this.weekdayDessert = 0;
    }

    public void setWeekendMain(int date, Order order) {
        int count = order.countMenuByCategory(Category.메인);
        if (Util.isWeekend(date)) {
            this.weekendMain = DiscountPolicy.WEEKEND.getAmount() * count;
            return;
        }
        this.weekendMain = 0;
    }

    public void setSpecial(int date) {
        if (SpecialDates.isSpecial(date)) {
            this.special = DiscountPolicy.SPECIAL.getAmount();
            return;
        }
        this.special = 0;
    }

    public void setFreeMenu(int originAmount) {
        if (originAmount >= EventConfig.GIFT_THRESHOLD.get()) {
            this.freeMenu = OrderItem.getSpecialMenu();
            return;
        }
        this.freeMenu = null;
    }

    public void setBadge() {
        int benefitAmount = getAllBenefits();
        if (canBadge(benefitAmount, Badge.SANTA)) return;
        if (canBadge(benefitAmount, Badge.TREE)) return;
        if (canBadge(benefitAmount, Badge.STAR)) return;
        this.badge = null;
    }

    private boolean canBadge(int benefitAmount, Badge badge) {
        if (benefitAmount >= badge.getThreshold()) {
            this.badge = badge;
            return true;
        }
        return false;
    }
}
