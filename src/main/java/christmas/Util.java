package christmas;

import christmas.setting.Unit;

import java.text.DecimalFormat;

public class Util {

    public static String toMoneyFormat(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number) + Unit.WON.get();
    }

}