package org.greenrobot.eventbus;

import java.lang.reflect.Method;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/SubscriberMethod.class */
public class SubscriberMethod {
    final Method method;
    final ThreadMode threadMode;
    final Class<?> eventType;
    final int priority;
    final boolean sticky;
    String methodString;

    public SubscriberMethod(Method method, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = cls;
        this.priority = i;
        this.sticky = z;
    }

    private synchronized void checkMethodString() {
        if (this.methodString == null) {
            StringBuilder sb = new StringBuilder(64);
            sb.append(this.method.getDeclaringClass().getName());
            sb.append('#').append(this.method.getName());
            sb.append('(').append(this.eventType.getName());
            this.methodString = sb.toString();
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SubscriberMethod)) {
            return false;
        }
        checkMethodString();
        SubscriberMethod subscriberMethod = (SubscriberMethod) obj;
        subscriberMethod.checkMethodString();
        return this.methodString.equals(subscriberMethod.methodString);
    }

    public int hashCode() {
        return this.method.hashCode();
    }
}