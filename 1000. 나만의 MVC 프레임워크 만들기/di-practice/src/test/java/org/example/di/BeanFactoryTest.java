package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    /**
     * @Before  @BeforeEach
     * 클래스내 @Test 메소드가 실행하기 전에 매번 실행 되는 로직
     *
     * @BeforeClass @BeforeAll
     * 모든 테스트 유닛의 실행 전 단 한번만 실행 되는 로직
     * 메소드는 Static으로 선언 되어져야 한다.
     *
     * 실행 우선 순위는 All -> each 순
     * ex) Before All -> BeforeEach -> AfterEach -> AfterAll
     */
    @BeforeEach // 테스트 유닛 실행 전 동작 - private 멤버들의 초기화를 수행
    void setup() {
        // 해당 패키지 이하의 인스턴스화 된 클래스를 리플랙션 한다.
        reflections = new Reflections("org.example");

        // 스캐닝 대상 중 Controller.class와 Service.class @interface 타입을 인자로 받아 인스턴스를 생성한 Set 컬렉션을 구성 한다.
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);

        // 어노테이션 선언된 클래스 객체들을 생성자로 전달하여 beanFactory를 구성 한다.
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    // 전달 받은 어노테이션 타입의 클래스 정보를 Set 객체에 담아 리턴한다.
    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) {
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) { // 복수개의 상속 클래스를 하나씩 이터레이션 한다.
            System.out.println(annotation.getName());
            // interface org.example.annotation.Controller
            // interface org.example.annotation.Service
            beans.addAll(reflections.getTypesAnnotatedWith(annotation)); // @interface 객체가 담긴다.
        }
        return beans;
    }
    @Nested // 테스트 유닛을 목적 별로 그룹핑 하기 위한 어노테이션
    class diInjectionTest {
        @DisplayName("초기화가 끝난 상황에서 Annotation 을 상속 받은 특정 Bean을 불러오는 테스트를 한다.")
        @Test
        void diTest() {
            UserController userController = beanFactory.getBean(UserController.class);
            // 인자로 넘긴 UserController가 존재하는지를 확인.
            assertThat(userController).isNotNull();

            // UserController의 파라미터로 존재하는 UserService.class가 존재하는지를 확인
            assertThat(userController.getUserService()).isNotNull();
        }
    }
}