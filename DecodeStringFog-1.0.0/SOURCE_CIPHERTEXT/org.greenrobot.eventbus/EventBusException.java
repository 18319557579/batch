package org.greenrobot.eventbus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EventBusException.class */
public class EventBusException extends RuntimeException {
    private static final long serialVersionUID = -2912559384646531479L;

    public EventBusException(String str) {
        super(str);
    }

    public EventBusException(Throwable th) {
        super(th);
    }

    public EventBusException(String str, Throwable th) {
        super(str, th);
    }
}