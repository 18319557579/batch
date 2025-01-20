package com.dlew.net.util;

import com.dlew.net.util.ThreadPoolManager;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/AbstractThreadExecutor.class */
public abstract class AbstractThreadExecutor {
    private ThreadPoolManager mManager;
    private byte[] mLock = new byte[0];

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/AbstractThreadExecutor$EvLveE1.class */
    public class EvLveE1 implements ThreadPoolManager.ITaskExecuteListener {
        public EvLveE1() {
        }

        @Override // com.dlew.net.util.ThreadPoolManager.ITaskExecuteListener
        public void beforeExecute(Thread thread, Runnable runnable) {
            if (runnable instanceof GoTask) {
                GoTask goTask = (GoTask) runnable;
                String str = goTask.mThreadName;
                if (str != null) {
                    thread.setName(str);
                }
                thread.setPriority(goTask.mPriority);
            }
        }

        @Override // com.dlew.net.util.ThreadPoolManager.ITaskExecuteListener
        public void afterExecute(Runnable runnable, Throwable th) {
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/AbstractThreadExecutor$GoTask.class */
    public static class GoTask implements Runnable {
        public Runnable mTask;
        public int mPriority = 5;
        public String mThreadName;

        public GoTask(Runnable runnable) {
            this.mTask = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.mTask.run();
        }
    }

    public abstract ThreadPoolManager initThreadPoolManager();

    public ThreadPoolManager.ITaskExecuteListener getTaskExecuteListener() {
        return new EvLveE1();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.dlew.net.util.AbstractThreadExecutor] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v7 */
    public void execute(Runnable runnable) {
        if (this.mManager == null) {
            ?? r0 = this;
            byte[] bArr = r0.mLock;
            synchronized (bArr) {
                if (r0.mManager == null) {
                    this.mManager = initThreadPoolManager();
                }
                r0 = bArr;
            }
        }
        this.mManager.execute(runnable);
    }

    public void cancel(Runnable runnable) {
        ThreadPoolManager threadPoolManager = this.mManager;
        if (threadPoolManager != null) {
            threadPoolManager.cancel(runnable);
        }
    }

    public void destroy() {
        ThreadPoolManager threadPoolManager = this.mManager;
        if (threadPoolManager != null) {
            ThreadPoolManager.destroy(threadPoolManager.getManagerName());
            this.mManager = null;
        }
    }

    public void execute(Runnable runnable, String str) {
        execute(runnable, str, Thread.currentThread().getPriority());
    }

    public void execute(Runnable runnable, int i) {
        execute(runnable, null, i);
    }

    public void execute(Runnable runnable, String str, int i) {
        GoTask goTask = new GoTask(runnable);
        goTask.mThreadName = str;
        goTask.mPriority = i;
        execute(goTask);
    }
}