package com.dlew.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/TimeConstants.class */
public final class TimeConstants {
    public static final int MSEC = 1;
    public static final int SEC = 1000;
    public static final int MIN = 60000;
    public static final int HOUR = 3600000;
    public static final int DAY = 86400000;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/common/TimeConstants$Unit.class */
    public @interface Unit {
    }
}