package vendingmachine.view;

import vendingmachine.dto.CoinsResponseDto;

public class OutputView {
    public void printVendingMachineCoins(CoinsResponseDto coins) {
        System.out.println("\n");
        System.out.println("자판기가 보유한 동전");
        System.out.println(coins);
    }
}
