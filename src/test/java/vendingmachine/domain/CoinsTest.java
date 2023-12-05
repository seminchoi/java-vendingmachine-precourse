package vendingmachine.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

public class CoinsTest {
    @Test
    @DisplayName("코인을 한 개씩 추가한다.")
    void givenCoinWhenAddCoinThenAddedCoin() {
        Coins coins = new Coins();
        coins.addCoin(Coin.COIN_500);
        coins.addCoin(Coin.COIN_500);
        coins.addCoin(Coin.COIN_100);
        coins.addCoin(Coin.COIN_10);
        Map<Coin, Integer> coinsResult = coins.getCoins();

        assertThat(coinsResult.get(Coin.COIN_500)).isEqualTo(2);
        assertThat(coinsResult.get(Coin.COIN_100)).isEqualTo(1);
        assertThat(coinsResult.get(Coin.COIN_10)).isEqualTo(1);
    }

    @Test
    @DisplayName("코인을 여러 개 추가한다.")
    void givenCoinsWhenAddCoinWithCountThenAddedCoin() {
        Coins coins = new Coins();
        coins.addCoin(Coin.COIN_500, 5);

        Map<Coin, Integer> coinsResult = coins.getCoins();

        assertThat(coinsResult.get(Coin.COIN_500)).isEqualTo(5);
    }

    @Test
    @DisplayName("잔돈을 알맞게 반환한다. - 정상 반환 가능 시")
    void givenMoneyWhenGiveChangeWThenChangeCoins() {
        Coins coins = new Coins();
        coins.addCoin(Coin.COIN_500, 5);
        coins.addCoin(Coin.COIN_100, 10);

        Coins changedCoins = coins.giveChange(new Money(1000));
        Map<Coin, Integer> coinsResult = changedCoins.getCoins();

        assertThat(coinsResult.get(Coin.COIN_500)).isEqualTo(2);
    }

    @Test
    @DisplayName("잔돈을 알맞게 반환한다. - 잔돈이 부족할 시")
    void givenMoneyWhenGiveChangeWThenChangeCoinsMaximum() {
        Coins coins = new Coins();
        coins.addCoin(Coin.COIN_500, 1);
        coins.addCoin(Coin.COIN_100, 10);

        Money money = new Money(1010);
        Coins changedCoins = coins.giveChange(money);
        Map<Coin, Integer> coinsResult = changedCoins.getCoins();

        assertThat(coinsResult.get(Coin.COIN_500)).isEqualTo(1);
        assertThat(coinsResult.get(Coin.COIN_100)).isEqualTo(5);
        assertThat(money.getAmount()).isEqualTo(10);
    }
}
