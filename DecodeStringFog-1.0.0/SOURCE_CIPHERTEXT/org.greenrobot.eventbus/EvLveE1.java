package org.greenrobot.eventbus;

/* compiled from: AsyncPoster.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EvLveE1.class */
public class EvLveE1 implements Runnable, Poster {

    /* renamed from: EvLveE1, reason: collision with root package name */
    public final EeleE f79EvLveE1 = new EeleE();
    public final EventBus v1vLvLE;

    public EvLveE1(EventBus eventBus) {
        this.v1vLvLE = eventBus;
    }

    @Override // org.greenrobot.eventbus.Poster
    public void enqueue(EvLeE11LLE evLeE11LLE, Object obj) {
        this.f79EvLveE1.EvLveE1(evleeEelE.EvLveE1(evLeE11LLE, obj));
        this.v1vLvLE.getExecutorService().execute(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        evleeEelE EvLveE12 = this.f79EvLveE1.EvLveE1();
        if (EvLveE12 == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.v1vLvLE.invokeSubscriber(EvLveE12);
    }
}