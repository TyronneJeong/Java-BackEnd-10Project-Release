package org.example.di;

import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.util.Set;

public class BeanFactoryUtils {
    // ReflectionUtils를 이용하여 전달받은 클래스 객체의 모든 생성자를 가져온다.
    // 단, 클래스내 @Inject 어노테이션이 붙어 있는 생성자에 한하여 동작을 수행 한다.
    public static Constructor<?> getInjectedConstructor(Class<?> clazz) {
        Set<Constructor> injectedConstructors = ReflectionUtils.getAllConstructors(clazz, ReflectionUtils.withAnnotation(Inject.class));
        if(injectedConstructors.isEmpty()){
            return null;
        }
        return injectedConstructors.iterator().next();
    }
}
