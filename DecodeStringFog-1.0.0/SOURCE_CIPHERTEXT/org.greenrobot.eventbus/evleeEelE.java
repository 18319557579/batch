package org.greenrobot.eventbus;

import java.util.ArrayList;
import java.util.List;

/* compiled from: PendingPost.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/evleeEelE.class */
public final class evleeEelE {
    public static final List<evleeEelE> EeleE = new ArrayList();

    /* renamed from: EvLveE1, reason: collision with root package name */
    public Object f85EvLveE1;
    public EvLeE11LLE v1vLvLE;
    public evleeEelE evleeEelE;

    public evleeEelE(Object obj, EvLeE11LLE evLeE11LLE) {
        this.f85EvLveE1 = obj;
        this.v1vLvLE = evLeE11LLE;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Throwable, java.util.List, java.util.List<org.greenrobot.eventbus.evleeEelE>] */
    public static evleeEelE EvLveE1(EvLeE11LLE evLeE11LLE, Object obj) {
        ?? r0 = EeleE;
        synchronized (r0) {
            int size = r0.size();
            if (size <= 0) {
                return new evleeEelE(obj, evLeE11LLE);
            }
            evleeEelE evleeeele = (evleeEelE) r0.remove(size - 1);
            evleeeele.f85EvLveE1 = obj;
            evleeeele.v1vLvLE = evLeE11LLE;
            evleeeele.evleeEelE = null;
            return evleeeele;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.util.List, java.util.List<org.greenrobot.eventbus.evleeEelE>] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4 */
    public static void EvLveE1(evleeEelE evleeeele) {
        evleeeele.f85EvLveE1 = null;
        evleeeele.v1vLvLE = null;
        evleeeele.evleeEelE = null;
        ?? r0 = EeleE;
        synchronized (r0) {
            if (r0.size() < 10000) {
                r0.add(evleeeele);
            }
            r0 = r0;
        }
    }
}