package org.example.di;

import com.sun.jdi.InvocationException;
import org.example.annotation.Inject;
import org.reflections.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private Map<Class<?>, Object> beans = new HashMap();

    private final Set<Class<?>> preInstantiatedClazz; // 생성자 생성시 저장되는 객체

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz; // 전달 받은 클래스 인스턴스들의 Set 객체
        this.initialize(); // 초기화
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz); // 전달 받은 클래스객체들의 인스턴스들을 선언 하여 beans Map에 저장시킨다.
            beans.put(clazz, instance); // <K, V> 는 Clazz Name, Instance
        }
    }

    // Instance 생성 메소드
    private Object createInstance(Class<?> clazz) {
        // 생성자 : 전달받은 클래스 객체의 생성자 정보를 읽어 리턴한다. ReflectionUtils 사용
        Constructor<?> constructor = findConstructor(clazz);

        // 생성자에 선언된 파라미터 정보를 담기 위한 리스트 객체 선언
        List<Object> parameters = new ArrayList<>();

        // 클래스 생성자에 선언된 파라미터 정보를 불러와 리스트에 담는다.
        for(Class<?> typeClass : constructor.getParameterTypes()) {
            parameters.add(getParameteresByClass(typeClass));
        }

        // 생성자에 파라미터 정보로 불러온 List 객체를 포함하여 인스턴스 선언을 하여 리턴 한다.
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationError | IllegalAccessError | IllegalAccessException |
                 InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    // 클래스의 @Inject 선언된 생성자를 호출 한다.
    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if(Objects.nonNull(constructor)){ // Object 의 값이 null이 아닌 경우
            return constructor; // @Inject로 선언된 컨스트럭터를 리턴한다.
        }
        return clazz.getConstructors()[0]; // 해당 클래스의 첫번째 컨스트럭터를 리턴한다.
    }

    // 파라미터로 선언된 객체들의 인스턴스 선언을 호출 한다.
    private Object getParameteresByClass(Class<?> typeClass) {
        // 전달된 파라미터의 클래스 정보를 빈객체에서 호출 한다.
        Object instanceBean = getBean(typeClass);
        if(Objects.nonNull(instanceBean)) { // 존재한다면 해당 객체를 리턴.
            return instanceBean;
        }
        return createInstance(typeClass); // 아니라면 새로운 인스턴스 선언을 호출 한다.
    }

    // 입력된 클래스 타입에 해당 하는 객체를 beans에서 꺼내어 리턴 한다.
    public <T> T getBean(Class<?> requiredType) {
        return (T) beans.get(requiredType);
    }
}
