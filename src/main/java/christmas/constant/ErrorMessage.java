package christmas.constant;

public enum ErrorMessage {
    INVALID_DATE("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_MENU_NAME("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_COUNT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    INVALID_MENU_FORMAT("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    DUPLICATED_MENU("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    OVERFLOW_MENU_COUNT("[ERROR] 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다. 다시 입력해 주세요."),
    DRINK_ONLY("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");


    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
