package org.example.di;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;

    @BeforeEach
    void setup() {
        reflections = new Reflections("org.example"); // 해당 패키지 이하를 대상으로 리플랙션 시작
        Set<Class<?>> preInstantiatedClazz = getTypesAnnotatedWith(Controller.class, Service.class);
    }

//    private Set<Class<?>> getTypesAnnotatedWith(Class<Controller> controllerClass, Class<Service> serviceClass) {
    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation>... annotations) { // Annotation 형태의 클래스가 여러개(...) 올수 있다.
        Set<Class<?>> beans = new HashSet<>();
        for (Class<? extends Annotation> annotation : annotations) { // 복수개의 상속 클래스를 하나씩 이터레이션 한다.
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return null;
    }
}