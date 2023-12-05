package vendingmachine.dto;

public record MenuRequestDto(String name, int price, int stockQuantity) {
}
