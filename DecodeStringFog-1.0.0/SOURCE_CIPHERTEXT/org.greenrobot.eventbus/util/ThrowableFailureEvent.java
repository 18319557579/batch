package org.greenrobot.eventbus.util;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/util/ThrowableFailureEvent.class */
public class ThrowableFailureEvent implements HasExecutionScope {
    protected final Throwable throwable;
    protected final boolean suppressErrorUi;
    private Object executionContext;

    public ThrowableFailureEvent(Throwable th) {
        this.throwable = th;
        this.suppressErrorUi = false;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }

    public boolean isSuppressErrorUi() {
        return this.suppressErrorUi;
    }

    @Override // org.greenrobot.eventbus.util.HasExecutionScope
    public Object getExecutionScope() {
        return this.executionContext;
    }

    @Override // org.greenrobot.eventbus.util.HasExecutionScope
    public void setExecutionScope(Object obj) {
        this.executionContext = obj;
    }

    public ThrowableFailureEvent(Throwable th, boolean z) {
        this.throwable = th;
        this.suppressErrorUi = z;
    }
}