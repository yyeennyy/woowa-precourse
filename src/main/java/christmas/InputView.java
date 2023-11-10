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

    public List<Order> orderMenu() throws IllegalArgumentException {
        System.out.println(Message.INPUT_MENU.get());
        List<String> orderInputs = List.of(Console.readLine().split(","));
        Map<String, Integer> orderInfo = new LinkedHashMap<>();
        for (String input : orderInputs) {
            List<String> menuAndCount = List.of(input.split("-"));
            String menu = menuAndCount.get(0);
            // 메뉴판에 있는 메뉴만 가능
            if (!Menu.exist(menu)) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_MENU_NAME.get());
            }
            int count = Integer.parseInt(menuAndCount.get(1));
            if(orderInfo.containsKey(menu)) {
                throw new IllegalArgumentException(ErrorMessage.DUPLICATED_MENU.get());
            }
            orderInfo.put(menu, count);
        }

        List<Order> orders = new ArrayList<>();
        int countSum = 0;
        for (Map.Entry<String, Integer> menuAndCount : orderInfo.entrySet()) {
            String menu = menuAndCount.getKey();
            int count = menuAndCount.getValue();
            countSum += count;
            orders.add(new Order(new Dish(menu), count));
        }
        // 음료만 주문 불가
        if (isDrinkOnly(orders)) {
            throw new IllegalArgumentException(ErrorMessage.DRINK_ONLY.get());
        }
        // 20개까지 주문 가능
        if (countSum > 20) {
            throw new IllegalArgumentException(ErrorMessage.OVERFLOW_MENU_COUNT.get());
        }
        return orders;
    }

    private boolean isDrinkOnly(List<Order> orders) {
        for (Order order : orders) {
            if (!Menu.getCategory(order.getMenu()).equals("음료")) {
                return false;
            }
        }
        return true;
    }
}
