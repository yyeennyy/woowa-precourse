package christmas;


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

    public void priceBeforeBenefits(List<Order> orders) {
        System.out.println(Message.GUIDE_OF_ORIGIN_PRICE.get());
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += Menu.getPrice(order.getMenu());
        }
        System.out.println(Util.toMoneyFormat(totalPrice) + Unit.WON.get());
        newLine();
    }

    public void freeMenu() {
        System.out.println(Message.GUIDE_OF_FREE_MENU.get());
        newLine();
    }

    public void benefitsList() {
        System.out.println(Message.GUID_OF_BENEFITS_NAMES.get());
        newLine();
    }

    public void totalBenefitsMoney() {
        System.out.println(Message.GUIDE_OF_AMOUNT_OF_BENEFIT.get());
        newLine();
    }

    public void priceAfterBenefits() {
        System.out.println(Message.GUIDE_OF_BENEFITS_PRICE.get());
        newLine();
    }

    public void aboutBadge() {
        System.out.println(Message.GUIDE_EVENT_BADGE.get());
        newLine();
    }

    public void newLine() {
        System.out.println();
    }

    public void print(String message) {
        System.out.println(message);
    }

}
