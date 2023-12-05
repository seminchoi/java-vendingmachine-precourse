package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class MenuTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 101})
    @DisplayName("유효하지 않은 재고 수량 입력시 예외가 발생한다.")
    void givenInvalidStockQuantityWhenConstructorThenThrowException(final int stockQuantity) {
        assertThatThrownBy(() -> new Menu("콜라", 2000, stockQuantity))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("부족한 금액으로 메뉴를 구매하려 하면 예외가 발생한다.")
    void givenFewMoneyWhenPurchaseThenThrowException() {
        final Menu menu = new Menu("콜라", 2000, 1);
        final Money money = new Money(1000);

        assertThatThrownBy(() -> menu.purchaseMenu(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("현재 가진 돈으로 구매 가능한 메뉴인지 확인한다.")
    void givenMoneyWhenCanPurchaseThenTrue() {
        final Menu menu = new Menu("콜라", 2000, 1);
        final Money money1 = new Money(1990);
        final Money money2 = new Money(2000);

        boolean canPurchase1 = menu.canPurchase(money1);
        boolean canPurchase2 = menu.canPurchase(money2);

        assertThat(canPurchase1).isFalse();
        assertThat(canPurchase2).isTrue();
    }
}
