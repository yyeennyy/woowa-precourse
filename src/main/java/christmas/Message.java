package christmas;

public enum Message {
    GREETING("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다."),
    INPUT_VISIT_DATE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    INPUT_MENU("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)"),
    EVENT_BENEFITS_INFO("12월 26일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    GUIDE_OF_MENU("<주문 메뉴>"),
    GUIDE_OF_ORIGIN_PRICE("<할인 전 총주문 금액>"),
    GUIDE_OF_FREE_MENU("<증정 메뉴>"),
    GUID_OF_BENEFITS_NAMES("<혜택 내역>"),
    GUIDE_OF_AMOUNT_OF_BENEFIT("<총 혜택 금액>"),
    GUIDE_OF_BENEFITS_PRICE("<할인 후 예상 결제 금액>"),
    GUIDE_EVENT_BADGE("<12월 이벤트 배지>"),
    NOT_EXIST("없음");

    private final String message;

    Message(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
