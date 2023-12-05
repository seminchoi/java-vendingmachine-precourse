package vendingmachine.domain;

public class Menu {
    private static final int MIN_STOCK_QUANTITY = 1;
    private static final int MAX_STOCK_QUANTITY = 100;

    private final String name;
    private final Money price;
    private int stockQuantity;

    public Menu(final String name, final int price, int stockQuantity) {
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

    public void purchaseMenu() {
        stockQuantity--;
    }

    public String getName() {
        return name;
    }
}