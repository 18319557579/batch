package com.dlew.net.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.dlew.StringFog;
import java.util.concurrent.TimeUnit;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ThreadExecutorProxy.class */
public class ThreadExecutorProxy {
    private static final int DEFAULT_CORE_POOL_SIZE = 1;
    private static final int DEFAULT_MAX_POOL_SIZE = 6;
    private static final int KEEP_ALIVE_TIME = 60;
    private static v1vLvLE sExecutor;
    private static HandlerThread sSingleAsyncThread;
    private static Handler sSingleAsyncHandler;
    private static Handler sMainHandler;
    private static volatile ThreadExecutorProxy singleton;
    private static final String POOL_NAME = StringFog.decrypt("M1AFcklTaKcLRQBlRFNglCReB3s=\n", "VDFoFyEyBMs=\n");
    private static final String ASYNC_THREAD_NAME = StringFog.decrypt("LTWJHZ6ci+JnJ40WkZGCoysnnRaV0JPmODGFHA==\n", "SlTkePb9544=\n");
    private static int sCorePoolSize = 1;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ThreadExecutorProxy$v1vLvLE.class */
    public static class v1vLvLE extends AbstractThreadExecutor {
        @Override // com.dlew.net.util.AbstractThreadExecutor
        public ThreadPoolManager initThreadPoolManager() {
            ThreadPoolManager buildInstance = ThreadPoolManager.buildInstance(StringFog.decrypt("Q1ah8POvQ/x7Q6Tn/q9Lz1RYo/k=\n", "JDfMlZvOL5A=\n"), ThreadExecutorProxy.sCorePoolSize, 6, 60L, TimeUnit.SECONDS, false, getTaskExecuteListener());
            buildInstance.allowCoreThreadTimeOut(true);
            return buildInstance;
        }

        public v1vLvLE() {
        }
    }

    private ThreadExecutorProxy() {
        init();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.net.util.ThreadExecutorProxy>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static ThreadExecutorProxy getInstance() {
        if (singleton == null) {
            ?? r0 = ThreadExecutorProxy.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new ThreadExecutorProxy();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    private void init() {
        int EvLveE12 = com.dlew.net.util.EvLveE1.EvLveE1() - 1;
        sCorePoolSize = EvLveE12;
        if (EvLveE12 < 1) {
            sCorePoolSize = 1;
        }
        if (sCorePoolSize > 6) {
            sCorePoolSize = 6;
        }
        sExecutor = new v1vLvLE();
        HandlerThread handlerThread = new HandlerThread(ASYNC_THREAD_NAME);
        sSingleAsyncThread = handlerThread;
        handlerThread.start();
        sSingleAsyncHandler = new Handler(sSingleAsyncThread.getLooper());
        sMainHandler = new Handler(Looper.getMainLooper());
    }

    public void execute(Runnable runnable) {
        sExecutor.execute(runnable);
    }

    public void cancel(Runnable runnable) {
        sExecutor.cancel(runnable);
        sSingleAsyncHandler.removeCallbacks(runnable);
        sMainHandler.removeCallbacks(runnable);
    }

    public void destroy() {
        sExecutor.destroy();
        sSingleAsyncHandler.removeCallbacksAndMessages(null);
        sMainHandler.removeCallbacksAndMessages(null);
    }

    public void runOnAsyncThread(Runnable runnable) {
        sSingleAsyncHandler.post(runnable);
    }

    public void runOnMainThread(Runnable runnable) {
        if (sMainHandler == null) {
            sMainHandler = new Handler(Looper.getMainLooper());
        }
        sMainHandler.post(runnable);
    }

    public void removeMainThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        sMainHandler.removeCallbacks(runnable);
    }

    public void execute(boolean z, Runnable runnable) {
        if (z) {
            sExecutor.execute(runnable);
        } else if (runnable != null) {
            runnable.run();
        }
    }

    public void runOnAsyncThread(Runnable runnable, long j) {
        sSingleAsyncHandler.postDelayed(runnable, j);
    }

    public void execute(Runnable runnable, String str) {
        sExecutor.execute(runnable, str);
    }

    public void runOnMainThread(Runnable runnable, long j) {
        sMainHandler.postDelayed(runnable, j);
    }

    public void execute(Runnable runnable, int i) {
        sExecutor.execute(runnable, i);
    }

    public void execute(Runnable runnable, String str, int i) {
        sExecutor.execute(runnable, str, i);
    }
}