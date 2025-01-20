package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.ThreadMode;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/meta/SubscriberMethodInfo.class */
public class SubscriberMethodInfo {
    final String methodName;
    final ThreadMode threadMode;
    final Class<?> eventType;
    final int priority;
    final boolean sticky;

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.methodName = str;
        this.threadMode = threadMode;
        this.eventType = cls;
        this.priority = i;
        this.sticky = z;
    }

    public SubscriberMethodInfo(String str, Class<?> cls) {
        this(str, cls, ThreadMode.POSTING, 0, false);
    }

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode) {
        this(str, cls, threadMode, 0, false);
    }
}