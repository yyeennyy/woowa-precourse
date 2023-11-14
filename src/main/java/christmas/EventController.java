package christmas;

import christmas.setting.Config;
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
        int originAmount = getOriginAmount();
        if (originAmount >= Config.BENEFIT_THRESHOLD.get()) {
            benefits = new Benefits();
            benefits.setChristmas(date);
            benefits.setFreeMenu(originAmount);
            benefits.setWeekdayDessert(date, orders);
            benefits.setWeekendMain(date, orders);
            benefits.setSpecial(date);
            benefits.setBadge();
        }
    }

    private int getOriginAmount() {
        int sum = 0;
        for (Order order : orders) {
            sum += Menu.getPrice(order.getMenu()) * order.getCount();
        }
        return sum;
    }

    public void previewBenefits() {
        int originAmount = getOriginAmount();
        outputView.startToGuideBenefits();
        outputView.orderedMenu(orders);
        outputView.printAmountBeforeBenefits(originAmount);
        outputView.freeMenu(benefits);
        outputView.benefitsList(benefits);
        outputView.totalBenefitsMoney(benefits);
        outputView.printAmountAfterBenefits(originAmount, benefits);
        outputView.aboutBadge(benefits);
    }
}
