package com.dlew.events;

import com.applovin.mediation.MaxAd;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/events/DLEWglEvent.class */
public class DLEWglEvent {
    public TYPE type;
    private MaxAd maxAd;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/events/DLEWglEvent$TYPE.class */
    public enum TYPE {
        AD_ON_SHOW,
        AD_ON_HIDE,
        AD_ON_INITIALIZED,
        AD_ON_OPEN_READY,
        AD_ON_OPEN_FAILED,
        ON_SDK_INITIALIZED,
        ON_BALANCE_MODIFY,
        APP_FRONT,
        AD_REVENUE
    }

    public DLEWglEvent() {
    }

    public static void postAdRevenue(MaxAd maxAd) {
        EventBus.getDefault().post(new DLEWglEvent(maxAd));
    }

    public MaxAd getMaxAd() {
        return this.maxAd;
    }

    public DLEWglEvent(TYPE type) {
        this.type = type;
    }

    public DLEWglEvent(MaxAd maxAd) {
        this.type = TYPE.AD_REVENUE;
        this.maxAd = maxAd;
    }
}