package com.dlew.lib;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import androidx.lifecycle.ViewModel;
import com.appsflyer.AppsFlyerLib;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.common.ServerTimeUtil;
import com.dlew.events.DLEWglEvent;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.log.ActivityLifecycleManager;
import com.dlew.log.EventTrackManager;
import com.dlew.log.GGAnalysis;
import com.dlew.net.GameNet;
import com.dlew.net.StrategyNet;
import com.dlew.net.UMKNet;
import com.dlew.net.bean.DLEWABData;
import com.dlew.net.bean.DLEWABField;
import com.dlew.net.bean.DLEWSettingInfo;
import com.dlew.net.bean.DLEWUInfo;
import com.dlew.net.bean.DLEWULRsp;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.ThreadExecutorProxy;
import com.dlew.other.ad.DLEWAd;
import com.dlew.other.af.AFWrapper;
import com.dlew.other.af.DLEWGyData;
import com.dlew.other.fb.DLEWFBWrapper;
import com.elvishew.xlog.LogConfiguration;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import java.math.BigDecimal;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK.class */
public class DLEWSDK extends ViewModel {
    private static boolean _initCompleted;
    private static DLEWUInfo _uInfo;
    private static DLEWNetCallback<DLEWUInfo> _initCompletedCallback;
    private static double[] m_LTVThresholds;
    private static String m_adThrottle;
    private static String m_localValueConfig;
    private static String m_typeBiddingConfig;
    private static boolean m_trackThrottle;
    private static int _initTaskCompletedCount;
    private static Application application;
    private static Activity activity;
    public static final String USER_INFO_KEY = StringFog.decrypt("EfkCTPYybIwd\n", "RLBMCrltJ8k=\n");
    static final String kLocalValueConfig = StringFog.decrypt("0sZQpDzHUcTrzHCqPvdZzw==\n", "nqkzxVCRMKg=\n");

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$EeleE.class */
    public class EeleE implements DLEWNetCallback<DLEWGyData> {
        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWGyData dLEWGyData) {
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$EvLeE11LLE.class */
    public class EvLeE11LLE implements DLEWNetCallback<DLEWABData[]> {

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$EvLeE11LLE$EvLveE1.class */
        public class EvLveE1 implements Runnable {
            public EvLveE1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DLEWSDK._initCompletedCallback.OnCompleted(true, DLEWSDK._uInfo);
                DLEWBus.post(new DLEWglEvent(DLEWglEvent.TYPE.ON_SDK_INITIALIZED));
            }
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWABData[] dLEWABDataArr) {
            if (z && dLEWABDataArr != null && dLEWABDataArr.length != 0) {
                for (DLEWABData dLEWABData : dLEWABDataArr) {
                    for (DLEWABField dLEWABField : dLEWABData.fields) {
                        if (dLEWABField.fkey.equals(StringFog.decrypt("XLKsJZmBgJBluIwrm7GImw==\n", "EN3PRPXX4fw=\n"))) {
                            if (!TextUtils.isEmpty(dLEWABField.fvalue)) {
                                String unused = DLEWSDK.m_localValueConfig = dLEWABField.fvalue;
                                SPUtil.putString(StringFog.decrypt("UOoU8KAqJFBp4DT+ohosWw==\n", "HIV3kcx8RTw=\n"), DLEWSDK.m_localValueConfig);
                                if (EventTrackManager.getInstance().strategyId.equals(StringFog.decrypt("Hg==\n", "L+JYe3C33ns=\n"))) {
                                    EventTrackManager.getInstance().strategyId = dLEWABData.vid;
                                }
                            }
                        } else if (dLEWABField.fkey.equals(StringFog.decrypt("AMULRQXofkok/jF1\n", "TJFdEW2aGzk=\n"))) {
                            double[] unused2 = DLEWSDK.m_LTVThresholds = (double[]) new Gson().fromJson(dLEWABField.fvalue, double[].class);
                        } else if (dLEWABField.fkey.equals(StringFog.decrypt("hGHgbVp9WrapQA==\n", "xSW0BSgSLsI=\n"))) {
                            String unused3 = DLEWSDK.m_adThrottle = dLEWABField.fvalue;
                        } else if (dLEWABField.fkey.equals(StringFog.decrypt("ABplHutlOtw7HHAR5Q==\n", "VGgEfYAxUq4=\n"))) {
                            if (dLEWABField.fvalue.equals(StringFog.decrypt("+w==\n", "yme84eQFESE=\n"))) {
                                boolean unused4 = DLEWSDK.m_trackThrottle = true;
                            }
                        } else if (dLEWABField.fkey.equals(StringFog.decrypt("sApqMhGc57iNHX0=\n", "5HMaV1P1g9w=\n"))) {
                            if (!TextUtils.isEmpty(dLEWABField.fvalue)) {
                                String unused5 = DLEWSDK.m_typeBiddingConfig = dLEWABField.fvalue;
                            }
                            if (EventTrackManager.getInstance().strategyId.equals(StringFog.decrypt("hQ==\n", "tJRIAIOD1vA=\n"))) {
                                EventTrackManager.getInstance().strategyId = dLEWABData.vid;
                            }
                        }
                    }
                }
            }
            if (DLEWConfig.isEnableLocalValue()) {
                DLEWSDK.InitEva(true);
            } else {
                boolean unused6 = DLEWSDK._initCompleted = true;
                ThreadExecutorProxy.getInstance().runOnMainThread(new EvLveE1());
            }
            DLEWSDK.access$008();
            if (DLEWSDK._initTaskCompletedCount == 2) {
                DLEWAd.startThrottle(DLEWSDK.m_adThrottle, DLEWSDK.activity);
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$EvLveE1.class */
    public class EvLveE1 implements DLEWNetCallback<Object> {
        @Override // com.dlew.net.util.DLEWNetCallback
        public void OnCompleted(boolean z, Object obj) {
            DLEWSDK.access$008();
            if (DLEWSDK._initTaskCompletedCount == 2) {
                DLEWAd.startThrottle(DLEWSDK.m_adThrottle, DLEWSDK.activity);
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$LvL11LLEl.class */
    public class LvL11LLEl implements DLEWNetCallback<DLEWULRsp> {
        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWULRsp dLEWULRsp) {
            if (z) {
                DLEWSDK._uInfo.uid = dLEWULRsp.uid;
                DLEWSDK._uInfo.huoyueDays = Double.valueOf(dLEWULRsp.more.get(StringFog.decrypt("aXQkTe7C4nNpbiM=\n", "CBdQJJinvRc=\n")).toString()).intValue();
                DLEWSDK._uInfo.zhuceDays = Double.valueOf(dLEWULRsp.more.get(StringFog.decrypt("GuIIPjDiwww34w4uMA==\n", "aIdvV0OWpn4=\n")).toString()).intValue();
                DLEWSDK._uInfo.createTimeStamp = new BigDecimal(dLEWULRsp.more.get(StringFog.decrypt("IbLfk1qxUZ4rrd8=\n", "QsC68i7UDuo=\n")).toString()).longValue();
                DLEWSDK._uInfo.now = dLEWULRsp.now;
                DLEWSDK._uInfo.local = dLEWULRsp.local;
                DLEWSDK._uInfo.yaoqingma = dLEWULRsp.inviteC;
                ServerTimeUtil.setServerTime(DLEWSDK._uInfo.now);
                SPUtil.putObject(StringFog.decrypt("4AbvZMr0XbPs\n", "tU+hIoWrFvY=\n"), DLEWSDK._uInfo);
                if (!SPUtil.getBoolean(StringFog.decrypt("v0DrXUUnBgCSQe1NRQw=\n", "zSWMNDZTY3I=\n") + DLEWSDK._uInfo.zhuceDays, false)) {
                    GGAnalysis.afTrack(StringFog.decrypt("Oo1w/vA7/O4XjHbu8BA=\n", "SOgXl4NPmZw=\n") + DLEWSDK._uInfo.zhuceDays, null);
                    SPUtil.putBoolean(StringFog.decrypt("P14uB2yb2V0SXygXbLA=\n", "TTtJbh/vvC8=\n") + DLEWSDK._uInfo.zhuceDays, true);
                }
                if (!SPUtil.getBoolean(StringFog.decrypt("WoYAY1kReu1Jnwg=\n", "L/ZsDDh1JYw=\n"), false)) {
                    UMKNet.getInstance().UploadAppsflyerID(AppsFlyerLib.getInstance().getAppsFlyerUID(DLEWSDK.getApplication()));
                }
            }
            DLEWSDK.FetchStrategy();
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$evleeEelE.class */
    public class evleeEelE implements DLEWNetCallback<DLEWGyData> {
        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWGyData dLEWGyData) {
            DLEWSDK.Login();
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$v1vLvLE.class */
    public class v1vLvLE implements DLEWNetCallback<String> {
        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, String str) {
            if (!PhoneInfoWrapper.getConversionData().IsUnknow()) {
                DLEWSDK.Login();
            }
            if (SPUtil.getBoolean(StringFog.decrypt("9JQieQoL7YLznjs=\n", "st1wKl5UudA=\n"), false)) {
                return;
            }
            EventTrackManager.getInstance().SendAppOpen();
            EventTrackManager.getInstance().SendAppFront();
            SPUtil.putBoolean(StringFog.decrypt("4+Z7/fgBQJ3k7GI=\n", "pa8prqxeFM8=\n"), true);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$vl1L.class */
    public class vl1L implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            DLEWSDK._initCompletedCallback.OnCompleted(true, DLEWSDK._uInfo);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$vv11e.class */
    public class vv11e implements DLEWNetCallback<DLEWSettingInfo[]> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ boolean f5EvLveE1;

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$vv11e$EvLveE1.class */
        public class EvLveE1 implements Runnable {
            public EvLveE1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DLEWSDK._initCompletedCallback.OnCompleted(true, DLEWSDK._uInfo);
            }
        }

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$vv11e$v1vLvLE.class */
        public class v1vLvLE implements Runnable {
            public v1vLvLE() {
            }

            @Override // java.lang.Runnable
            public void run() {
                DLEWSDK._initCompletedCallback.OnCompleted(true, DLEWSDK._uInfo);
            }
        }

        public vv11e(boolean z) {
            this.f5EvLveE1 = z;
        }

        @Override // com.dlew.net.util.DLEWNetCallback
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWSettingInfo[] dLEWSettingInfoArr) {
            if (!z || dLEWSettingInfoArr == null || dLEWSettingInfoArr.length == 0) {
                boolean unused = DLEWSDK._initCompleted = true;
                if (this.f5EvLveE1) {
                    ThreadExecutorProxy.getInstance().runOnMainThread(new v1vLvLE());
                    return;
                }
                return;
            }
            for (DLEWSettingInfo dLEWSettingInfo : dLEWSettingInfoArr) {
                if (dLEWSettingInfo.code.equals(StringFog.decrypt("g7YfkYyrT/+6vD+fjptH9A==\n", "z9l88OD9LpM=\n"))) {
                    SPUtil.putString(StringFog.decrypt("94UF+HBFrEDOjyX2cnWkSw==\n", "u+pmmRwTzSw=\n"), dLEWSettingInfo.setting.toString());
                    boolean unused2 = DLEWSDK._initCompleted = true;
                    if (this.f5EvLveE1) {
                        ThreadExecutorProxy.getInstance().runOnMainThread(new EvLveE1());
                    }
                }
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWSDK$vvlEEv1lv.class */
    public class vvlEEv1lv implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            DLEWSDK._initCompletedCallback.OnCompleted(true, DLEWSDK._uInfo);
        }
    }

    public static boolean getInitCompleted() {
        return _initCompleted;
    }

    public static DLEWUInfo getUserInfo() {
        if (_uInfo == null) {
            DLEWUInfo dLEWUInfo = (DLEWUInfo) SPUtil.getObject(USER_INFO_KEY, DLEWUInfo.class);
            _uInfo = dLEWUInfo;
            if (dLEWUInfo == null) {
                _uInfo = new DLEWUInfo();
            }
        }
        return _uInfo;
    }

    public static double[] getLTVThresholds() {
        return m_LTVThresholds;
    }

    public static String getTypeBiddingConfig() {
        return m_typeBiddingConfig;
    }

    public static boolean isTrackThrottle() {
        return m_trackThrottle;
    }

    public static Application getApplication() {
        return application;
    }

    public static Activity getActivity() {
        return activity;
    }

    public static void Init(Activity activity2, DLEWNetCallback<DLEWUInfo> dLEWNetCallback) {
        _initCompletedCallback = dLEWNetCallback;
        activity = activity2;
        _uInfo = new DLEWUInfo();
        InitAppsflyer(application);
        DLEWAd.init(activity, new EvLveE1());
        DLEWFBWrapper.init();
    }

    private static void InitAppsflyer(Application application2) {
        PhoneInfoWrapper.Init(new v1vLvLE());
        if (PhoneInfoWrapper.getConversionData().IsUnknow()) {
            AFWrapper.getInstance().init(application, new evleeEelE());
        } else {
            AFWrapper.getInstance().init(application, new EeleE());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void Login() {
        if (TextUtils.isEmpty(_uInfo.uid)) {
            UMKNet.getInstance().Login(new LvL11LLEl());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void FetchStrategy() {
        StrategyNet.getInstance().Fetch(StringFog.decrypt("PfU8m4+Bo1QE/xyVjbGrX13OLZuAvJZQA/Urjo+y7nk1zjeIjKO2VBS2C4OTsoBRFf42lIQ=\n", "cZpf+uPXwjg=\n"), new EvLeE11LLE());
    }

    public static void InitEva(boolean z) {
        if (DLEWConfig.isForceLocalValueConfig()) {
            _initCompleted = true;
            if (z) {
                ThreadExecutorProxy.getInstance().runOnMainThread(new vvlEEv1lv());
                return;
            }
            return;
        }
        if (TextUtils.isEmpty(m_localValueConfig)) {
            GameNet.getInstance().FetchConfig(kLocalValueConfig, new vv11e(z));
            return;
        }
        _initCompleted = true;
        if (z) {
            ThreadExecutorProxy.getInstance().runOnMainThread(new vl1L());
        }
    }

    public static void onApplicationCreate(Application application2) {
        application = application2;
        PhoneInfoWrapper.putContext(application2);
        XLog.init(new LogConfiguration.Builder().logLevel(DLEWConfig.isPrintLog() ? Integer.MIN_VALUE : Integer.MAX_VALUE).tag(StringFog.decrypt("Ckm+5XKizgEVRYI=\n", "UQ7fiRPat1I=\n")).build());
        ActivityLifecycleManager.getInstance().onApplicationCreate(application2);
    }

    public static void onApplicationTerminate(Application application2) {
        ActivityLifecycleManager.getInstance().onApplicationTerminate(application2);
    }

    public static /* synthetic */ int access$008() {
        int i = _initTaskCompletedCount;
        _initTaskCompletedCount = i + 1;
        return i;
    }
}