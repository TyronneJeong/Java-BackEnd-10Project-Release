package org.example.restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class MenuItemTest {

    @DisplayName("메뉴아이탬 등록 검증")
    @Test
    void registerMenuItem() {
        assertThatCode(()->new MenuItem("냉면", 7000))
                .doesNotThrowAnyException();
    }
}
