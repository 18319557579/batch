package org.greenrobot.eventbus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/SubscriberExceptionEvent.class */
public final class SubscriberExceptionEvent {
    public final EventBus eventBus;
    public final Throwable throwable;
    public final Object causingEvent;
    public final Object causingSubscriber;

    public SubscriberExceptionEvent(EventBus eventBus, Throwable th, Object obj, Object obj2) {
        this.eventBus = eventBus;
        this.throwable = th;
        this.causingEvent = obj;
        this.causingSubscriber = obj2;
    }
}