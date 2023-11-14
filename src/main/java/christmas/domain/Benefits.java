package christmas.domain;

import christmas.Util;
import christmas.setting.*;
import java.util.List;

public class Benefits {
    private int christmas;
    private int weekdayDessert;
    private int weekendMain;
    private int special;
    private Menu freeMenu;
    private Badge badge;
    private static final String colon = ": ";
    private static final int sign = -1;

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
        if (discountAmount != Config.ZERO_PRICE.get()) {
            sb.append(policy.get()).append(colon);
            sb.append(Util.toMoneyFormat(discountAmount * sign));
            sb.append(Util.newLine());
        }
        return sb.toString();
    }

    private String getDiscountSummary(DiscountPolicy policy, Menu menu) {
        StringBuilder sb = new StringBuilder();
        if (menu != null) {
            sb.append(policy.get()).append(colon);
            sb.append(Util.toMoneyFormat(menu.getPrice() * sign));
            sb.append(Util.newLine());
        }
        return sb.toString();
    }

    public int getAllBenefits() {
        if (freeMenu != null) {
            return (christmas + weekdayDessert + weekendMain + special + freeMenu.getPrice());
        }
        return getEffectiveDiscount();
    }

    public int getEffectiveDiscount() {
        return (christmas + weekdayDessert + weekendMain + special);
    }

    public String getFreeMenu() {
        if (freeMenu != null) {
            return freeMenu.get();
        }
        return Message.NOT_EXIST.get();
    }

    public Badge getBadge() {
        return this.badge;
    }

    public void setChristmas(int date) {
        int discount = DiscountPolicy.CHRISTMAS_INIT.getDiscount();

        for (int i = SpecialDates.SECOND_DAY; i <= SpecialDates.CHRISTMAS; i++) {
            discount += DiscountPolicy.CHRISTMAS.getDiscount();
            if (i == date) {
                break;
            }
        }

        this.christmas = discount;
    }

    public void setWeekdayDessert(int date, List<Order> orders) {
        int count = countMenuByCategory(Category.디저트, orders);
        if (!Util.isWeekend(date)) {
            this.weekdayDessert = DiscountPolicy.WEEKDAY.getDiscount() * count;
            return;
        }
        this.weekdayDessert = 0;
    }

    public void setWeekendMain(int date, List<Order> orders) {
        int count = countMenuByCategory(Category.메인, orders);
        if (Util.isWeekend(date)) {
            this.weekendMain = DiscountPolicy.WEEKEND.getDiscount() * count;
            return;
        }
        this.weekendMain = 0;
    }

    private int countMenuByCategory(Category category, List<Order> orders) {
        int count = 0;
        for (Order order : orders) {
            if (Menu.getCategory(order.getMenu()).equals(category)) {
                count += order.getCount();
            }
        }
        return count;
    }

    public void setSpecial(int date) {
        if (SpecialDates.isSpecialDay(date)) {
            this.special = DiscountPolicy.SPECIAL.getDiscount();
            return;
        }
        this.special = 0;
    }

    public void setFreeMenu(int originAmount) {
        if (originAmount >= Config.GIFT_THRESHOLD.get()) {
            this.freeMenu = Menu.SPECIAL_MENU;
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
