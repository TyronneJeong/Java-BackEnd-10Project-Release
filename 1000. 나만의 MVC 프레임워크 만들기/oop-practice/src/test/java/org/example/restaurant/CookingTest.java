package org.example.restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class CookingTest {

    @DisplayName("메뉴 주문 후 조리 검증")
    @Test
    void makeCookTest() {
        Cooking cooking = new Cooking(); // 요리사의 요리 행위
        MenuItem menuItem = new MenuItem("냉면", 7000); // 전달할 메뉴 아이탬

        Cook cook = cooking.makeCook(menuItem); // 메뉴아이탬을 전달하여 완성된 요리를 리턴

        assertThat(cook).isEqualTo(new Cook("냉면", 7000)); // 완성된 요리의 정보가 해당 객체와 같은지를 비교
        // 객체간 비교를 위해 hashCode와 equals 메소드를 오버라이딩 해준다.
    }
}
