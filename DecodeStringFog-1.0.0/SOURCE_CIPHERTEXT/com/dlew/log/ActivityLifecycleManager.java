package com.dlew.log;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import com.dlew.events.DLEWglEvent;
import com.dlew.hardware.PhoneInfoWrapper;
import org.greenrobot.eventbus.EventBus;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/ActivityLifecycleManager.class */
public class ActivityLifecycleManager extends EvLveE1 {
    private static volatile ActivityLifecycleManager singleton;
    private long entryTime;
    private boolean isAppOpen;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.log.ActivityLifecycleManager>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static ActivityLifecycleManager getInstance() {
        if (singleton == null) {
            ?? r0 = ActivityLifecycleManager.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new ActivityLifecycleManager();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    @Override // com.dlew.log.EvLveE1, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        super.onActivityCreated(activity, bundle);
        if (this.isAppOpen) {
            return;
        }
        this.isAppOpen = true;
        if (TextUtils.isEmpty(PhoneInfoWrapper.getGAID())) {
            return;
        }
        EventTrackManager.getInstance().SendAppOpen();
    }

    @Override // com.dlew.log.EvLveE1, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        if (isBackground()) {
            this.entryTime = System.currentTimeMillis();
            if (!TextUtils.isEmpty(PhoneInfoWrapper.getGAID())) {
                EventTrackManager.getInstance().SendAppFront();
            }
            EventBus.getDefault().post(new DLEWglEvent(DLEWglEvent.TYPE.APP_FRONT));
        }
        super.onActivityStarted(activity);
    }

    @Override // com.dlew.log.EvLveE1, android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        super.onActivityStopped(activity);
        long currentTimeMillis = System.currentTimeMillis();
        if (isBackground()) {
            long j = this.entryTime;
            if (j <= 0 || currentTimeMillis <= j) {
                return;
            }
            EventTrackManager.getInstance().SendAppExit(String.valueOf(this.entryTime));
        }
    }

    @Override // com.dlew.log.EvLveE1
    public /* bridge */ /* synthetic */ boolean isBackground() {
        return super.isBackground();
    }

    @Override // com.dlew.log.EvLveE1
    public /* bridge */ /* synthetic */ void onApplicationTerminate(Application application) {
        super.onApplicationTerminate(application);
    }

    @Override // com.dlew.log.EvLveE1
    public /* bridge */ /* synthetic */ void onApplicationCreate(Application application) {
        super.onApplicationCreate(application);
    }

    @Override // com.dlew.log.EvLveE1, android.app.Application.ActivityLifecycleCallbacks
    public /* bridge */ /* synthetic */ void onActivityDestroyed(Activity activity) {
        super.onActivityDestroyed(activity);
    }

    @Override // com.dlew.log.EvLveE1, android.app.Application.ActivityLifecycleCallbacks
    public /* bridge */ /* synthetic */ void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        super.onActivitySaveInstanceState(activity, bundle);
    }

    @Override // com.dlew.log.EvLveE1, android.app.Application.ActivityLifecycleCallbacks
    public /* bridge */ /* synthetic */ void onActivityPaused(Activity activity) {
        super.onActivityPaused(activity);
    }

    @Override // com.dlew.log.EvLveE1, android.app.Application.ActivityLifecycleCallbacks
    public /* bridge */ /* synthetic */ void onActivityResumed(Activity activity) {
        super.onActivityResumed(activity);
    }
}