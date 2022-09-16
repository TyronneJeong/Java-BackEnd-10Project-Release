package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD}) // 생성자, 필드, 메소드에 선언 가능
@Retention(RetentionPolicy.RUNTIME)
public @interface Inject {
}
