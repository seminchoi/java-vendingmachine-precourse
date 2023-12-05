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

    public void addCoin(final Coin coin, final int count) {
        if (count == 0) {
            return;
        }
        final int coinCount = coins.getOrDefault(coin, 0);
        coins.put(coin, coinCount + count);
    }

    public Coins giveChange(final Money money) {
        Coins changeCoins = new Coins();
        for (final Coin coin : coins.keySet()) {
            addChanges(changeCoins, coin, money);
            if (money.isZero()) {
                break;
            }
        }

        return changeCoins;
    }

    private void addChanges(final Coins changeCoins, final Coin coin, final Money money) {
        int coinCount = coins.get(coin);
        int addedCount = Math.min(coinCount, money.getAmount() / coin.getAmount());
        money.consumeMoney(coin.getAmount() * addedCount);
        changeCoins.addCoin(coin, addedCount);
    }

    public Map<Coin, Integer> getCoins() {
        return coins;
    }

    public void initCoins() {
        for (Coin coin : Coin.values()) {
            coins.put(coin, 0);
        }
    }
}
