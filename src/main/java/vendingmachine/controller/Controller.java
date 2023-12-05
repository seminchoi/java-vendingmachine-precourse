package vendingmachine.controller;

import vendingmachine.domain.Coins;
import vendingmachine.domain.Menu;
import vendingmachine.domain.MenuBoard;
import vendingmachine.domain.Money;
import vendingmachine.domain.RandomCoinGenerator;
import vendingmachine.domain.VendingMachine;
import vendingmachine.dto.CoinsResponseDto;
import vendingmachine.dto.MenuRequestDto;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;

    public Controller(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        final VendingMachine vendingMachine = initVendingMachine();
        purchaseMenuUntilAvailable(vendingMachine);
    }

    private VendingMachine initVendingMachine() {
        final Coins havingCoins = initVendingMachineCoins();
        final MenuBoard menuBoard = initMenuBoard();
        final Money money = insertMoney();
        VendingMachine vendingMachine = new VendingMachine(havingCoins, menuBoard);

        vendingMachine.inputMoney(money);

        return vendingMachine;
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

    private MenuBoard initMenuBoard() {
        try {
            final List<Menu> menus = inputView.inputMenus().stream()
                    .map(MenuRequestDto::toDomain)
                    .toList();
            return new MenuBoard(menus);
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return initMenuBoard();
        }
    }

    private Money insertMoney() {
        try {
            final int amount = inputView.inputInsertAmount();
            return new Money(amount);
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            return insertMoney();
        }
    }

    private void purchaseMenuUntilAvailable(final VendingMachine vendingMachine) {
        while (vendingMachine.hasPurchasableMenu()) {
            purchaseMenu(vendingMachine);
        }

        outputView.printInsertAmount(vendingMachine.getInsertAmount());
        Coins coins = vendingMachine.giveChanges();
        outputView.printChanges(CoinsResponseDto.of(coins));
    }

    private void purchaseMenu(final VendingMachine vendingMachine) {
        try {
            outputView.printInsertAmount(vendingMachine.getInsertAmount());
            String menuName = inputView.inputMenuName();
            vendingMachine.purchaseMenu(menuName);
        } catch (IllegalArgumentException e) {
            outputView.printError(e);
            purchaseMenu(vendingMachine);
        }
    }
}
