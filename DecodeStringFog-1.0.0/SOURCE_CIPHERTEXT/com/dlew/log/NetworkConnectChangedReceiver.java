package com.dlew.log;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.dlew.StringFog;
import com.dlew.common.NetUtils;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/NetworkConnectChangedReceiver.class */
public class NetworkConnectChangedReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null || !intent.getAction().equals(StringFog.decrypt("lJIHo88Be0ObmRf/wwdxA9u/LJ/uLVw5vKoqhfk3XCW0siSU\n", "9fxj0aBoH20=\n"))) {
            return;
        }
        if (!NetUtils.isNetConnected(context)) {
            EventTrackManager.getInstance().StopTimer();
        } else {
            if (ActivityLifecycleManager.getInstance().isBackground()) {
                return;
            }
            EventTrackManager.getInstance().StartTimer();
        }
    }
}