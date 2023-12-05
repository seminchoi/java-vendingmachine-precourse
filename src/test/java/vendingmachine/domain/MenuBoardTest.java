package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class MenuBoardTest {
    @Test
    @DisplayName("메뉴판을 생성할 때 중복된 이름의 메뉴가 있으면 예외가 발생한다.")
    void givenMenuThenAddedMenu() {
        final Menu menu = new Menu("콜라", 2000, 1);
        final List<Menu> menus = List.of(menu, menu);

        assertThatThrownBy(() -> new MenuBoard(menus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("구매 가능한 메뉴가 있다.")
    void givenMoneyWhenHasAvailableMenuThenTrue() {
        final Menu menu = new Menu("콜라", 2000, 1);
        final List<Menu> menus = List.of(menu);

        final MenuBoard menuBoard = new MenuBoard(menus);
        final boolean hasAvailableMenu = menuBoard.hasAvailableMenu(new Money(2000));

        assertThat(hasAvailableMenu).isTrue();
    }


    @Test
    @DisplayName("존재하지 않는 메뉴를 구매하려 하면 예외가 발생한다.")
    void givenNotExistWhenPurchaseMenuThenThrowException() {
        final MenuBoard menuBoard = new MenuBoard(Collections.emptyList());
        final Money money = new Money(2000);

        assertThatThrownBy(() -> menuBoard.purchaseMenu("콜라", money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("메뉴를 구매하면 매뉴의 재고와 투입 금액이 줄어든다.")
    void givenMoneyWhenPurchaseMenuThenDecreaseStockQuantityAndInsertAmount() {
        final Menu menu = new Menu("콜라", 2000, 1);
        final Money money = new Money(2000);
        final MenuBoard menuBoard = new MenuBoard(Collections.singletonList(menu));

        menuBoard.purchaseMenu("콜라", money);

        assertThat(menu.isSoldOut()).isTrue();
        assertThat(money.getAmount()).isEqualTo(0);
    }
}
