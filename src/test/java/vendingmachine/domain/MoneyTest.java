package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class MoneyTest {
    @ParameterizedTest
    @ValueSource(ints = {5, 90, 2_023, 50_010, 60_000})
    @DisplayName("유효하지 않은 금액을 입력 시 예외가 발생한다.")
    void givenInvalidAmountWhenConstructorThenThrowException(final int amount) {
        assertThatThrownBy(()-> new Money(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {100, 110, 50_000})
    @DisplayName("유효한 금액ㅇ르 입력시 정상적으로 금액을 생성한다.")
    void givenValidAmountWhenConstructorThenThrowException(final int amount) {
        Money money = new Money(amount);
    }
}
