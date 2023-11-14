package christmas;

import christmas.constant.Message;
import christmas.domain.Benefits;
import christmas.domain.Menu;
import christmas.domain.Order;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class EventController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private int date;
    private List<Order> orders;
    private Benefits benefits;

    public void startReservation() {
        outputView.greet();
    }

    public void inputDate() {
        while (true) {
            try {
                date = inputView.reserveDate();
                break;
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    public void inputOrder() {
        while (true) {
            try {
                orders = inputView.orderMenu();
                break;
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    public void setBenefits() {
        int totalPrice = 0;
        for (Order order : orders) {
            totalPrice += Menu.getPrice(order.getMenu()) * order.getCount();
        }
        if (totalPrice >= 10000) {
            benefits = new Benefits();
            benefits.setChristmas(date);
            benefits.setFreeMenu(totalPrice);
            benefits.setWeekdayDessert(date, orders);
            benefits.setWeekendMain(date, orders);
            benefits.setSpecial(date);

            benefits.setBadge(); // 총혜택금액에 따라서 제공되는 배지가 다름
        }
    }

    public void previewBenefits() {
        outputView.startToGuideBenefits();
        outputView.orderedMenu(orders);
        int totalPrice = outputView.priceBeforeBenefits(orders);
        outputView.freeMenu(benefits);
        outputView.benefitsList(benefits);
        outputView.totalBenefitsMoney(benefits);
        outputView.priceAfterBenefits(totalPrice, benefits);
        outputView.aboutBadge(benefits);
    }
}
