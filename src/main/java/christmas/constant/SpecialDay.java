package christmas.constant;

import java.util.List;

public class SpecialDay {
    private static final List<Integer> specialDays = List.of(3, 10, 17, 24, 25, 31);

    public static boolean isSpecialDay(int date) {
        if (specialDays.contains(date)) {
            return true;
        }
        return false;
    }
}
