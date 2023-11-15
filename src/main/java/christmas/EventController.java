package christmas;

import christmas.domain.Order;
import christmas.setting.EventConfig;
import christmas.domain.Benefits;
import christmas.setting.Menu;
import christmas.domain.OrderItem;
import christmas.view.InputView;
import christmas.view.OutputView;

public class EventController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private int date;
    private Order order;
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
                order = inputView.orderMenu();
                break;
            } catch (IllegalArgumentException e) {
                outputView.print(e.getMessage());
            }
        }
    }

    public void setBenefits() {
        int originAmount = getOriginAmount();
        if (originAmount >= EventConfig.BENEFIT_THRESHOLD.get()) {
            benefits = new Benefits();
            benefits.setChristmas(date);
            benefits.setFreeMenu(originAmount);
            benefits.setWeekdayDessert(date, order);
            benefits.setWeekendMain(date, order);
            benefits.setSpecial(date);
            benefits.setBadge();
        }
    }

    private int getOriginAmount() {
        int sum = 0;
        for (OrderItem orderItem : order.getOrderItems()) {
            sum += Menu.getPrice(orderItem.getMenuName()) * orderItem.getCount();
        }
        return sum;
    }

    public void previewBenefits() {
        int originAmount = getOriginAmount();
        outputView.startToGuideBenefits();
        outputView.orderedMenu(order);
        outputView.printAmountBeforeBenefits(originAmount);
        outputView.freeMenu(benefits);
        outputView.benefitsList(benefits);
        outputView.totalBenefitsMoney(benefits);
        outputView.printAmountAfterBenefits(originAmount, benefits);
        outputView.aboutBadge(benefits);
    }
}
