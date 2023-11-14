package christmas.view;


import christmas.domain.Order;
import christmas.Util;
import christmas.constant.Message;
import christmas.constant.Unit;
import christmas.domain.Benefits;
import christmas.domain.Menu;

import java.util.List;

public class OutputView {

    public void greet() {
        System.out.println(Message.GREETING.get());
    }

    public void startToGuideBenefits() {
        System.out.println(Message.EVENT_BENEFITS_INFO.get());
        newLine();
    }

    public void orderedMenu(List<Order> orders) {
        System.out.println(Message.GUIDE_OF_MENU.get());
        for (Order order : orders) {
            System.out.printf("%s %d%s\n", order.getMenu(), order.getCount(), Unit.COUNT.get());
        }
        newLine();
    }

    public void printPriceBeforeBenefits(int price) {
        System.out.println(Message.GUIDE_OF_ORIGIN_PRICE.get());
        System.out.println(Util.toMoneyFormat(price));
        newLine();
    }

    public void freeMenu(Benefits benefits) {
        System.out.println(Message.GUIDE_OF_FREE_MENU.get());
        if (benefits == null) {
            System.out.println(Message.NOT_EXIST.get());
            newLine();
            return;
        }
        System.out.println(benefits.getFreeMenu() + " 1개");
        newLine();
    }

    public void benefitsList(Benefits benefits) {
        System.out.println(Message.GUID_OF_BENEFITS_NAMES.get());
        if (benefits == null) {
            System.out.println(Message.NOT_EXIST.get());
            newLine();
            return;
        }
        System.out.println(benefits.getAllBenefitsList());
        newLine();
    }

    public void totalBenefitsMoney(Benefits benefits) {
        System.out.println(Message.GUIDE_OF_AMOUNT_OF_BENEFIT.get());
        if (benefits == null) {
            System.out.println("0원");
            newLine();
            return;
        }
        System.out.println(Util.toMoneyFormat(benefits.getAllBenefitsPrice() * -1));
        newLine();
    }

    public void priceAfterBenefits(int totalPrice, Benefits benefits) {
        System.out.println(Message.GUIDE_OF_BENEFITS_PRICE.get());
        if (benefits == null) {
            System.out.println(Util.toMoneyFormat(totalPrice));
            newLine();
            return;
        }
        System.out.println(Util.toMoneyFormat(totalPrice - benefits.getDiscountPrice()));
        newLine();
    }

    public void aboutBadge(Benefits benefits) {
        System.out.println(Message.GUIDE_EVENT_BADGE.get());
        if (benefits == null) {
            System.out.println(Message.NOT_EXIST.get());
            newLine();
            return;
        }
        int benefitPrice = benefits.getAllBenefitsPrice();
        if (benefitPrice >= 20000) {
            System.out.println("산타");
            return;
        }
        if (benefitPrice >= 10000) {
            System.out.println("트리");
            return;
        }
        if (benefitPrice >= 5000) {
            System.out.println("별");
            return;
        }
        System.out.println("없음");
    }

    public void newLine() {
        System.out.println();
    }

    public void print(String message) {
        System.out.println(message);
    }

}
