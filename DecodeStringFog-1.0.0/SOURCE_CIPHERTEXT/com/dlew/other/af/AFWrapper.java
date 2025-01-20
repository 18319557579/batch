package com.dlew.other.af;

import android.content.Context;
import android.text.TextUtils;
import com.appsflyer.AppsFlyerConversionListener;
import com.appsflyer.AppsFlyerLib;
import com.appsflyer.share.LinkGenerator;
import com.appsflyer.share.ShareInviteHelper;
import com.dlew.StringFog;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWConfig;
import com.dlew.lib.DLEWSDK;
import com.dlew.log.EventTrackManager;
import com.dlew.log.GGAnalysis;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.ThreadExecutorProxy;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.Map;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/af/AFWrapper.class */
public class AFWrapper implements AppsFlyerConversionListener {
    private static AFWrapper singleton;
    private static Map<String, Object> afMap;
    private DLEWNetCallback<DLEWGyData> _completedCallback;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/af/AFWrapper$EvLveE1.class */
    public class EvLveE1 implements Runnable {
        public EvLveE1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            EventTrackManager.getInstance().SendBaseEvent();
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/af/AFWrapper$v1vLvLE.class */
    public class v1vLvLE extends TypeToken<Map<String, Object>> {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.other.af.AFWrapper>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static AFWrapper getInstance() {
        if (singleton == null) {
            ?? r0 = AFWrapper.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new AFWrapper();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    public static String generateInviteLink(String str, String str2) {
        if (TextUtils.isEmpty(DLEWConfig.getOneLinkID())) {
            XLog.e(StringFog.decrypt("cP/FXa65//84Qw7SC1t2J9oKAd8LfVytKuKACKo=\n", "lmNvtCs0GEI=\n"));
            return null;
        }
        AppsFlyerLib.getInstance().setAppInviteOneLink(str);
        LinkGenerator generateInviteUrl = ShareInviteHelper.generateInviteUrl(DLEWSDK.getApplication());
        generateInviteUrl.addParameter(StringFog.decrypt("Hhy5EASn2vkTFw==\n", "d3LPeXDCmZY=\n"), str2);
        return generateInviteUrl.generateLink();
    }

    public static String getAFSiteId() {
        return getAFValue(StringFog.decrypt("1Lr+zW/RtE3R\n", "tdyhvgal0SQ=\n"));
    }

    private static String getAFValue(String str) {
        if (afMap == null) {
            afMap = (Map) new Gson().fromJson(PhoneInfoWrapper.getConversionData().data, new v1vLvLE());
        }
        Map<String, Object> map = afMap;
        return (map == null || !map.containsKey(str)) ? BuildConfig.VERSION_NAME : afMap.get(str).toString();
    }

    public void init(Context context, DLEWNetCallback<DLEWGyData> dLEWNetCallback) {
        this._completedCallback = dLEWNetCallback;
        if (!PhoneInfoWrapper.getConversionData().IsUnknow()) {
            this._completedCallback.OnCompleted(true, PhoneInfoWrapper.getConversionData());
            ThreadExecutorProxy.getInstance().runOnAsyncThread(new EvLveE1(), 2000L);
        }
        AppsFlyerLib.getInstance().setDebugLog(DLEWConfig.isPrintLog());
        AppsFlyerLib.getInstance().init(DLEWConfig.getAfDevKey(), this, context);
        AppsFlyerLib.getInstance().start(context);
    }

    public void onConversionDataSuccess(Map<String, Object> map) {
        XLog.d(map);
        String json = new Gson().toJson(map);
        DLEWGyData dLEWGyData = new DLEWGyData();
        dLEWGyData.success = true;
        dLEWGyData.data = json;
        if (map.containsKey(StringFog.decrypt("6+/mfLk0YlPz+OFw\n", "hoqCFdhrETw=\n"))) {
            String obj = map.get(StringFog.decrypt("Oo01bnc+gd8imjJi\n", "V+hRBxZh8rA=\n")).toString();
            String str = obj;
            if (TextUtils.isEmpty(obj)) {
                str = (map.containsKey(StringFog.decrypt("sBHCHTU=\n", "2WKde1eCpEE=\n")) && map.get(StringFog.decrypt("hQNTBQI=\n", "7HAMY2B2uFk=\n")).equals(StringFog.decrypt("2MKVZQ==\n", "rLDgAO3xDf4=\n"))) ? StringFog.decrypt("SeszhqRiLbRQyzSQ\n", "D4pQ48YNQt8=\n") : StringFog.decrypt("KLzLCUZK\n", "RtOlZjQtaYg=\n");
            }
            if (!map.containsKey(StringFog.decrypt("cyGWTqPm\n", "EkbzIMCfoxQ=\n")) || map.get(StringFog.decrypt("X3e8Sig8\n", "PhDZJEtFuNo=\n")) == null || TextUtils.isEmpty(map.get(StringFog.decrypt("d8USuFNl\n", "FqJ31jAcmlo=\n")).toString())) {
                dLEWGyData.agency = StringFog.decrypt("ivI56g==\n", "5IdVhgu7Coc=\n");
            } else {
                dLEWGyData.agency = map.get(StringFog.decrypt("my5Twqfp\n", "+kk2rMSQtxw=\n")).toString();
            }
            dLEWGyData.channel = str;
            if (str.equals(StringFog.decrypt("9/HZa0O99zH44e9+Vr4=\n", "lpeGCjPNqFg=\n"))) {
                dLEWGyData.inviteCode = map.get(StringFog.decrypt("NwJWyPxy62A6CQ==\n", "XmwgoYgXqA8=\n")).toString();
            }
        } else {
            dLEWGyData.channel = map.get(StringFog.decrypt("VXdTrGZmAcZH\n", "NBEM3xIHdbM=\n")).toString();
        }
        if (PhoneInfoWrapper.getConversionData().IsUnknow()) {
            PhoneInfoWrapper.putConversionData(dLEWGyData);
            if (!TextUtils.isEmpty(DLEWConfig.getAppKey())) {
                GGAnalysis.track(StringFog.decrypt("wFQizoGW0Z3N\n", "oTJhpuD4v/g=\n"), StringFog.decrypt("Cpya6g==\n", "fuXqj1Vedtg=\n"), dLEWGyData.channel);
                EventTrackManager.getInstance().SendBaseEvent();
            }
            this._completedCallback.OnCompleted(true, dLEWGyData);
        }
    }

    public void onConversionDataFail(String str) {
        XLog.d(str);
        DLEWGyData dLEWGyData = new DLEWGyData();
        dLEWGyData.success = false;
        dLEWGyData.errorMsg = str;
        if (PhoneInfoWrapper.getConversionData().IsUnknow()) {
            this._completedCallback.OnCompleted(false, null);
            if (TextUtils.isEmpty(DLEWConfig.getAppKey())) {
                return;
            }
            EventTrackManager.getInstance().SendBaseEvent();
            GGAnalysis.track(StringFog.decrypt("qbsWxcno0mukmCffx/Q=\n", "yN1VraiGvA4=\n"), StringFog.decrypt("Fzk8Uw==\n", "Y0BMNiF77ec=\n"), str);
        }
    }

    public void onAppOpenAttribution(Map<String, String> map) {
        XLog.d(map);
    }

    public void onAttributionFailure(String str) {
    }
}