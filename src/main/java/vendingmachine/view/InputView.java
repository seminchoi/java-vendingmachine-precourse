package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public int inputVendingMachineHoldingAmount() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return readInt();
    }

    private int readInt() {
        String line = Console.readLine();
        try {
            return Integer.parseInt(line.trim());
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }
}
