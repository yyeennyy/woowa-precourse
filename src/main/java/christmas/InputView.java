package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;

public class InputView {

    public int reserveDate() throws IllegalArgumentException {
        System.out.println(Message.INPUT_VISIT_DATE.get());
        String input = Console.readLine();
        int date = Validator.checkDate(input);
        return date;
    }

    public List<Order> orderMenu() {
        System.out.println(Message.INPUT_MENU.get());
        List<String> orderInputs = List.of(Console.readLine().split(","));
        Map<String, Integer> orderInfo = new LinkedHashMap<>();
        for (String input : orderInputs) {
            List<String> menuAndCount = List.of(input.split("-"));
            String menu = menuAndCount.get(0);
            int count = Integer.parseInt(menuAndCount.get(1));
            if(orderInfo.containsKey(menu)) {
                orderInfo.put(menu, orderInfo.get(menu) + count);
                continue;
            }
            orderInfo.put(menu, count);
        }

        List<Order> orders = new ArrayList<>();
        for (Map.Entry<String, Integer> menuAndCount : orderInfo.entrySet()) {
            String menu = menuAndCount.getKey();
            int count = menuAndCount.getValue();
            orders.add(new Order(new Dish(menu), count));
        }
        return orders;
    }
}
