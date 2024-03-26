package org.example.api.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)  //어디에 사용
@Retention(RetentionPolicy.RUNTIME) //실행 중에만
public @interface UserSession {
}
