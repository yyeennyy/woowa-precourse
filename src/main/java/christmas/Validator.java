package christmas;

import christmas.constant.ErrorMessage;

public class Validator {

    public static int checkDate(String dateInput) throws IllegalArgumentException {
        int date;
        try {
            date = checkInteger(dateInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
        checkDateRange(date);
        return date;
    }

    public static void checkDateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
    }

    public static int checkInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

}