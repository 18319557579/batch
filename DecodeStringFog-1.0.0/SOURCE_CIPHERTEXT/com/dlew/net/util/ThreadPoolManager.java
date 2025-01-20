package com.dlew.net.util;

import android.os.Build;
import android.os.Process;
import android.util.Log;
import com.dlew.StringFog;
import com.elvishew.xlog.BuildConfig;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ThreadPoolManager.class */
public class ThreadPoolManager {
    private static final int DEFAULT_COREPOOL_SIZE = 4;
    private static final int DEFAULT_MAXIMUMPOOL_SIZE = 4;
    private static final long DEFAULT_KEEPALIVE_TIME = 0;
    private static final TimeUnit DEFAULT_TIMEUNIT = TimeUnit.SECONDS;
    private static final HashMap<String, ThreadPoolManager> sThreadPoolManagerhHashMap = new HashMap<>();
    private static ScheduledExecutorService sScheduledExecutorService = null;
    private static evleeEelE sScheduledRunnable = null;
    private ThreadPoolExecutor mWorkThreadPool;
    private Queue<Runnable> mWaitTasksQueue;
    private RejectedExecutionHandler mRejectedExecutionHandler;
    private final Object mLock;
    private String mName;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ThreadPoolManager$EvLveE1.class */
    public class EvLveE1 extends ThreadPoolExecutor {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ ITaskExecuteListener f31EvLveE1;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EvLveE1(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue blockingQueue, RejectedExecutionHandler rejectedExecutionHandler, ITaskExecuteListener iTaskExecuteListener) {
            super(i, i2, j, timeUnit, (BlockingQueue<Runnable>) blockingQueue, rejectedExecutionHandler);
            this.f31EvLveE1 = iTaskExecuteListener;
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void beforeExecute(Thread thread, Runnable runnable) {
            this.f31EvLveE1.beforeExecute(thread, runnable);
        }

        @Override // java.util.concurrent.ThreadPoolExecutor
        public void afterExecute(Runnable runnable, Throwable th) {
            this.f31EvLveE1.afterExecute(runnable, th);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ThreadPoolManager$ITaskExecuteListener.class */
    public interface ITaskExecuteListener {
        void beforeExecute(Thread thread, Runnable runnable);

        void afterExecute(Runnable runnable, Throwable th);
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ThreadPoolManager$evleeEelE.class */
    public static class evleeEelE implements Runnable {
        public evleeEelE() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap] */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v5 */
        @Override // java.lang.Runnable
        public void run() {
            ?? r0 = ThreadPoolManager.sThreadPoolManagerhHashMap;
            synchronized (r0) {
                Process.setThreadPriority(10);
                Collection values = ThreadPoolManager.sThreadPoolManagerhHashMap.values();
                if (values != null && values.size() > 0) {
                    Iterator it = values.iterator();
                    while (it.hasNext()) {
                        ((ThreadPoolManager) it.next()).executeWaitTask();
                    }
                }
                r0 = r0;
            }
        }

        public /* synthetic */ evleeEelE(EvLveE1 evLveE1) {
            this();
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/ThreadPoolManager$v1vLvLE.class */
    public class v1vLvLE implements RejectedExecutionHandler {
        public v1vLvLE() {
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.lang.Throwable] */
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            synchronized (ThreadPoolManager.this.mLock) {
                ThreadPoolManager.this.mWaitTasksQueue.offer(runnable);
            }
        }
    }

    private ThreadPoolManager() {
        this(4, 4, DEFAULT_KEEPALIVE_TIME, DEFAULT_TIMEUNIT, false, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.HashMap, java.util.HashMap<java.lang.String, com.dlew.net.util.ThreadPoolManager>] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Throwable] */
    public static ThreadPoolManager getInstance(String str) {
        ThreadPoolManager threadPoolManager;
        ThreadPoolManager threadPoolManager2 = null;
        if (str != null && !BuildConfig.VERSION_NAME.equals(str.trim())) {
            ?? r0 = sThreadPoolManagerhHashMap;
            synchronized (r0) {
                ThreadPoolManager threadPoolManager3 = (ThreadPoolManager) r0.get(str);
                threadPoolManager2 = threadPoolManager3;
                if (threadPoolManager3 == null) {
                    threadPoolManager2 = threadPoolManager;
                    threadPoolManager = new ThreadPoolManager();
                    threadPoolManager.mName = str;
                    r0.put(str, threadPoolManager2);
                }
                r0 = r0;
            }
        }
        return threadPoolManager2;
    }

    public static ThreadPoolManager buildInstance(String str, int i, int i2, long j, TimeUnit timeUnit) {
        return buildInstance(str, i, i2, j, timeUnit, false);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.util.HashMap, java.util.HashMap<java.lang.String, com.dlew.net.util.ThreadPoolManager>] */
    public static void destroyAll() {
        ?? r0 = sThreadPoolManagerhHashMap;
        synchronized (r0) {
            Set keySet = r0.keySet();
            if (keySet != null && keySet.size() > 0) {
                Iterator it = keySet.iterator();
                while (it.hasNext()) {
                    ThreadPoolManager threadPoolManager = sThreadPoolManagerhHashMap.get((String) it.next());
                    if (threadPoolManager != null) {
                        threadPoolManager.cleanUp();
                    }
                }
            }
            sThreadPoolManagerhHashMap.clear();
        }
        ScheduledExecutorService scheduledExecutorService = sScheduledExecutorService;
        if (scheduledExecutorService != null) {
            if (!scheduledExecutorService.isShutdown()) {
                try {
                    sScheduledExecutorService.shutdownNow();
                } catch (Exception unused) {
                }
            }
            sScheduledExecutorService = null;
        }
        if (sScheduledRunnable != null) {
            sScheduledRunnable = null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.HashMap, java.util.HashMap<java.lang.String, com.dlew.net.util.ThreadPoolManager>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static void destroy(String str) {
        ?? r0 = sThreadPoolManagerhHashMap;
        synchronized (r0) {
            ThreadPoolManager threadPoolManager = (ThreadPoolManager) r0.get(str);
            if (threadPoolManager != null) {
                threadPoolManager.cleanUp();
            }
            r0 = r0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [com.dlew.net.util.ThreadPoolManager] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    public void executeWaitTask() {
        ?? r0 = this;
        Object obj = r0.mLock;
        synchronized (obj) {
            if (r0.hasMoreWaitTask()) {
                Runnable poll = this.mWaitTasksQueue.poll();
                if (poll != null) {
                    execute(poll);
                }
            }
            r0 = obj;
        }
    }

    private void initRejectedExecutionHandler() {
        this.mRejectedExecutionHandler = new v1vLvLE();
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object, java.lang.Throwable] */
    private void cleanUp() {
        ?? isShutdown = this.mWorkThreadPool.isShutdown();
        if (isShutdown == 0) {
            try {
                this.mWorkThreadPool.shutdownNow();
            } catch (Exception unused) {
                isShutdown.printStackTrace();
            }
        }
        this.mRejectedExecutionHandler = null;
        synchronized (this.mLock) {
            this.mWaitTasksQueue.clear();
        }
    }

    public boolean hasMoreWaitTask() {
        return !this.mWaitTasksQueue.isEmpty();
    }

    public void execute(Runnable runnable) {
        if (runnable != null) {
            this.mWorkThreadPool.execute(runnable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void cancel(Runnable runnable) {
        if (runnable != null) {
            synchronized (this.mLock) {
                if (this.mWaitTasksQueue.contains(runnable)) {
                    this.mWaitTasksQueue.remove(runnable);
                }
            }
            this.mWorkThreadPool.remove(runnable);
        }
    }

    public void removeAllTask() {
        try {
            if (this.mWorkThreadPool.isShutdown()) {
                return;
            }
            Iterator<Runnable> it = this.mWorkThreadPool.getQueue().iterator();
            while (it.hasNext()) {
                this.mWorkThreadPool.remove(it.next());
            }
        } catch (Throwable th) {
            Log.e(StringFog.decrypt("NBmikDZ+xUAPHZ2UOXvyShI=\n", "YHHQ9VcalS8=\n"), StringFog.decrypt("xVxBx6VwBZvbbU3buDU=\n", "tzksqNMVRPc=\n") + th.getMessage());
        }
    }

    public boolean isShutdown() {
        return this.mWorkThreadPool.isShutdown();
    }

    public void setThreadFactory(ThreadFactory threadFactory) {
        this.mWorkThreadPool.setThreadFactory(threadFactory);
    }

    public void allowCoreThreadTimeOut(boolean z) {
        if (Build.VERSION.SDK_INT > 8) {
            this.mWorkThreadPool.allowCoreThreadTimeOut(z);
        }
    }

    public String getManagerName() {
        return this.mName;
    }

    private ThreadPoolManager(int i, int i2, long j, TimeUnit timeUnit, boolean z, ITaskExecuteListener iTaskExecuteListener) {
        BlockingQueue blockingQueue;
        this.mWorkThreadPool = null;
        this.mWaitTasksQueue = null;
        this.mRejectedExecutionHandler = null;
        this.mLock = new Object();
        this.mWaitTasksQueue = new ConcurrentLinkedQueue();
        if (sScheduledRunnable == null) {
            sScheduledRunnable = new evleeEelE(null);
            ScheduledExecutorService newSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
            sScheduledExecutorService = newSingleThreadScheduledExecutor;
            newSingleThreadScheduledExecutor.scheduleAtFixedRate(sScheduledRunnable, DEFAULT_KEEPALIVE_TIME, 1500L, TimeUnit.MILLISECONDS);
        }
        initRejectedExecutionHandler();
        if (z) {
            blockingQueue = r0;
            BlockingQueue priorityBlockingQueue = new PriorityBlockingQueue(16);
        } else {
            blockingQueue = r0;
            BlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(16);
        }
        if (iTaskExecuteListener == null) {
            this.mWorkThreadPool = new ThreadPoolExecutor(i, i2, j, timeUnit, (BlockingQueue<Runnable>) blockingQueue, this.mRejectedExecutionHandler);
        } else {
            this.mWorkThreadPool = new EvLveE1(i, i2, j, timeUnit, blockingQueue, this.mRejectedExecutionHandler, iTaskExecuteListener);
        }
    }

    public static ThreadPoolManager buildInstance(String str, int i, int i2, long j, TimeUnit timeUnit, boolean z) {
        return buildInstance(str, i, i2, j, timeUnit, z, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.dlew.net.util.ThreadPoolManager, java.lang.Object, java.lang.Throwable] */
    public static ThreadPoolManager buildInstance(String str, int i, int i2, long j, TimeUnit timeUnit, boolean z, ITaskExecuteListener iTaskExecuteListener) {
        if (str == null || BuildConfig.VERSION_NAME.equals(str.trim()) || i < 0 || i2 <= 0 || i2 < i || j < DEFAULT_KEEPALIVE_TIME) {
            return null;
        }
        ?? threadPoolManager = new ThreadPoolManager(i, i2, j, timeUnit, z, iTaskExecuteListener);
        ((ThreadPoolManager) threadPoolManager).mName = str;
        HashMap<String, ThreadPoolManager> hashMap = sThreadPoolManagerhHashMap;
        synchronized (hashMap) {
            hashMap.put(str, threadPoolManager);
        }
        return threadPoolManager;
    }
}