package vendingmachine.domain;

public class Money {
    private static final int MIN_AMOUNT_UNIT = 10;
    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 50_000;

    private int amount;

    public Money(final int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(final int amount) {
        validateAmountUnit(amount);
        validateAmountRange(amount);
    }

    private void validateAmountUnit(final int amount) {
        if (isInvalidAmountUnit(amount)) {
            throw new IllegalArgumentException("10원 단위로 입력해주세요.");
        }
    }

    private boolean isInvalidAmountUnit(final int amount) {
        return amount % MIN_AMOUNT_UNIT != 0;
    }

    private void validateAmountRange(final int amount) {
        if (isInvalidAmountRange(amount)) {
            throw new IllegalArgumentException("100원 이상 5만원 이하의 금액만 입력 가능합니다.");
        }
    }

    private boolean isInvalidAmountRange(final int amount) {
        return amount < MIN_AMOUNT || amount > MAX_AMOUNT;
    }

    public void consumeMoney(final int amount) {
        validate(this.amount - amount);
        this.amount -= amount;
    }

    public int getAmount() {
        return amount;
    }
}
