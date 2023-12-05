package vendingmachine.domain;

public class VendingMachine {
    private final Coins havingCoins;
    private final MenuBoard menuBoard;
    private Money inputMoney;

    public VendingMachine(final Coins havingCoins, final MenuBoard menuBoard) {
        this.havingCoins = havingCoins;
        this.menuBoard = menuBoard;
    }

    public void inputMoney(final int amount) {
        //TODO : 돈을 여러 번 넣을 수 있다면?
        inputMoney = new Money(amount);
    }
}
