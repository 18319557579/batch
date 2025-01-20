package EvLveE1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.dlew.StringFog;
import java.lang.reflect.Constructor;

/* compiled from: AgentInfo.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:EvLveE1/v1vLvLE.class */
public class v1vLvLE {
    @SuppressLint({"NewApi"})
    public static String EvLveE1(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

    public static String v1vLvLE(Context context) {
        String userAgentString;
        try {
            Class<?> cls = Class.forName(StringFog.decrypt("ZLiC81hFay5ys4TqXlghV2C0teRDWGZuYqWl7VZffGlm\n", "BdbmgTcsDwA=\n"));
            Class<?> cls2 = Class.forName(StringFog.decrypt("tkDuWojHZdegS+hDjtovrrJM3EGC2UKVtl35QYQ=\n", "1y6KKOeuAfk=\n"));
            Class<?>[] clsArr = new Class[2];
            clsArr[0] = Context.class;
            clsArr[1] = cls2;
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(clsArr);
            declaredConstructor.setAccessible(true);
            try {
                userAgentString = ((WebSettings) declaredConstructor.newInstance(context, null)).getUserAgentString();
                declaredConstructor.setAccessible(false);
            } catch (Throwable th) {
                declaredConstructor.setAccessible(false);
                throw th;
            }
        } catch (Exception unused) {
            userAgentString = new WebView(context).getSettings().getUserAgentString();
        }
        return userAgentString;
    }
}