package com.dlew.net.util;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/SafeRunnable.class */
public abstract class SafeRunnable implements Runnable {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.dlew.net.util.SafeRunnable] */
    /* JADX WARN: Type inference failed for: r0v3, types: [com.dlew.net.util.SafeRunnable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:9:0x000c -> B:3:0x0005). Please report as a decompilation issue!!! */
    @Override // java.lang.Runnable
    public final void run() {
        ?? r0;
        try {
            SafeRunnable safeRunnable = this;
            safeRunnable.doInBackground();
            r0 = safeRunnable;
        } finally {
            try {
                r0.handleFinally();
            } catch (Throwable th) {
            }
        }
        r0.handleFinally();
    }

    public abstract void doInBackground() throws Throwable;

    public abstract void handleError(Throwable th);

    public abstract void handleFinally();
}