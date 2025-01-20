package org.greenrobot.eventbus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/MainThreadSupport.class */
public interface MainThreadSupport {
    boolean isMainThread();

    Poster createPoster(EventBus eventBus);
}