package com.dlew.log;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.elvishew.xlog.BuildConfig;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ActivityLifecycleCallbacks.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/EvLveE1.class */
public abstract class EvLveE1 implements Application.ActivityLifecycleCallbacks {
    private ArrayList<String> sActivityTask = new ArrayList<>();

    private String getTopActivityClassName() {
        String str = BuildConfig.VERSION_NAME;
        if (this.sActivityTask.size() > 0) {
            ArrayList<String> arrayList = this.sActivityTask;
            str = arrayList.get(arrayList.size() - 1);
        }
        return str;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        String topActivityClassName = getTopActivityClassName();
        String className = activity.getComponentName().getClassName();
        if (className.equals(topActivityClassName)) {
            return;
        }
        this.sActivityTask.add(className);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Iterator<String> it = this.sActivityTask.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (next.equals(activity.getComponentName().getClassName())) {
                this.sActivityTask.remove(next);
                return;
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
    }

    public void onApplicationCreate(Application application) {
        application.registerActivityLifecycleCallbacks(this);
    }

    public void onApplicationTerminate(Application application) {
        application.unregisterActivityLifecycleCallbacks(this);
    }

    public boolean isBackground() {
        return this.sActivityTask.size() == 0;
    }
}