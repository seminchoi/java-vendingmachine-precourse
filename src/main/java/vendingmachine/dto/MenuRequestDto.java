package vendingmachine.dto;

import vendingmachine.domain.Menu;

public record MenuRequestDto(String name, int price, int stockQuantity) {
    public Menu toDomain() {
        return new Menu(name, price, stockQuantity);
    }
}
