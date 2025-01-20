package org.greenrobot.eventbus;

/* compiled from: Subscription.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EvLeE11LLE.class */
public final class EvLeE11LLE {

    /* renamed from: EvLveE1, reason: collision with root package name */
    public final Object f78EvLveE1;
    public final SubscriberMethod v1vLvLE;
    public volatile boolean evleeEelE = true;

    public EvLeE11LLE(Object obj, SubscriberMethod subscriberMethod) {
        this.f78EvLveE1 = obj;
        this.v1vLvLE = subscriberMethod;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EvLeE11LLE)) {
            return false;
        }
        EvLeE11LLE evLeE11LLE = (EvLeE11LLE) obj;
        return this.f78EvLveE1 == evLeE11LLE.f78EvLveE1 && this.v1vLvLE.equals(evLeE11LLE.v1vLvLE);
    }

    public int hashCode() {
        return this.f78EvLveE1.hashCode() + this.v1vLvLE.methodString.hashCode();
    }
}