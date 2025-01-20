package org.greenrobot.eventbus;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/HandlerPoster.class */
public class HandlerPoster extends Handler implements Poster {
    private final EeleE queue;
    private final int maxMillisInsideHandleMessage;
    private final EventBus eventBus;
    private boolean handlerActive;

    public HandlerPoster(EventBus eventBus, Looper looper, int i) {
        super(looper);
        this.eventBus = eventBus;
        this.maxMillisInsideHandleMessage = i;
        this.queue = new EeleE();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.greenrobot.eventbus.Poster
    public void enqueue(EvLeE11LLE evLeE11LLE, Object obj) {
        evleeEelE EvLveE12 = evleeEelE.EvLveE1(evLeE11LLE, obj);
        synchronized (this) {
            this.queue.EvLveE1(EvLveE12);
            if (!this.handlerActive) {
                this.handlerActive = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v14, types: [org.greenrobot.eventbus.HandlerPoster] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v18, types: [org.greenrobot.eventbus.HandlerPoster] */
    @Override // android.os.Handler
    public void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                evleeEelE EvLveE12 = this.queue.EvLveE1();
                evleeEelE evleeeele = EvLveE12;
                if (EvLveE12 == null) {
                    ?? r0 = this;
                    synchronized (r0) {
                        evleeEelE EvLveE13 = r0.queue.EvLveE1();
                        evleeeele = EvLveE13;
                        if (EvLveE13 == null) {
                            r0 = this;
                            r0.handlerActive = false;
                            return;
                        }
                    }
                }
                this.eventBus.invokeSubscriber(evleeeele);
            } while (SystemClock.uptimeMillis() - uptimeMillis < this.maxMillisInsideHandleMessage);
            if (!sendMessage(obtainMessage())) {
                throw new EventBusException("Could not send handler message");
            }
            this.handlerActive = true;
        } catch (Throwable th) {
            th.handlerActive = false;
            throw null;
        }
    }
}