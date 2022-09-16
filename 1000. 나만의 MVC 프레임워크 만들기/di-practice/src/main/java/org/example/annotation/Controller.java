package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ANNOTATION_TYPE - Annotation type declaration
 * CONSTRUCTOR - Constructor declaration
 * FIELD - Field declaration (includes enum constants)
 * LOCAL_VARIABLE - Local variable declaration
 * METHOD - Method declaration
 * PACKAGE - Package declaration
 * PARAMETER - Parameter declaration
 * TYPE - Class, interface (including annotation type), or enum declaration
 */
@Target({ElementType.TYPE}) // 어노테이션을 선언 가능한 타입
@Retention(RetentionPolicy.RUNTIME) // 유지기간 : Class, Source, Runtime 등이 존재
public @interface Controller {

}
