package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class Coins {
    private final Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

    public void addCoin(final Coin coin) {
        int coinCount = coins.getOrDefault(coin, 0);
        coins.put(coin, coinCount + 1);
    }
}
