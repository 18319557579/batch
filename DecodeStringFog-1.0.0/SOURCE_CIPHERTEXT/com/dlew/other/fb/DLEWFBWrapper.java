package com.dlew.other.fb;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.lib.DLEWSDK;
import com.dlew.net.UMKNet;
import com.elvishew.xlog.XLog;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/fb/DLEWFBWrapper.class */
public class DLEWFBWrapper {
    public static void init() {
        if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(DLEWSDK.getApplication()) != 0) {
            XLog.e(StringFog.decrypt("8dcr+mAXuKIqNaEXrZYgyVM363qY2nf4Olz8EOniN6E8EAL0fhf6JcXd\n", "trhEnQxymEQ=\n"));
            return;
        }
        String string = SPUtil.getString(StringFog.decrypt("Mee0TWjDUbko+qlDb8w=\n", "d67mCCqCAvw=\n"), null);
        if (!TextUtils.isEmpty(string)) {
            UMKNet.getInstance().setFirebaseToken(string, false);
        }
        FirebaseAnalytics.getInstance(DLEWSDK.getApplication()).getAppInstanceId().addOnCompleteListener(new OnCompleteListener<String>() { // from class: com.dlew.other.fb.DLEWFBWrapper.1
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    XLog.e(StringFog.decrypt("/uGRBs0SbZDD5o0c3w==\n", "t4/icqx+AfE=\n"), StringFog.decrypt("dbA6KU0G7NFP/jwuVUOFy1OqOidNArjMT7B7AmU=\n", "IN5bSyFjzKU=\n"));
                } else {
                    UMKNet.getInstance().setFirebaseAppInstanceId((String) task.getResult());
                    XLog.d(StringFog.decrypt("jvWYH+FmNFwUHk2bHZVSVBYLZpQdhBNbBR5mnlQ=\n", "Znsv+m7wcjU=\n") + ((String) task.getResult()));
                }
            }
        });
    }
}