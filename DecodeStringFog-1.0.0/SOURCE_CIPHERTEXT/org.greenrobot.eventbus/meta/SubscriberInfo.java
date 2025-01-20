package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.SubscriberMethod;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/meta/SubscriberInfo.class */
public interface SubscriberInfo {
    Class<?> getSubscriberClass();

    SubscriberMethod[] getSubscriberMethods();

    SubscriberInfo getSuperSubscriberInfo();

    boolean shouldCheckSuperclass();
}