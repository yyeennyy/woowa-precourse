package christmas;

public class Application {
    public static void main(String[] args) {
        EventController eventController = new EventController();

        eventController.startReservation();
        eventController.inputDate();
        eventController.inputOrder();

        eventController.setBenefits();
        eventController.printBenefits();
    }
}