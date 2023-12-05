package vendingmachine.dto;

import java.util.Map;
import java.util.StringJoiner;

public record CoinsResponseDto(Map<Integer, Integer> coins) {
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
