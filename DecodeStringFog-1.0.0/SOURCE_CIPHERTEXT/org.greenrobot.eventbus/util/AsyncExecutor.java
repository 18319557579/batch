package org.greenrobot.eventbus.util;

import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/util/AsyncExecutor.class */
public class AsyncExecutor {
    private final Executor threadPool;
    private final Constructor<?> failureEventConstructor;
    private final EventBus eventBus;
    private final Object scope;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/util/AsyncExecutor$Builder.class */
    public static class Builder {
        private Executor threadPool;
        private Class<?> failureEventType;
        private EventBus eventBus;

        public Builder threadPool(Executor executor) {
            this.threadPool = executor;
            return this;
        }

        public Builder failureEventType(Class<?> cls) {
            this.failureEventType = cls;
            return this;
        }

        public Builder eventBus(EventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }

        public AsyncExecutor build() {
            return buildForScope(null);
        }

        public AsyncExecutor buildForScope(Object obj) {
            if (this.eventBus == null) {
                this.eventBus = EventBus.getDefault();
            }
            if (this.threadPool == null) {
                this.threadPool = Executors.newCachedThreadPool();
            }
            if (this.failureEventType == null) {
                this.failureEventType = ThrowableFailureEvent.class;
            }
            return new AsyncExecutor(this.threadPool, this.eventBus, this.failureEventType, obj);
        }

        private Builder() {
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/util/AsyncExecutor$RunnableEx.class */
    public interface RunnableEx {
        void run() throws Exception;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static AsyncExecutor create() {
        return new Builder().build();
    }

    public void execute(RunnableEx runnableEx) {
        this.threadPool.execute(() -> {
            try {
                runnableEx.run();
            } catch (Exception e) {
                try {
                    Object newInstance = this.failureEventConstructor.newInstance(e);
                    if (newInstance instanceof HasExecutionScope) {
                        ((HasExecutionScope) newInstance).setExecutionScope(this.scope);
                    }
                    this.eventBus.post(newInstance);
                } catch (Exception e2) {
                    this.eventBus.getLogger().log(Level.SEVERE, "Original exception:", e);
                    throw new RuntimeException("Could not create failure event", e2);
                }
            }
        });
    }

    private AsyncExecutor(Executor executor, EventBus eventBus, Class<?> cls, Object obj) {
        this.threadPool = executor;
        this.eventBus = eventBus;
        this.scope = obj;
        try {
            Class<?>[] clsArr = new Class[1];
            clsArr[0] = Throwable.class;
            this.failureEventConstructor = cls.getConstructor(clsArr);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", e);
        }
    }
}