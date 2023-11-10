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

    public int priceBeforeBenefits(List<Order> orders) {
        System.out.println(Message.GUIDE_OF_ORIGIN_PRICE.get());
        int totalPrice = getTotalPrice(orders);
        System.out.println(Util.toMoneyFormat(totalPrice));
        newLine();
        return totalPrice;
    }

    private int getTotalPrice(List<Order> orders) {
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += Menu.getPrice(order.getMenu()) * order.getCount();
        }
        return totalPrice;
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
        newLine();
    }

    public void newLine() {
        System.out.println();
    }

    public void print(String message) {
        System.out.println(message);
    }

}
