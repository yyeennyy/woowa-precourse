package christmas;

public class Validator {

    public static int checkDate(String dateInput) throws IllegalArgumentException {
        int date;
        try {
            date = checkInteger(dateInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.get());
        }
        return date;
    }

    public static int checkInteger(String input) throws NumberFormatException {
        return Integer.parseInt(input);
    }

}