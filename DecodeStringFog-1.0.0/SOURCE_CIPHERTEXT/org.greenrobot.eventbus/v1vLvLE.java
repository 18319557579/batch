package org.greenrobot.eventbus;

import com.dlew.common.TimeConstants;
import java.util.logging.Level;

/* compiled from: BackgroundPoster.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/v1vLvLE.class */
public final class v1vLvLE implements Runnable, Poster {

    /* renamed from: EvLveE1, reason: collision with root package name */
    public final EeleE f86EvLveE1 = new EeleE();
    public final EventBus v1vLvLE;
    public volatile boolean evleeEelE;

    public v1vLvLE(EventBus eventBus) {
        this.v1vLvLE = eventBus;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.greenrobot.eventbus.v1vLvLE] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v3 */
    @Override // org.greenrobot.eventbus.Poster
    public void enqueue(EvLeE11LLE evLeE11LLE, Object obj) {
        ?? r0 = this;
        evleeEelE EvLveE12 = evleeEelE.EvLveE1(evLeE11LLE, obj);
        synchronized (r0) {
            r0.f86EvLveE1.EvLveE1(EvLveE12);
            if (!r0.evleeEelE) {
                this.evleeEelE = true;
                this.v1vLvLE.getExecutorService().execute(this);
            }
            r0 = this;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v12, types: [org.greenrobot.eventbus.v1vLvLE] */
    /* JADX WARN: Type inference failed for: r0v2, types: [org.greenrobot.eventbus.v1vLvLE] */
    /* JADX WARN: Type inference failed for: r0v7, types: [org.greenrobot.eventbus.EventBus] */
    /* JADX WARN: Type inference failed for: r0v8, types: [org.greenrobot.eventbus.v1vLvLE] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    @Override // java.lang.Runnable
    public void run() {
        ?? r0;
        while (true) {
            try {
                try {
                    evleeEelE EvLveE12 = this.f86EvLveE1.EvLveE1(TimeConstants.SEC);
                    evleeEelE evleeeele = EvLveE12;
                    if (EvLveE12 == null) {
                        r0 = this;
                        synchronized (r0) {
                            evleeEelE EvLveE13 = r0.f86EvLveE1.EvLveE1();
                            evleeeele = EvLveE13;
                            if (EvLveE13 == null) {
                                r0 = this;
                                r0.evleeEelE = false;
                                r0.evleeEelE = false;
                                return;
                            }
                        }
                    }
                    r0 = this.v1vLvLE;
                    r0.invokeSubscriber(evleeeele);
                } catch (InterruptedException e) {
                    r0 = this;
                    r0.v1vLvLE.getLogger().log(Level.WARNING, Thread.currentThread().getName() + " was interruppted", e);
                    r0.evleeEelE = false;
                    return;
                }
            } catch (Throwable unused) {
                this.evleeEelE = false;
                throw r0;
            }
        }
    }
}