package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.Validator;
import christmas.domain.Dish;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.setting.ErrorMessage;
import christmas.setting.InputConfig;
import christmas.setting.Message;
import java.util.ArrayList;
import java.util.List;

public class InputView {

    public int reserveDate() throws IllegalArgumentException {
        System.out.println(Message.INPUT_VISIT_DATE.get());
        String input = Console.readLine();
        int date = Validator.checkDate(input);
        Message.INPUT_VISIT_DATE.setDate(date);
        return date;
    }

    public Order orderMenu() throws IllegalArgumentException {
        System.out.println(Message.INPUT_MENU.get());
        String userOrderInput;
        userOrderInput = Console.readLine();
        return getOrder(userOrderInput);
    }

    Order getOrder(String input) throws IllegalArgumentException {
        List<String> orderInputs = List.of(input.split(InputConfig.ORDER_MENU_DELIMITER.get()));
        List<OrderItem> orderItems = new ArrayList<>();

        for (String orderInput : orderInputs) {
            List<String> menuAndCount = List.of(orderInput.split(InputConfig.MENU_AND_COUNT_DELIMITER.get()));
            if (menuAndCount.size() != 2) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_FORMAT.get());
            }
            Dish dish = Dish.of(menuAndCount.get(0));
            int count = OrderItem.checkOrderCount(menuAndCount.get(1));
            OrderItem orderItem = OrderItem.from(dish, count);
            orderItems.add(orderItem);
        }
        return Order.of(orderItems);
    }
}
