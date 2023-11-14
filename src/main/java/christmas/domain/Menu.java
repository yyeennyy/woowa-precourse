package christmas.domain;

import christmas.constant.Category;

public enum Menu {
    양송이수프(Category.애피타이저,  "양송이수프", 6000),
    타파스(Category.애피타이저, "타파스", 5500),
    시저샐러드(Category.애피타이저, "시저샐러드", 8000),
    티본스테이크(Category.메인, "티본스테이크", 55000),
    바비큐립(Category.메인, "바비큐립", 54000),
    해산물파스타(Category.메인, "해산물파스타", 35000),
    크리스마스파스타(Category.메인, "크리스마스파스타", 25000),
    초코케이크(Category.디저트, "초코케이크", 15000),
    아이스크림(Category.디저트, "아이스크림", 5000),
    제로콜라(Category.음료, "제로콜라", 3000),
    레드와인(Category.음료, "레드와인", 60000),
    샴페인(Category.음료, "샴페인", 25000);

    private final Category category;
    private final String name;
    private final int price;

    Menu(Category category, String name, int price) {
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public static boolean exist(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.get().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static Category getCategory(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.get().equals(menuName)) {
                return menu.getCategory();
            }
        }
        throw new IllegalArgumentException();
    }

    public static int getPrice(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menu.get().equals(menuName)) {
                return menu.getPrice();
            }
        }
        throw new IllegalArgumentException();
    }

    public String get() {
        return this.name;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getPrice() {
        return this.price;
    }


}
