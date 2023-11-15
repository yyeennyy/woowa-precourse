package christmas.setting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DateConfig {
    public static final Set<Integer> FRI_SET = new HashSet<>(Arrays.asList(1, 8, 15, 22, 29));
    public static final Set<Integer> SAT_SET = new HashSet<>(Arrays.asList(2, 9, 16, 23, 30));
    public static final Set<Integer> SON_SET = new HashSet<>(Arrays.asList(3, 10, 17, 24, 31));
    public static final Set<Integer> MON_SET = new HashSet<>(Arrays.asList(4, 11, 18, 25));
    public static final Set<Integer> TUE_SET = new HashSet<>(Arrays.asList(5, 12, 19, 26));
    public static final Set<Integer> WED_SET = new HashSet<>(Arrays.asList(6, 14, 20, 27));
    public static final Set<Integer> THU_SET = new HashSet<>(Arrays.asList(7, 14, 21, 28));

    public static final int CHRISTMAS = 25;
    public static final int FIRST_DAY = 1;

    public static final int SECOND_DAY = 2;
    private static final List<Integer> specials = List.of(3, 10, 17, 24, CHRISTMAS, 31);

    public static boolean isSpecial(int date) {
        return specials.contains(date);
    }

    public static Week getWeek(int date) {
        if (FRI_SET.contains(date)) return Week.FRI;
        if (SAT_SET.contains(date)) return Week.SAT;
        if (SON_SET.contains(date)) return Week.SON;
        if (MON_SET.contains(date)) return Week.MON;
        if (TUE_SET.contains(date)) return Week.TUE;
        if (WED_SET.contains(date)) return Week.WED;
        if (THU_SET.contains(date)) return Week.THU;
        throw new IllegalArgumentException();
    }

    public static boolean isWeekend(int date) {
        Week week = getWeek(date);
        return week.equals(Week.FRI) || week.equals(Week.SAT);
    }
}
