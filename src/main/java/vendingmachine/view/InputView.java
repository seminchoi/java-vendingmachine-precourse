package vendingmachine.view;

import camp.nextstep.edu.missionutils.Console;
import vendingmachine.dto.MenuRequestDto;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private final static String MENU_REGEX = "\\[(.*+),(\\d+),(\\d+)\\]";
    public int inputVendingMachineHoldingAmount() {
        System.out.println("자판기가 보유하고 있는 금액을 입력해 주세요.");
        return readInt();
    }

    public int readInt() {
        String line = Console.readLine();
        try {
            return Integer.parseInt(line.trim());
        }
        catch (NumberFormatException e) {
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
        Pattern pattern = Pattern.compile(MENU_REGEX);
        Matcher matcher = pattern.matcher(menuInput);

        //TODO : 정규식 공부
        if(!matcher.matches()) {
            throw new IllegalArgumentException("올바른 입력 형식이 아닙니다.");
        }

        String name = matcher.group(1);
        int price = Integer.parseInt(matcher.group(2));
        int stockQuantity = Integer.parseInt(matcher.group(3));

        return new MenuRequestDto(name, price, stockQuantity);
    }
}
