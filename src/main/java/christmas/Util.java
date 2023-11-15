package christmas;

import christmas.setting.Unit;
import christmas.setting.Week;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {

    private static final Set<Integer> FRI_SET = new HashSet<>(Arrays.asList(1, 8, 15, 22, 29));
    private static final Set<Integer> SAT_SET = new HashSet<>(Arrays.asList(2, 9, 16, 23, 30));
    private static final Set<Integer> SON_SET = new HashSet<>(Arrays.asList(3, 10, 17, 24, 31));
    private static final Set<Integer> MON_SET = new HashSet<>(Arrays.asList(4, 11, 18, 25));
    private static final Set<Integer> TUE_SET = new HashSet<>(Arrays.asList(5, 12, 19, 26));
    private static final Set<Integer> WED_SET = new HashSet<>(Arrays.asList(6, 14, 20, 27));
    private static final Set<Integer> THU_SET = new HashSet<>(Arrays.asList(7, 14, 21, 28));


    public static String toMoneyFormat(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number) + Unit.WON.get();
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