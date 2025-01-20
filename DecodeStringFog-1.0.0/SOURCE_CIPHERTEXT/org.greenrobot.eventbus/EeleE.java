package org.greenrobot.eventbus;

/* compiled from: PendingPostQueue.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EeleE.class */
public final class EeleE {

    /* renamed from: EvLveE1, reason: collision with root package name */
    public evleeEelE f77EvLveE1;
    public evleeEelE v1vLvLE;

    public synchronized void EvLveE1(evleeEelE evleeeele) {
        if (evleeeele == null) {
            throw new NullPointerException("null cannot be enqueued");
        }
        evleeEelE evleeeele2 = this.v1vLvLE;
        if (evleeeele2 != null) {
            evleeeele2.evleeEelE = evleeeele;
            this.v1vLvLE = evleeeele;
        } else {
            if (this.f77EvLveE1 != null) {
                throw new IllegalStateException("Head present, but no tail");
            }
            this.v1vLvLE = evleeeele;
            this.f77EvLveE1 = evleeeele;
        }
        notifyAll();
    }

    public synchronized evleeEelE EvLveE1() {
        evleeEelE evleeeele = this.f77EvLveE1;
        if (evleeeele != null) {
            evleeEelE evleeeele2 = evleeeele.evleeEelE;
            this.f77EvLveE1 = evleeeele2;
            if (evleeeele2 == null) {
                this.v1vLvLE = null;
            }
        }
        return evleeeele;
    }

    public synchronized evleeEelE EvLveE1(int i) throws InterruptedException {
        if (this.f77EvLveE1 == null) {
            wait(i);
        }
        return EvLveE1();
    }
}