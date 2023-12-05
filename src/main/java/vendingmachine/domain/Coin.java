package vendingmachine.domain;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Coin {
    COIN_500(500),
    COIN_100(100),
    COIN_50(50),
    COIN_10(10);

    private final int amount;

    Coin(final int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    private static final Map<Integer, Coin> amountCoin = Stream.of(values())
            .collect(Collectors.toUnmodifiableMap(Coin::getAmount, Function.identity()));

    public static List<Integer> getAvailableCoinAmounts() {
        final List<Integer> coinAmounts = new LinkedList<>();

        for (final Coin coin : values()) {
            coinAmounts.add(coin.amount);
        }
        coinAmounts.sort(Comparator.reverseOrder());
        return coinAmounts;
    }

    public static Coin findByAmount(final int amount) {
        return amountCoin.get(amount);
    }
}
