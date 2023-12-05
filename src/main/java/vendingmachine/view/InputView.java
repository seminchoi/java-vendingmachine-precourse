package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.MenuRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    public int inputVendingMachineHoldingAmount() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return readInt();
    }

    public int inputInsertAmount() {
        System.out.println("투입 금액을 입력해 주세요.");
        return readInt();
    }

    private int readInt() {
        String line = Console.readLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public List<MenuRequestDto> inputMenus() {
        System.out.println("상품명과 가격, 수량을 입력해 주세요.");
        final String input = Console.readLine();
        return parseMenus(input);
    }

    private List<MenuRequestDto> parseMenus(String input) {
        List<MenuRequestDto> menuList = new ArrayList<>();

        String[] splitMenu = input.split(";");
        for (String menuInput : splitMenu) {
            menuList.add(parseMenu(menuInput));
        }

        return menuList;
    }

    private MenuRequestDto parseMenu(String menuInput) {
        //TODO : 정규식 공부

        if (!(menuInput.startsWith("[") && menuInput.endsWith("]"))) {
            throw new IllegalArgumentException("올바른 메뉴 형식이 아닙니다.");
        }
        try {
            final String substring = menuInput.substring(1, menuInput.length() - 1);
            final String[] split = substring.split(",");
            final String name = split[0];
            final int price = Integer.parseInt(split[1]);
            final int stockQuantity = Integer.parseInt(split[2]);

            return new MenuRequestDto(name, price, stockQuantity);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalArgumentException("올바른 메뉴 형식이 아닙니다.");
        }
    }

    public String inputMenuName() {
        System.out.println("구매할 상품명을 입력해 주세요.");
        return Console.readLine();
    }
}
