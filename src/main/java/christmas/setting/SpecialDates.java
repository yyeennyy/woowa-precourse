package christmas.setting;

import java.util.List;

public class SpecialDates {
    public static final int CHRISTMAS = 25;
    public static final int SECOND_DAY = 2;
    private static final List<Integer> specials = List.of(3, 10, 17, 24, CHRISTMAS, 31);

    public static boolean isSpecial(int date) {
        return specials.contains(date);
    }
}
