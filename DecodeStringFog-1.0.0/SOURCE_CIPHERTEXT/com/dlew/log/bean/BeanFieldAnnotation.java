package com.dlew.log.bean;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/bean/BeanFieldAnnotation.class */
public @interface BeanFieldAnnotation {
    int order();
}