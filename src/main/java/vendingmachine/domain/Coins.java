package vendingmachine.domain;

import java.util.EnumMap;
import java.util.Map;

public class Coins {
    private final Map<Coin, Integer> coins = new EnumMap<>(Coin.class);

    public int calculateTotalAmount() {
        int totalAmount = 0;
        for (final Coin coin : coins.keySet()) {
            totalAmount += calculateCoinAmount(coin);
        }

        return totalAmount;
    }

    private int calculateCoinAmount(final Coin coin) {
        return coin.getAmount() * coins.get(coin);
    }

    public void addCoin(final Coin coin) {
        final int coinCount = coins.getOrDefault(coin, 0);
        coins.put(coin, coinCount + 1);
    }
}
