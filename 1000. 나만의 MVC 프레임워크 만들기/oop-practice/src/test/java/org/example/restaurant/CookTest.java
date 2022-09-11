package org.example.restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CookTest {

    @DisplayName("요리정보 생성 테스트")
    @Test
    void createFoodInfo() {
        assertThatCode(()->new Cook("냉면", 7000)) // 7000원 가격의 냉면 정보 생성
                .doesNotThrowAnyException(); // 정상적인 생성시 오류가 발생하지 않는다.
    }
}
