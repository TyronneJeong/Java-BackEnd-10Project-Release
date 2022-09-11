package org.example.restaurant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatCode;

/**
 * 음식점에서 음식을 주문하는 과정을 구현한다
 * 요구사항
 * 1. 도메인을 구성하는 객체에는 어떤 것들이 있는지 고민
 *    ㄴ 손님, 메뉴판, 돈까스/냉면/만두등의 음식, 요리사, 요리 등
 * 2. 객체들 간의 관계를 고민
 *    ㄴ 손님 -- 메뉴판 : 손님은 메뉴판을 통해 메뉴를 주문
 *    ㄴ 손님 -- 요리사 : 손님을 요리사에게 요리를 요청
 *    ㄴ 요리사 -- 요리 : 요리사는 주문서를 보고 음식을 조리
 * 3. 동적인 객체를 정적인 타입으로 추상화 하여 도메인 모델링 하기
 *    ㄴ 손님  -- 손님 추상화
 *    ㄴ 요리  -- 요리 추상화
 *    ㄴ 메뉴판 -- 메뉴판 추상화
 *    ㄴ 메뉴  -- 메뉴 추상화
 *    ㄴ 요리  -- 요리 추상화
 * 4. 협력을 설계
 * 5. 객체들을 포괄하는 타입에 적절한 책임을 할당
 * 6. 구현하기
 */
public class CustomerTest {

    @DisplayName("주문 테스트")
    @Test
    void orderingTest() {
        Customer customer = new Customer();
        Menu menu = new Menu(List.of(new MenuItem("냉면", 7000),
                new MenuItem("만두", 5000),
                new MenuItem("불고기", 13000)
        ));
        Cooking cooking = new Cooking();

        assertThatCode(()->customer.order("냉면", menu, cooking))
                .doesNotThrowAnyException(); // 정상 주문 가능 시나리오
    }
}
