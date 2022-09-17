package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})  // Class, interface (including annotation type), or enum declaration
@Retention(RetentionPolicy.RUNTIME) // 런타임 기간 동안에만 해당 내용이 유지 된다.
public @interface Controller {

}
