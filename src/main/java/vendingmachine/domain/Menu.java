package vendingmachine.domain;

public class Menu {
    private static final int MIN_STOCK_QUANTITY = 1;
    private static final int MAX_STOCK_QUANTITY = 100;

    private final String name;
    private final Money price;
    private int stockQuantity;

    public Menu(final String name, final int price, int stockQuantity) {
        validate(stockQuantity);
        this.name = name;
        this.price = new Money(price);
        this.stockQuantity = stockQuantity;
    }

    private void validate(final int stockQuantity) {
        if (isInvalidStockQuantityRange(stockQuantity)) {
            throw new IllegalArgumentException("재고 수량 범위는 1 ~ 100 입니다.");
        }
    }

    private boolean isInvalidStockQuantityRange(final int stockQuantity) {
        return stockQuantity < MIN_STOCK_QUANTITY || stockQuantity > MAX_STOCK_QUANTITY;
    }

    public void purchaseMenu(final Money money) {
        if(!canPurchase(money)) {
            throw new IllegalArgumentException("재고가 없거나 투입 금액이 부족합니다.");
        }
        stockQuantity--;
        money.consumeMoney(price.getAmount());
    }

    public boolean canPurchase(final Money money) {
        return stockQuantity > 0 && price.getAmount() <= money.getAmount() ;
    }

    public String getName() {
        return name;
    }


    public boolean isSoldOut() {
        return stockQuantity == 0;
    }
}
