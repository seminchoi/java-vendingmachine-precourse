package vendingmachine.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuBoard {
    private final Map<String, Menu> menus = new HashMap<>();

    public MenuBoard(final List<Menu> menus) {
        for (final Menu menu : menus) {
            addMenu(menu);
        }
    }

    private void addMenu(final Menu menu) {
        if(menus.containsKey(menu.getName())) {
            throw new IllegalArgumentException("메뉴 이름은 중복될 수 없습니다.");
        }
        menus.put(menu.getName(), menu);
    }

    public boolean hasAvailableMenu(final Money money) {
        for (Menu menu : menus.values()) {
            if(canPurchaseMenu(menu, money)) {
                return true;
            }
        }
        return false;
    }

    private boolean canPurchaseMenu(final Menu menu, final Money money) {
        return menu.canPurchase(money);
    }

    public void purchaseMenu(final String menuName, final Money money) {
        if(!menus.containsKey(menuName)) {
            throw new IllegalArgumentException("매진되었거나 존재하지 않는 메뉴입니다.");
        }
        final Menu menu = menus.get(menuName);
        menu.purchaseMenu(money);

        if(menu.isSoldOut()) {
            menus.remove(menuName);
        }
    }
}
