package christmas.domain;

import christmas.setting.Badge;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BenefitsTest {

    Order setUpNormal() {
        OrderItem main1 = OrderItem.from(Dish.of("크리스마스파스타"), 3);
        OrderItem main2 = OrderItem.from(Dish.of("바비큐립"), 2);
        OrderItem drink1 = OrderItem.from(Dish.of("샴페인"), 1);

        return Order.of(List.of(main1, main2, drink1));
    }

    Order setUpWithOneDessertWeekday() {
        OrderItem ice = OrderItem.from(Dish.of("아이스크림"), 1);
        return Order.of(List.of(ice));
    }

    Order setUpDessertsWeekdayOver10000() {
        OrderItem ice = OrderItem.from(Dish.of("아이스크림"), 4);
        OrderItem cake = OrderItem.from(Dish.of("초코케이크"), 1);

        return Order.of(List.of(ice, cake));
    }

    Order setUpManWeekend() {
        OrderItem main = OrderItem.from(Dish.of("크리스마스파스타"), 1);
        return Order.of(List.of(main));
    }

    @Test
    @DisplayName("[기능]Benefits__일요일 총 할인 혜택 검증")
    void checkAllBenefitsAboutNormalOrder() {
        Order order = setUpNormal();
        Benefits benefits = Benefits.from(order, 24);

        assertThat(benefits.getBenefitsSummary())
                .contains("크리스마스 디데이 할인: -3,300원",
                        "특별 할인: -1,000원", "증정 이벤트: -25,000원");
        assertThat(benefits.getAllBenefits()).isEqualTo((1000 + 100 * 23) + 1000
                + 25000 * 1);
        assertThat(benefits.getFreeMenu()).isEqualTo("샴페인 1개");
        assertThat(benefits.getBadge()).isEqualTo(Badge.SANTA);
    }

    @Test
    @DisplayName("[기능]Benefits__주말 총 할인 혜택 검증")
    void checkAllBenefitsAboutNormalOrderAt30() {
        Order order = setUpNormal();
        Benefits benefits = Benefits.from(order, 30);

        assertThat(benefits.getBenefitsSummary())
                .contains("크리스마스 디데이 할인: -3,400원",
                        "주말 할인: -10,115원", "증정 이벤트: -25,000원");
        assertThat(benefits.getAllBenefits()).isEqualTo((1000 + 100 * 24) + 10115
                + 25000 * 1);
        assertThat(benefits.getFreeMenu()).isEqualTo("샴페인 1개");
        assertThat(benefits.getBadge()).isEqualTo(Badge.SANTA);
    }

    @Test
    @DisplayName("[기능]Benefits__만원 아래라서 이벤트 대상 아닐 때")
    void checkAllBenefitsAboutWeekdayDessert() {
        Order order = setUpWithOneDessertWeekday();
        Benefits benefits = Benefits.from(order, 1);

        assertThat(benefits.getBenefitsSummary())
                .isEqualTo("");
        assertThat(benefits.getAllBenefits()).isEqualTo(0);
        assertThat(benefits.getFreeMenu()).isEqualTo("");
        assertThat(benefits.getBadge()).isNull();
    }

    @Test
    @DisplayName("[기능]Benefits__일요일에 디저트 10000원 이상 시켰을 때")
    void checkAllBenefitsAboutWeekendDessert() {
        Order order = setUpDessertsWeekdayOver10000();
        Benefits benefits = Benefits.from(order, 3);

        assertThat(benefits.getBenefitsSummary())
                .contains("크리스마스 디데이 할인: -1,200원",
                        "특별 할인: -1,000원",
                        "평일 할인: -10,115원");
        assertThat(order.getOriginAmount()).isEqualTo(35000);
        assertThat(benefits.getAllBenefits()).isEqualTo(12315);
        assertThat(benefits.getFreeMenu()).isEqualTo("");
        assertThat(benefits.getBadge()).isEqualTo(Badge.TREE);
    }
}