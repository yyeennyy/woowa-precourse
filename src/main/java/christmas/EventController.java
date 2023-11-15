package christmas;

import christmas.domain.Order;
import christmas.setting.EventConfig;
import christmas.domain.Benefits;
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
        if (order.getOriginAmount() >= EventConfig.BENEFIT_THRESHOLD.get()) {
            benefits = Benefits.from(order, date);
        }
    }



    public void printBenefits() {
        int originAmount = order.getOriginAmount();
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
