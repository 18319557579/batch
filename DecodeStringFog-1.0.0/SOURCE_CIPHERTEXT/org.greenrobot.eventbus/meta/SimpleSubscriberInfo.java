package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.SubscriberMethod;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/meta/SimpleSubscriberInfo.class */
public class SimpleSubscriberInfo extends AbstractSubscriberInfo {
    private final SubscriberMethodInfo[] methodInfos;

    public SimpleSubscriberInfo(Class cls, boolean z, SubscriberMethodInfo[] subscriberMethodInfoArr) {
        super(cls, null, z);
        this.methodInfos = subscriberMethodInfoArr;
    }

    @Override // org.greenrobot.eventbus.meta.SubscriberInfo
    public synchronized SubscriberMethod[] getSubscriberMethods() {
        int length = this.methodInfos.length;
        SubscriberMethod[] subscriberMethodArr = new SubscriberMethod[length];
        for (int i = 0; i < length; i++) {
            SubscriberMethodInfo subscriberMethodInfo = this.methodInfos[i];
            subscriberMethodArr[i] = createSubscriberMethod(subscriberMethodInfo.methodName, subscriberMethodInfo.eventType, subscriberMethodInfo.threadMode, subscriberMethodInfo.priority, subscriberMethodInfo.sticky);
        }
        return subscriberMethodArr;
    }
}