package org.greenrobot.eventbus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/Subscribe.class */
public @interface Subscribe {
    ThreadMode threadMode() default ThreadMode.POSTING;

    boolean sticky() default false;

    int priority() default 0;
}