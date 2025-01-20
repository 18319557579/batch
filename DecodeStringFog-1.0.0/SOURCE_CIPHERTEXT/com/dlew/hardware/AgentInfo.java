package com.dlew.hardware;

import EvLveE1.v1vLvLE;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Constructor;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/AgentInfo.class */
public class AgentInfo {
    public static String getDefaultUserAgent(Context context) {
        String userAgentString;
        int i = Build.VERSION.SDK_INT;
        if (i >= 17) {
            userAgentString = v1vLvLE.EvLveE1(context);
        } else if (i >= 16) {
            userAgentString = v1vLvLE.v1vLvLE(context);
        } else {
            try {
                Class[] clsArr = new Class[2];
                clsArr[0] = Context.class;
                clsArr[1] = WebView.class;
                Constructor declaredConstructor = WebSettings.class.getDeclaredConstructor(clsArr);
                declaredConstructor.setAccessible(true);
                try {
                    userAgentString = ((WebSettings) declaredConstructor.newInstance(context, null)).getUserAgentString();
                } finally {
                    declaredConstructor.setAccessible(false);
                }
            } catch (Exception unused) {
                userAgentString = new WebView(context).getSettings().getUserAgentString();
            }
        }
        return userAgentString;
    }
}