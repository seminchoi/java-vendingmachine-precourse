package vendingmachine.dto;

import vendingmachine.domain.Coin;
import vendingmachine.domain.Coins;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

public record CoinsResponseDto(Map<Integer, Integer> coins) {
    public static CoinsResponseDto of(final Coins coins) {
        final Map<Coin, Integer> coinsInfo = coins.getCoins();

        Map<Integer, Integer> coinsResponse = new LinkedHashMap<>();
        for (Coin coin : coinsInfo.keySet()) {
            coinsResponse.put(coin.getAmount(), coinsInfo.get(coin));
        }

        return new CoinsResponseDto(coinsResponse);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer coin : coins.keySet()) {
            builder
                    .append(coin.toString()).append("원")
                    .append(" - ")
                    .append(coins.get(coin).toString()).append("개")
                    .append("\n");
        }

        return builder.toString();
    }
}
