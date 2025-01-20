package com.dlew.net;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWConfig;
import com.dlew.net.bean.DLEWBaseRsp;
import com.dlew.net.bean.DLEWULRsp;
import com.dlew.net.req.UAFRes;
import com.dlew.net.req.UFBUpdateRes;
import com.dlew.net.req.ULRes;
import com.dlew.net.req.UNotifyAddRes;
import com.dlew.net.req.UNotifyCancelRes;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.net.util.DLEWRequestCompleted;
import com.dlew.net.util.EncryptUtil;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/UMKNet.class */
public class UMKNet extends BaseNet {
    private static UMKNet singleton;
    private String firebaseToken = BuildConfig.VERSION_NAME;
    private String firebaseAppInstanceId = BuildConfig.VERSION_NAME;
    private boolean needUpdateFirebaseToken;
    private static final String LOGIN_API = StringFog.decrypt("DWr60IQ=\n", "IgyMovKH02Q=\n");
    private static final String API_FIREBASE_ADD = StringFog.decrypt("AETTtaE=\n", "Lze20NDamhA=\n");
    private static final String API_FIREBASE_CANCEL = StringFog.decrypt("xnyMYiQ=\n", "6QnlEVcaZ4Q=\n");
    private static final String API_FIREBASE_TOKEN_UPDATE = StringFog.decrypt("1YI/BWw=\n", "+uNNcRU4m0E=\n");
    private static final String API_APPSFLYER_ID = StringFog.decrypt("YYJKmn8=\n", "Tu066g9yar8=\n");
    private static final String TOKEN_KEY = StringFog.decrypt("lEnZN+tXzlGZ\n", "wAaScqUIhRQ=\n");
    public static String Token = null;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/UMKNet$EeleE.class */
    public class EeleE extends DLEWRequestCompleted<DLEWBaseRsp<Object>> {
        public EeleE() {
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<Object> dLEWBaseRsp) {
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/UMKNet$EvLveE1.class */
    public class EvLveE1 extends DLEWRequestCompleted<DLEWBaseRsp<DLEWULRsp>> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f23EvLveE1;

        public EvLveE1(DLEWNetCallback dLEWNetCallback) {
            this.f23EvLveE1 = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<DLEWULRsp> dLEWBaseRsp) {
            if (!z || dLEWBaseRsp.code != 0) {
                this.f23EvLveE1.OnCompleted(false, null);
                return;
            }
            UMKNet.Token = dLEWBaseRsp.data.tk;
            SPUtil.putString(StringFog.decrypt("Bto+u+SgkooL\n", "UpV1/qr/2c8=\n"), UMKNet.Token);
            this.f23EvLveE1.OnCompleted(true, dLEWBaseRsp.data);
            if (TextUtils.isEmpty(UMKNet.this.firebaseToken) || !UMKNet.this.needUpdateFirebaseToken) {
                return;
            }
            UMKNet uMKNet = UMKNet.this;
            uMKNet.UpdateFirebaseToken(uMKNet.firebaseToken);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/UMKNet$LvL11LLEl.class */
    public class LvL11LLEl extends DLEWRequestCompleted<DLEWBaseRsp<Object>> {
        public LvL11LLEl() {
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<Object> dLEWBaseRsp) {
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/UMKNet$evleeEelE.class */
    public class evleeEelE extends DLEWRequestCompleted<DLEWBaseRsp<Object>> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f25EvLveE1;

        public evleeEelE(DLEWNetCallback dLEWNetCallback) {
            this.f25EvLveE1 = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<Object> dLEWBaseRsp) {
            if (z && dLEWBaseRsp.code == 0) {
                DLEWNetCallback dLEWNetCallback = this.f25EvLveE1;
                if (dLEWNetCallback != null) {
                    dLEWNetCallback.OnCompleted(true, dLEWBaseRsp);
                    return;
                }
                return;
            }
            DLEWNetCallback dLEWNetCallback2 = this.f25EvLveE1;
            if (dLEWNetCallback2 != null) {
                dLEWNetCallback2.OnCompleted(false, null);
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/UMKNet$v1vLvLE.class */
    public class v1vLvLE extends DLEWRequestCompleted<DLEWBaseRsp<Object>> {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f26EvLveE1;

        public v1vLvLE(DLEWNetCallback dLEWNetCallback) {
            this.f26EvLveE1 = dLEWNetCallback;
        }

        @Override // com.dlew.net.util.DLEWRequestCompleted
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public void OnCompleted(boolean z, DLEWBaseRsp<Object> dLEWBaseRsp) {
            if (z && dLEWBaseRsp.code == 0) {
                DLEWNetCallback dLEWNetCallback = this.f26EvLveE1;
                if (dLEWNetCallback != null) {
                    dLEWNetCallback.OnCompleted(true, dLEWBaseRsp);
                    return;
                }
                return;
            }
            DLEWNetCallback dLEWNetCallback2 = this.f26EvLveE1;
            if (dLEWNetCallback2 != null) {
                dLEWNetCallback2.OnCompleted(false, null);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.net.UMKNet>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static UMKNet getInstance() {
        if (singleton == null) {
            ?? r0 = UMKNet.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new UMKNet();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    public static String getToken() {
        if (Token == null) {
            Token = SPUtil.getString(TOKEN_KEY, null);
        }
        return Token;
    }

    private UMKNet() {
        DLEWConfig.getInstance();
        this.url = DLEWConfig.getServerUrl();
        this.needAccessToken = true;
    }

    public void setFirebaseToken(String str, boolean z) {
        this.firebaseToken = str;
        if (this.needUpdateFirebaseToken) {
            return;
        }
        this.needUpdateFirebaseToken = z;
    }

    public void setFirebaseAppInstanceId(String str) {
        this.firebaseAppInstanceId = str;
    }

    public void Login(DLEWNetCallback<DLEWULRsp> dLEWNetCallback) {
        ULRes uLRes = new ULRes();
        uLRes.bb = StringFog.decrypt("hUGGdA==\n", "9S71AGY33j8=\n");
        String str = LOGIN_API;
        uLRes.aa = str;
        PostAsync(uLRes, str, new EvLveE1(dLEWNetCallback));
    }

    public void AddNotification(String str, float f, String str2, String str3, String str4, @Nullable DLEWNetCallback<DLEWBaseRsp> dLEWNetCallback) {
        if (TextUtils.isEmpty(str)) {
            XLog.e(StringFog.decrypt("TCBSnjJEd8MCfmn6XUAgpR4y054sS3X1KnFJ/FBLF6kSLQ7SAg==\n", "qpfpe7jkkU0=\n"));
            dLEWNetCallback.OnCompleted(false, null);
            return;
        }
        if (TextUtils.isEmpty(this.firebaseToken)) {
            XLog.e(StringFog.decrypt("dCOP2qf4vOI6fbS+yPzrhCYxDllEKj8O8+dRH1k3MQn8tNCHl7/z1g==\n", "kpQ0Py1YWmw=\n"));
            dLEWNetCallback.OnCompleted(false, null);
            return;
        }
        UNotifyAddRes uNotifyAddRes = new UNotifyAddRes();
        DLEWConfig.getInstance();
        uNotifyAddRes.a = DLEWConfig.getUmkAppId();
        uNotifyAddRes.content = str3;
        if (TextUtils.isEmpty(str4)) {
            str4 = BuildConfig.VERSION_NAME;
        }
        uNotifyAddRes.more = str4;
        uNotifyAddRes.bt = str2;
        uNotifyAddRes.tk = this.firebaseToken;
        StringBuilder append = new StringBuilder().append(str).append(PhoneInfoWrapper.getGAID());
        DLEWConfig.getInstance();
        uNotifyAddRes.id = EncryptUtil.md5Decode32(append.append(DLEWConfig.getAppKey()).toString());
        uNotifyAddRes.bb = StringFog.decrypt("AbUhOQ==\n", "cdpSTShZtiw=\n");
        String str5 = API_FIREBASE_ADD;
        uNotifyAddRes.aa = str5;
        PostAsync(uNotifyAddRes, str5, new v1vLvLE(dLEWNetCallback));
    }

    public void RemoveNotification(@NonNull String str, @Nullable DLEWNetCallback<DLEWBaseRsp> dLEWNetCallback) {
        if (TextUtils.isEmpty(str)) {
            XLog.e(StringFog.decrypt("c9M636M9eDU+tSy48BEvUyL5ltyBGnoDFroMvv0aGF8u5kuQrw==\n", "llysORW1nrs=\n"));
            dLEWNetCallback.OnCompleted(false, null);
            return;
        }
        UNotifyCancelRes uNotifyCancelRes = new UNotifyCancelRes();
        uNotifyCancelRes.tid = EncryptUtil.md5Decode32(str + PhoneInfoWrapper.getGAID() + DLEWConfig.getAppKey());
        String str2 = API_FIREBASE_CANCEL;
        uNotifyCancelRes.aa = CrateGetApiWrapper(uNotifyCancelRes, str2);
        uNotifyCancelRes.bb = StringFog.decrypt("H47q\n", "eOue+E/qA0U=\n");
        PostAsync(uNotifyCancelRes, str2, new evleeEelE(dLEWNetCallback));
    }

    public void UpdateFirebaseToken(@NonNull String str) {
        UFBUpdateRes uFBUpdateRes = new UFBUpdateRes();
        uFBUpdateRes.tk = str;
        uFBUpdateRes.appid = this.firebaseAppInstanceId;
        String str2 = API_FIREBASE_TOKEN_UPDATE;
        uFBUpdateRes.aa = CrateGetApiWrapper(uFBUpdateRes, str2);
        uFBUpdateRes.bb = StringFog.decrypt("2dyZ\n", "vrntsXw5JRk=\n");
        PostAsync(uFBUpdateRes, str2, new EeleE());
    }

    public void UploadAppsflyerID(@NonNull String str) {
        UAFRes uAFRes = new UAFRes();
        uAFRes.appsflyerID = str;
        String str2 = API_APPSFLYER_ID;
        uAFRes.aa = CrateGetApiWrapper(uAFRes, str2);
        uAFRes.bb = StringFog.decrypt("AJ7z\n", "Z/uHluadalg=\n");
        PostAsync(uAFRes, str2, new LvL11LLEl());
    }
}