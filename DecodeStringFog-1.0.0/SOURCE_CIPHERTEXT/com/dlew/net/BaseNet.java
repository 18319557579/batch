package com.dlew.net;

import android.text.TextUtils;
import android.util.Base64;
import com.dlew.StringFog;
import com.dlew.lib.DLEWConfig;
import com.dlew.net.util.DLEWRequestCompleted;
import com.dlew.net.util.EncryptUtil;
import com.dlew.net.util.HttpUtil;
import com.dlew.net.util.ThreadExecutorProxy;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.XLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.lang.reflect.Field;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/BaseNet.class */
public class BaseNet {
    private static final String GET_SUFFIX = StringFog.decrypt("xWbb1cGaDg==\n", "+gW/tLX7M/c=\n");
    private static final String SYMBOL_EQUAL = StringFog.decrypt("jA==\n", "sZegtmd/Bys=\n");
    private static final String SYMBOL_AND = StringFog.decrypt("5w==\n", "wazaDWerjRU=\n");
    protected String url = DLEWConfig.getServerUrl();
    protected boolean needAccessToken = false;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/BaseNet$EeleE.class */
    public class EeleE implements HttpUtil.RespCallback {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ String f14EvLveE1;
        public final /* synthetic */ DLEWRequestCompleted v1vLvLE;

        public EeleE(String str, DLEWRequestCompleted dLEWRequestCompleted) {
            this.f14EvLveE1 = str;
            this.v1vLvLE = dLEWRequestCompleted;
        }

        @Override // com.dlew.net.util.HttpUtil.RespCallback
        public void onSuccess(int i, String str) {
            try {
                String Decrypt = EncryptUtil.Decrypt(str, BaseNet.this.url);
                Gson gson = new Gson();
                XLog.d(StringFog.decrypt("lK1jB/FSjw==\n", "fBL34mrMtc4=\n") + this.f14EvLveE1 + StringFog.decrypt("35I=\n", "gM2JlU+gKe4=\n") + Decrypt);
                DLEWRequestCompleted dLEWRequestCompleted = this.v1vLvLE;
                dLEWRequestCompleted.OnCompleted(true, gson.fromJson(Decrypt, dLEWRequestCompleted.mType));
            } catch (Exception e) {
                XLog.e(this.f14EvLveE1 + StringFog.decrypt("1ec=\n", "irg76QeKcwU=\n") + e.toString());
                XLog.e((Object[]) e.getStackTrace());
                this.v1vLvLE.OnCompleted(false, null);
            }
        }

        @Override // com.dlew.net.util.HttpUtil.RespCallback
        public void onError(int i, Throwable th) {
            XLog.e(this.f14EvLveE1 + StringFog.decrypt("+Q==\n", "wy5aLJEF5aw=\n") + th.toString());
            this.v1vLvLE.OnCompleted(false, null);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/BaseNet$EvLveE1.class */
    public class EvLveE1 implements Runnable {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ Object f15EvLveE1;
        public final /* synthetic */ String v1vLvLE;
        public final /* synthetic */ DLEWRequestCompleted evleeEelE;

        public EvLveE1(Object obj, String str, DLEWRequestCompleted dLEWRequestCompleted) {
            this.f15EvLveE1 = obj;
            this.v1vLvLE = str;
            this.evleeEelE = dLEWRequestCompleted;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseNet.this.Post(this.f15EvLveE1, this.v1vLvLE, this.evleeEelE);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/BaseNet$evleeEelE.class */
    public class evleeEelE implements HttpUtil.RespCallback {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ String f16EvLveE1;
        public final /* synthetic */ DLEWRequestCompleted v1vLvLE;

        public evleeEelE(String str, DLEWRequestCompleted dLEWRequestCompleted) {
            this.f16EvLveE1 = str;
            this.v1vLvLE = dLEWRequestCompleted;
        }

        @Override // com.dlew.net.util.HttpUtil.RespCallback
        public void onSuccess(int i, String str) {
            try {
                String Decrypt = EncryptUtil.Decrypt(str, BaseNet.this.url);
                Gson create = new GsonBuilder().serializeNulls().create();
                XLog.d(StringFog.decrypt("onHRBNL7Lw==\n", "Ss5F4UllFZ4=\n") + this.f16EvLveE1 + StringFog.decrypt("8QA=\n", "rl+qgnNBdxk=\n") + Decrypt);
                DLEWRequestCompleted dLEWRequestCompleted = this.v1vLvLE;
                dLEWRequestCompleted.OnCompleted(true, create.fromJson(Decrypt, dLEWRequestCompleted.mType));
            } catch (Exception e) {
                XLog.d(this.f16EvLveE1 + StringFog.decrypt("uZA=\n", "5s/jjhrri9k=\n") + e.toString());
                this.v1vLvLE.OnCompleted(false, null);
            }
        }

        @Override // com.dlew.net.util.HttpUtil.RespCallback
        public void onError(int i, Throwable th) {
            XLog.d(this.f16EvLveE1 + StringFog.decrypt("rg==\n", "lOOfHa67o7s=\n") + th.toString());
            this.v1vLvLE.OnCompleted(false, null);
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/BaseNet$v1vLvLE.class */
    public class v1vLvLE implements Runnable {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ Object f17EvLveE1;
        public final /* synthetic */ String v1vLvLE;
        public final /* synthetic */ DLEWRequestCompleted evleeEelE;

        public v1vLvLE(Object obj, String str, DLEWRequestCompleted dLEWRequestCompleted) {
            this.f17EvLveE1 = obj;
            this.v1vLvLE = str;
            this.evleeEelE = dLEWRequestCompleted;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseNet.this.Get(this.f17EvLveE1, this.v1vLvLE, this.evleeEelE);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void Get(Object obj, String str, DLEWRequestCompleted<T> dLEWRequestCompleted) {
        String str2;
        String str3 = StringFog.decrypt("3jipe9M9\n", "nFnaErAdGGw=\n") + Base64.encodeToString((DLEWConfig.getAppKey() + StringFog.decrypt("hA==\n", "voEW4EsCrqQ=\n") + DLEWConfig.getAppSecret()).getBytes(), 2);
        StringBuilder sb = new StringBuilder();
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object obj2 = field.get(obj);
                if (obj2 != null) {
                    sb.append(field.getName()).append(SYMBOL_EQUAL).append(obj2).append(SYMBOL_AND);
                }
            }
        } catch (Exception unused) {
        }
        try {
            str2 = EncryptUtil.Encrypt(sb.toString(), this.url);
        } catch (Exception unused2) {
            str2 = str;
        }
        XLog.d(StringFog.decrypt("Zzx55VU/qQ==\n", "j5POA+S9k0A=\n") + str + StringFog.decrypt("MPk=\n", "b6YNJL1oiuU=\n") + sb.toString());
        String md5Decode32 = EncryptUtil.md5Decode32(sb.toString());
        sb.delete(0, sb.length());
        sb.append(str2.replace(StringFog.decrypt("VA==\n", "fxa3kBDQnBE=\n"), StringFog.decrypt("Yw==\n", "TrlFszFTddg=\n")).replace(StringFog.decrypt("7w==\n", "wLZ/8wTtGs8=\n"), StringFog.decrypt("8g==\n", "rVO2bQgNkOA=\n")));
        sb.insert(0, this.url + str + GET_SUFFIX);
        HttpUtil httpUtil = new HttpUtil(sb.toString(), HttpUtil.RequestMethod.GET);
        httpUtil.setAuthorization(str3);
        httpUtil.setContentType(StringFog.decrypt("0rfvHdbeM+baqPFe1c49/A==\n", "s8efcb+9UpI=\n"));
        httpUtil.setReadTimeout(15000);
        httpUtil.setConnectionTimeout(15000);
        httpUtil.setSign(md5Decode32);
        if (this.needAccessToken && !TextUtils.isEmpty(UMKNet.getToken())) {
            httpUtil.setAccessToken(UMKNet.getToken());
        }
        httpUtil.setRespCallback(new evleeEelE(str, dLEWRequestCompleted));
        httpUtil.executeAsyn(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void Post(Object obj, String str, DLEWRequestCompleted<T> dLEWRequestCompleted) {
        String str2;
        String str3 = StringFog.decrypt("/XMARF0i\n", "vxJzLT4CxTc=\n") + Base64.encodeToString((DLEWConfig.getAppKey() + StringFog.decrypt("8Q==\n", "yySC5nJGfio=\n") + DLEWConfig.getAppSecret()).getBytes(), 2);
        HttpUtil httpUtil = new HttpUtil(this.url, HttpUtil.RequestMethod.POST);
        httpUtil.setAuthorization(str3);
        httpUtil.setContentType(StringFog.decrypt("CdlXcbeAhT4BxkkytJCLJA==\n", "aKknHd7j5Eo=\n"));
        httpUtil.setReadTimeout(15000);
        httpUtil.setConnectionTimeout(15000);
        if (this.needAccessToken && !TextUtils.isEmpty(UMKNet.getToken())) {
            httpUtil.setAccessToken(UMKNet.getToken());
        }
        String json = new Gson().toJson(obj);
        try {
            str2 = EncryptUtil.Encrypt(json, this.url);
        } catch (Exception unused) {
            str2 = json;
        }
        XLog.d(StringFog.decrypt("tnlBE7ZrMw==\n", "Xtb29QfpCe4=\n") + this.url + str + StringFog.decrypt("ouk=\n", "/bZj19gWSTU=\n") + json);
        httpUtil.setSign(EncryptUtil.md5Decode32(json));
        httpUtil.setPostData(str2);
        httpUtil.setRespCallback(new EeleE(str, dLEWRequestCompleted));
        httpUtil.executeAsyn(true);
    }

    public <T> void PostAsync(Object obj, String str, DLEWRequestCompleted<T> dLEWRequestCompleted) {
        ThreadExecutorProxy.getInstance().runOnAsyncThread(new EvLveE1(obj, str, dLEWRequestCompleted));
    }

    public <T> void GetAsync(Object obj, String str, DLEWRequestCompleted<T> dLEWRequestCompleted) {
        ThreadExecutorProxy.getInstance().runOnAsyncThread(new v1vLvLE(obj, str, dLEWRequestCompleted));
    }

    public String CrateGetApiWrapper(Object obj, String str) {
        return str + StringFog.decrypt("Cg==\n", "NdOX1ey6vqk=\n") + new Gson().toJson(obj).replace(StringFog.decrypt("XA==\n", "ZvfV0ZvmeSg=\n"), SYMBOL_EQUAL).replace(StringFog.decrypt("FA==\n", "OKJpQ5sF0bc=\n"), SYMBOL_AND).replace(StringFog.decrypt("vQ==\n", "n+tesVnfQlg=\n"), BuildConfig.VERSION_NAME).replace(StringFog.decrypt("SA==\n", "M/GqKu9f//I=\n"), BuildConfig.VERSION_NAME).replace(StringFog.decrypt("0g==\n", "r+aDy1NQRrE=\n"), BuildConfig.VERSION_NAME);
    }
}