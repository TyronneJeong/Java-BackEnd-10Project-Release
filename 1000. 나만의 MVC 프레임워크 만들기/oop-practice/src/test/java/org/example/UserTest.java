package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("랜덤으로 패스워드를 초기화 한다.")
    @Test
    void passwordTest() {
        // given
        User user = new User();

        // when
        user.initPassword(new RandomPasswordGenerator()); // 리턴 되는 패스워드의 길이를 알 수 없다.

        // then
        assertThat(user.getPassword()).isNotNull(); // 정상 값이 리턴 되어 초기화 될 수도, 아닐 수도 있다.
    }

    @DisplayName("고정값 8로 패스워드를 초기화 한다.")
    @Test
    void passwordTest1() {
        // given
        User user = new User();

        // when
        user.initPassword(new CorrectPasswordGenerator()); // 항상 8자가 리턴 된다.
        // user.initPassword(() -> "abcdefgh"); // 항상 8자가 리턴 된다.

        // then
        assertThat(user.getPassword()).isNotNull(); // 정상 리턴 되어 초기값이 존재한다. 고로 NotNull 상태다.
    }

    @DisplayName("고정값 2로 비 초기화 한다.")
    @Test
    void passwordTest2() {
        // given
        User user = new User();

        // when
        user.initPassword(new WrongPasswordGenerator()); // 2글자가 리턴 된다.
        // user.initPassword(() -> "ab"); // 2글자가 리턴 된다.

        // then
        assertThat(user.getPassword()).isNull(); // 초기화 되지 않아 Null이 그대로 유지
    }
}