package vendingmachine.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class RandomCoinGenerator {
    public Coins generateCoins(final Money money) {
        final Coins coins = new Coins();
        while (money.getAmount() > 0) {
            final List<Integer> amounts = Coin.getAvailableCoinAmounts();
            final Coin coin = generateCoin(amounts, money);
            coins.addCoin(coin);
        }
        return coins;
    }

    public Coin generateCoin(final List<Integer> amounts, final Money money) {
        removeAllExceededAmount(amounts, money);
        final int amount = Randoms.pickNumberInList(amounts);
        money.consumeMoney(amount);
        return Coin.findByAmount(amount);
    }

    private void removeAllExceededAmount(final List<Integer> amounts, final Money money) {
        while (!amounts.isEmpty() && amountIsUnderMaxCoin(amounts, money)) {
            amounts.remove(0);
        }
    }

    private boolean amountIsUnderMaxCoin(final List<Integer> amounts, final Money money) {
        return amounts.get(0) > money.getAmount();
    }
}
