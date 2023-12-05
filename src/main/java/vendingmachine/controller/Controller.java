package vendingmachine.controller;

import vendingmachine.domain.Coins;
import vendingmachine.domain.Money;
import vendingmachine.domain.RandomCoinGenerator;
import vendingmachine.dto.CoinsResponseDto;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Coins havingCoins = initVendingMachineCoins();
    }

    private Coins initVendingMachineCoins() {
        try {
            final int amount = inputView.inputVendingMachineHoldingAmount();
            final Money money = new Money(amount);
            RandomCoinGenerator randomCoinGenerator = new RandomCoinGenerator();

            Coins coins = randomCoinGenerator.generateCoins(money);
            outputView.printVendingMachineCoins(CoinsResponseDto.of(coins));

            return coins;
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return initVendingMachineCoins();
        }
    }
}
