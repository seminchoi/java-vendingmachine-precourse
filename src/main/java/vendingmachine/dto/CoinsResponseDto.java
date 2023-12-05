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
        StringJoiner joiner = new StringJoiner("\n");
        for (Integer coin : coins.keySet()) {
            joiner
                    .add(coin.toString()).add("원")
                    .add(" - ")
                    .add(coins.get(coin).toString()).add("개");
        }

        return joiner.toString();
    }
}
