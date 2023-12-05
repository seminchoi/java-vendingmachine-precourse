package vendingmachine.view;

import vendingmachine.dto.CoinsResponseDto;

public class OutputView {
    private static final String ERROR_FORMAT = "[ERROR] %s\n";
    public void printVendingMachineCoins(CoinsResponseDto coins) {
        System.out.println("\n자판기가 보유한 동전");
        System.out.println(coins);
    }

    public void printError(Exception e) {
        System.out.printf(ERROR_FORMAT, e.getMessage());
    }

    public void printInsertAmount(int insertAmount) {
        System.out.printf("\n투입 금액: %d원\n", insertAmount);
    }

    public void printChanges(CoinsResponseDto coins) {
        System.out.println("잔돈");
        System.out.println(coins);
    }
}
