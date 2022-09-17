package org.example;


import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void componentScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        // 담겨진 클래스 객체들을 적어 본다.
        logger.info("beans : [{}] "+ beans);
    }

    private static Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        // 컴포넌트 스캔 루트 패스
        Reflections reflections = new Reflections("org.example");

        // RETURN 객체
        Set<Class<?>> beans = new HashSet<>();

        // 어노테이션 기반으로 호출된 클래스 정보를 Set에 추가
        annotations.forEach(e -> {
            beans.addAll(reflections.getTypesAnnotatedWith(e));
        });
        return beans;
    }

    @Test
    void showClass() {
        Class<User> clazz = User.class;
        logger.info(clazz.getName()); // 클래스의 이름을 출력

        // 클래스에 선언된 멤버변수들을 출력
        logger.info("User all declared fields : [{}]"
                , Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));

        // 클래스에 선언된 생성자들을 출력
        logger.info("User all declared constructors : [{}]"
                , Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));

        // 클래스에 선언된 method들을 출력
        logger.info("User all declared methods : [{}]"
                , Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @DisplayName("Heap 영역에 로드되어 있는 클래스 정보 가져 오기")
    @Test
    void load() throws ClassNotFoundException {
        // 1. 선언형으로 가져온다.
        Class<User> clazz1 = User.class; // 클래스 정보가 import로 이미 선언되어 있음.

        // 2. 인스턴스 생성후 가져온다.
        User user = new User("Sample", "샘플"); // 인스턴스를 생성까지 했으므로 클래스 정보는 로드되어 있음.
        Class<? extends User> clazz2 = user.getClass();

        // 3. 런타임 클래스 로더
        Class<?> clazz3 = Class.forName("org.example.model.User"); // 입력된 경로를 통해 로드하므로 찾지 못할 수 있다.

        logger.info("clazz1 : [{}]", clazz1);
        logger.info("clazz2 : [{}]", clazz2);
        logger.info("clazz3 : [{}]", clazz3);

        logger.info("clazz1 == clazz2 : [{}]", clazz1.equals(clazz2));
        logger.info("clazz1 == clazz3 : [{}]", clazz1.equals(clazz3));
        logger.info("clazz2 == clazz3 : [{}]", clazz2.equals(clazz3));

        // 3가지 호출 법 모두 동일 객체를 가리키는지 확인
        assertThat(clazz1 == clazz2).isTrue();
        assertThat(clazz1 == clazz3).isTrue();
        assertThat(clazz2 == clazz3).isTrue();
    }
}
