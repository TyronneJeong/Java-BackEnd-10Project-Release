package org.example.restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuTest {

    @DisplayName("메뉴 항목 리턴 테스트")
    @Test
    void chooseTest() {
        Menu menu = new Menu(List.of(new MenuItem("냉면", 7000),
                new MenuItem("만두", 5000),
                new MenuItem("불고기", 13000)
                ));
        MenuItem menuItem = menu.choose("냉면");
        assertThat(menuItem).isEqualTo(new MenuItem("냉면", 7000)); // 객체 비교를 위해서는 hashCode, equals 오버라이드가 필요하다.
    }

    @DisplayName("메뉴판에 없는 메뉴를 주문 테스트")
    @Test
    void missOrderTest() {
        Menu menu = new Menu(List.of(new MenuItem("냉면", 7000),
                new MenuItem("만두", 5000),
                new MenuItem("불고기", 13000)
        ));

        assertThatCode(()->menu.choose("초콜릿"))
                .isInstanceOf(IllegalArgumentException.class) // IlligalArgumentException 이 발생되는지를 확인
                .hasMessage("해당 하는 메뉴 아이탬 항목이 없습니다."); // 리턴 메세지 내용이 다음과 같은지를 확인
    }
}
