package vendingmachine.view;

import vendingmachine.dto.CoinsResponseDto;

public class OutputView {
    private static final String ERROR_FORMAT = "[ERROR] %s\n";
    public void printVendingMachineCoins(CoinsResponseDto coins) {
        System.out.println("\n");
        System.out.println("자판기가 보유한 동전");
        System.out.println(coins);
    }

    public void printError(Exception e) {
        System.out.printf(ERROR_FORMAT, e.getMessage());
    }
}
