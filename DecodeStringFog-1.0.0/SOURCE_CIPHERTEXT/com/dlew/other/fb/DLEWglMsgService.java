package com.dlew.other.fb;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.net.UMKNet;
import com.elvishew.xlog.XLog;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/fb/DLEWglMsgService.class */
public class DLEWglMsgService extends FirebaseMessagingService {
    public void onNewToken(@NonNull String str) {
        super.onNewToken(str);
        XLog.d(StringFog.decrypt("9KD3Og2sAEhuSyK+8V9mVXNFJbG4\n", "HC5A34I6RiE=\n") + str);
        SPUtil.putString(StringFog.decrypt("FhHMcesxAvYPDNF/7D4=\n", "UFieNKlwUbM=\n"), str);
        UMKNet.getInstance().setFirebaseToken(str, true);
        if (TextUtils.isEmpty(UMKNet.Token)) {
            return;
        }
        UMKNet.getInstance().UpdateFirebaseToken(str);
    }

    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        XLog.d(StringFog.decrypt("CW339KjD47SdnCNwUxaFO1lxp5CPSQ==\n", "7/lBESBzpd0=\n") + remoteMessage.getNotification().getBody());
    }
}