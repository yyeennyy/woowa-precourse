package christmas;

import java.util.List;

public class EventController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private int date;
    private List<Order> orders;

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
        orders = inputView.orderMenu();
    }

    public void previewBenefits() {
        outputView.startToGuideBenefits();
        outputView.orderedMenu(orders);
        outputView.priceBeforeBenefits();
        outputView.freeMenu();
        outputView.benefitsList();
        outputView.totalBenefitsMoney();
        outputView.priceAfterBenefits();
        outputView.aboutBadge();
    }
}
