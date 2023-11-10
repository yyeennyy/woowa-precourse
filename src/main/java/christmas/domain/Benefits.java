package christmas.domain;

import christmas.Util;
import christmas.constant.Message;
import christmas.constant.SpecialDay;

import java.util.List;

public class Benefits {
    private int christmas;
    private int weekdayDessert;
    private int weekendMain;
    private int special;
    private Menu freeMenu;
    private String badge;


    public String getAllBenefitsList() {
        StringBuilder benefits = new StringBuilder();
        if (christmas != 0) {
            benefits.append("크리스마스 디데이 할인: ");
            benefits.append(Util.toMoneyFormat(christmas * -1));
            benefits.append("\n");
        }
        if (weekdayDessert != 0) {
            benefits.append("평일 할인: ");
            benefits.append(Util.toMoneyFormat(weekdayDessert * -1));
            benefits.append("\n");
        }
        if (weekendMain != 0) {
            benefits.append("주말 할인: ");
            benefits.append(Util.toMoneyFormat(weekendMain * -1));
            benefits.append("\n");
        }
        if (special != 0) {
            benefits.append("특별 할인: ");
            benefits.append(Util.toMoneyFormat(special * -1));
            benefits.append("\n");
        }
        if (freeMenu != null) {
            benefits.append("증정 이벤트: ");
            benefits.append(Util.toMoneyFormat(freeMenu.getPrice() * -1));
            benefits.append("\n");
        }
        return benefits.toString().trim();
    }

    public int getAllBenefitsPrice() {
        if (freeMenu != null) {
            return (christmas + weekdayDessert + weekendMain + special + freeMenu.getPrice());
        }
        return getDiscountPrice();
    }

    public int getDiscountPrice() {
        return (christmas + weekdayDessert + weekendMain + special);
    }

    public String getFreeMenu() {
        if (freeMenu != null) {
            return freeMenu.get();
        }
        return Message.NOT_EXIST.get();
    }

    public void setChristmas(int date) {
        int discount = 1000;
        for (int i = 2; i <= 25; i++) {
            discount += 100;
            if (i == date) {
                break;
            }
        }

        this.christmas = discount;
    }

    public void setWeekdayDessert(int date, List<Order> orders) {
        // 디저트 메뉴 개수 세기
        int count = 0;
        for (Order order : orders) {
            if (Menu.getCategory(order.getMenu()).equals("디저트")) {
                count += order.getCount();
            }
        }
        if (!Util.isWeekend(date)) {
            // 디저트 총액을 고려하여 할인될 수 있는 액수
            int discoutable = 2023 * count;
            this.weekdayDessert = discoutable;  // TODO: 0이하 방지해야 함
            return;
        }
        this.weekdayDessert = 0;
    }

    public void setWeekendMain(int date, List<Order> orders) {
        // 메인 메뉴 개수 세기
        int count = 0;
        for (Order order : orders) {
            if (Menu.getCategory(order.getMenu()).equals("메인")) {
                count++;
            }
        }
        if (Util.isWeekend(date)) {
            int discountable = 2023 * count;
            this.weekendMain = discountable;
        }
        this.weekendMain = 0;
    }

    public void setSpecial(int date) {
        if (SpecialDay.isSpecialDay(date)) {
            this.special = 1000;
            return;
        }
        this.special = 0;
    }

    public void setFreeMenu(int totalPrice) {
        if (totalPrice >= 120000) {
            this.freeMenu = Menu.샴페인;
            return;
        }
        this.freeMenu = null;
    }

    public void setBadge() {

    }

    public void setBenefits(int date, int totalPrice) {

    }
}
