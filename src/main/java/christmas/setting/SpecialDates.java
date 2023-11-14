package christmas.setting;

import java.util.List;

public class SpecialDates {
    public static final int CHRISTMAS = 25;
    public static final int SECOND_DAY = 2;
    private static final List<Integer> specialDays = List.of(3, 10, 17, 24, CHRISTMAS, 31);

    public static boolean isSpecialDay(int date) {
        return specialDays.contains(date);
    }
}
