package christmas.constant;

import java.io.Console;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다."),
    INVALID_MENU_NAME("유효하지 않은 주문입니다."),
    INVALID_MENU_COUNT("유효하지 않은 주문입니다."),
    INVALID_MENU_FORMAT("유효하지 않은 주문입니다."),
    DUPLICATED_MENU("유효하지 않은 주문입니다."),
    OVERFLOW_MENU_COUNT("메뉴는 한 번에 최대 %d개까지만 주문할 수 있습니다."),
    DRINK_ONLY("음료만 주문할 수 없습니다.");


    private final String message;
    private static final String PREFIX = "[ERROR]";
    private static final String RETRY = "다시 입력해 주세요.";
    private static final String SPACE = " ";

    ErrorMessage(String message) {
        this.message = message;
    }

    public String get() {
        if (this == OVERFLOW_MENU_COUNT) {
            return PREFIX + SPACE + String.format(message, Config.MAX_MENU_COUNT.get()) + SPACE + RETRY;
        }
        return PREFIX + SPACE + message + SPACE + RETRY;
    }
}
