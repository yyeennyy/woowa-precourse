package christmas;

import christmas.setting.Unit;
import christmas.setting.Week;
import java.text.DecimalFormat;
import java.util.List;

public class Util {
    public static String toMoneyFormat(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number) + Unit.WON.get();
    }

    // 개선
    public static Week getWeek(int date) {
        List<Integer> fri = List.of(1, 8, 15, 22, 29);
        List<Integer> sat = List.of(2, 9, 16, 23, 30);
        List<Integer> son = List.of(3, 10, 17, 24, 31);
        List<Integer> mon = List.of(4, 11, 18, 25);
        List<Integer> tue = List.of(5, 12, 19, 26);
        List<Integer> wed = List.of(6, 14, 20, 27);
        List<Integer> thu = List.of(7, 14, 21, 28);

        if (fri.contains(date)) {
            return Week.FRI;
        }
        if (sat.contains(date)) {
            return Week.SAT;
        }
        if (son.contains(date)) {
            return Week.SON;
        }
        if (mon.contains(date)) {
            return Week.MON;
        }
        if (tue.contains(date)) {
            return Week.TUE;
        }
        if (wed.contains(date)) {
            return Week.WED;
        }
        if (thu.contains(date)) {
            return Week.THU;
        }
        throw new IllegalArgumentException();
    }

    public static boolean isWeekend(int date) {
        Week week = getWeek(date);

        return week.equals(Week.FRI) || week.equals(Week.SAT);
    }

    public static String newLine() {
        return "\n";
    }
}
