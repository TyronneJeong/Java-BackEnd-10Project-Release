package org.example.restaurant;

import java.util.List;

public class Menu {

    private final List<MenuItem> menuItems;

    public Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItem choose(String name) {
        return this.menuItems.stream()
                .filter(menuItem -> menuItem.matches(name)) // true 인것만 리턴 된다.
                .findFirst() // 첫번째 항목을 리턴한다.
                .orElseThrow(() -> new IllegalArgumentException("해당 하는 메뉴 아이탬 항목이 없습니다.")); // 해당 항목이 없는 경우 예외가 발생된다.
    }
}
