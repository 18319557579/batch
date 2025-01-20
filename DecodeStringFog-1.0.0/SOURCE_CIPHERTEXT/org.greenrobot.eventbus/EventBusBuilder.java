package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.greenrobot.eventbus.Logger;
import org.greenrobot.eventbus.android.AndroidComponents;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EventBusBuilder.class */
public class EventBusBuilder {
    private static final ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    boolean throwSubscriberException;
    boolean ignoreGeneratedIndex;
    boolean strictMethodVerification;
    List<Class<?>> skipMethodVerificationForClasses;
    List<SubscriberInfoIndex> subscriberInfoIndexes;
    Logger logger;
    MainThreadSupport mainThreadSupport;
    boolean logSubscriberExceptions = true;
    boolean logNoSubscriberMessages = true;
    boolean sendSubscriberExceptionEvent = true;
    boolean sendNoSubscriberEvent = true;
    boolean eventInheritance = true;
    ExecutorService executorService = DEFAULT_EXECUTOR_SERVICE;

    public EventBusBuilder logSubscriberExceptions(boolean z) {
        this.logSubscriberExceptions = z;
        return this;
    }

    public EventBusBuilder logNoSubscriberMessages(boolean z) {
        this.logNoSubscriberMessages = z;
        return this;
    }

    public EventBusBuilder sendSubscriberExceptionEvent(boolean z) {
        this.sendSubscriberExceptionEvent = z;
        return this;
    }

    public EventBusBuilder sendNoSubscriberEvent(boolean z) {
        this.sendNoSubscriberEvent = z;
        return this;
    }

    public EventBusBuilder throwSubscriberException(boolean z) {
        this.throwSubscriberException = z;
        return this;
    }

    public EventBusBuilder eventInheritance(boolean z) {
        this.eventInheritance = z;
        return this;
    }

    public EventBusBuilder executorService(ExecutorService executorService) {
        this.executorService = executorService;
        return this;
    }

    public EventBusBuilder skipMethodVerificationFor(Class<?> cls) {
        if (this.skipMethodVerificationForClasses == null) {
            this.skipMethodVerificationForClasses = new ArrayList();
        }
        this.skipMethodVerificationForClasses.add(cls);
        return this;
    }

    public EventBusBuilder ignoreGeneratedIndex(boolean z) {
        this.ignoreGeneratedIndex = z;
        return this;
    }

    public EventBusBuilder strictMethodVerification(boolean z) {
        this.strictMethodVerification = z;
        return this;
    }

    public EventBusBuilder addIndex(SubscriberInfoIndex subscriberInfoIndex) {
        if (this.subscriberInfoIndexes == null) {
            this.subscriberInfoIndexes = new ArrayList();
        }
        this.subscriberInfoIndexes.add(subscriberInfoIndex);
        return this;
    }

    public EventBusBuilder logger(Logger logger) {
        this.logger = logger;
        return this;
    }

    public Logger getLogger() {
        Logger logger = this.logger;
        return logger != null ? logger : Logger.Default.get();
    }

    public MainThreadSupport getMainThreadSupport() {
        MainThreadSupport mainThreadSupport = this.mainThreadSupport;
        if (mainThreadSupport != null) {
            return mainThreadSupport;
        }
        if (AndroidComponents.areAvailable()) {
            return AndroidComponents.get().defaultMainThreadSupport;
        }
        return null;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Class<org.greenrobot.eventbus.EventBus>, java.lang.Throwable] */
    public EventBus installDefaultEventBus() {
        EventBus eventBus;
        synchronized (EventBus.class) {
            if (EventBus.defaultInstance != null) {
                throw new EventBusException("Default instance already exists. It may be only set once before it's used the first time to ensure consistent behavior.");
            }
            EventBus.defaultInstance = build();
            eventBus = EventBus.defaultInstance;
        }
        return eventBus;
    }

    public EventBus build() {
        return new EventBus(this);
    }
}