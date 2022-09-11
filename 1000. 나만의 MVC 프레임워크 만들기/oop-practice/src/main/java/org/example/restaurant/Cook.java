package org.example.restaurant;

import java.util.Objects;

public class Cook {
    // 인스턴스 생성을 통해 선언된 내용은 영속성을 위해 final 로 선언한다.
    // final 로 선언시 최초 대입 이후 데이터 변경이 불가능 하다.
    private final String name;
    private final int price;

    public Cook(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Cook(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cook cook = (Cook) o;
        return price == cook.price && Objects.equals(name, cook.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
