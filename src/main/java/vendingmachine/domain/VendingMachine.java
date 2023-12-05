package vendingmachine.domain;

public class VendingMachine {
    private final Coins havingCoins;
    private final MenuBoard menuBoard;
    private Money inputMoney;

    public VendingMachine(final Coins havingCoins, final MenuBoard menuBoard) {
        this.havingCoins = havingCoins;
        this.menuBoard = menuBoard;
    }

    public void inputMoney(final Money money) {
        //TODO : 돈을 여러 번 넣을 수 있다면?
        inputMoney = money;
    }

    public boolean hasPurchasableMenu() {
        return menuBoard.hasAvailableMenu(inputMoney);
    }

    public void purchaseMenu(final String menuName) {
        menuBoard.purchaseMenu(menuName, inputMoney);
    }

    public Coins giveChanges() {
        return havingCoins.giveChange(inputMoney);
    }

    public int getInputAmount() {
        return inputMoney.getAmount();
    }
}
