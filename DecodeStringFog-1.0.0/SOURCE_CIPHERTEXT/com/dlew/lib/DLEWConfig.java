package com.dlew.lib;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.elvishew.xlog.BuildConfig;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/lib/DLEWConfig.class */
public class DLEWConfig {
    private static DLEWConfig singleton;
    public static final String sdk_Version_Name = StringFog.decrypt("jvY5I1w=\n", "vNgBDWjQkvY=\n");
    public static final String sdk_Version_Code = StringFog.decrypt("7dA=\n", "3uZ3cztS8Go=\n");

    @SerializedName("debug")
    private boolean debug = false;

    @SerializedName("serverUrl")
    private String serverUrl = BuildConfig.VERSION_NAME;

    @SerializedName("statUrl")
    private String statUrl = null;

    @SerializedName("appKey")
    private String appKey = BuildConfig.VERSION_NAME;

    @SerializedName("appSecret")
    private String appSecret = BuildConfig.VERSION_NAME;

    @SerializedName("umkAppId")
    private String umkAppId = BuildConfig.VERSION_NAME;

    @SerializedName("afDevKey")
    private String afDevKey = BuildConfig.VERSION_NAME;

    @SerializedName("oneLinkID")
    private String oneLinkID = null;

    @SerializedName("insUnitId")
    private String insUnitId = BuildConfig.VERSION_NAME;

    @SerializedName("videoUnitId")
    private String videoUnitId = BuildConfig.VERSION_NAME;

    @SerializedName("openUnitId")
    private String openUnitId = BuildConfig.VERSION_NAME;

    @SerializedName("bannerUnitId")
    private String bannerUnitId = null;

    @SerializedName("nativeUnitId")
    private String nativeUnitId = null;

    @SerializedName("email")
    private String email = null;

    @SerializedName("printLog")
    private boolean printLog = false;

    @SerializedName("enableLocalValue")
    private boolean enableLocalValue = false;

    @SerializedName("forceLocalValueConfig")
    private boolean forceLocalValueConfig = false;

    @SerializedName("localValueConfig")
    private String localValueConfig = BuildConfig.VERSION_NAME;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<com.dlew.lib.DLEWConfig>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static DLEWConfig getInstance() {
        if (singleton == null) {
            ?? r0 = DLEWConfig.class;
            synchronized (r0) {
                if (singleton == null) {
                    singleton = new DLEWConfig();
                }
                r0 = r0;
            }
        }
        return singleton;
    }

    public static boolean isDebug() {
        return getInstance().debug;
    }

    public static boolean isPrintLog() {
        return getInstance().printLog;
    }

    public static boolean isEnableLocalValue() {
        return getInstance().enableLocalValue;
    }

    public static boolean isForceLocalValueConfig() {
        return getInstance().forceLocalValueConfig;
    }

    public static String getAppKey() {
        return getInstance().appKey;
    }

    public static String getAppSecret() {
        return getInstance().appSecret;
    }

    public static String getUmkAppId() {
        return getInstance().umkAppId;
    }

    public static String getAfDevKey() {
        return getInstance().afDevKey;
    }

    public static String getOneLinkID() {
        return getInstance().oneLinkID;
    }

    public static String getServerUrl() {
        return getInstance().serverUrl;
    }

    public static String getStatUrl() {
        return getInstance().statUrl;
    }

    public static String getVideoUnitId() {
        return getInstance().videoUnitId;
    }

    public static String getInsUnitId() {
        return getInstance().insUnitId;
    }

    public static String getNativeUnitId() {
        return getInstance().nativeUnitId;
    }

    public static String getBannerUnitId() {
        return getInstance().bannerUnitId;
    }

    public static String getLocalValueConfig() {
        String string = SPUtil.getString(StringFog.decrypt("9MFI17zYnRjNy2jZvuiVEw==\n", "uK4rttCO/HQ=\n"), null);
        return TextUtils.isEmpty(string) ? getInstance().localValueConfig : string;
    }

    public static String getEmail() {
        return getInstance().email;
    }

    public static String getOpenUnitId() {
        return getInstance().openUnitId;
    }

    public DLEWConfig setDebug(boolean z) {
        this.debug = z;
        return this;
    }

    public DLEWConfig setServerUrl(String str) {
        this.serverUrl = str;
        return this;
    }

    public DLEWConfig setStatUrl(String str) {
        this.statUrl = str;
        return this;
    }

    public DLEWConfig setAppKey(String str) {
        this.appKey = str;
        return this;
    }

    public DLEWConfig setAppSecret(String str) {
        this.appSecret = str;
        return this;
    }

    public DLEWConfig setUmkAppId(String str) {
        this.umkAppId = str;
        return this;
    }

    public DLEWConfig setAfDevKey(String str) {
        this.afDevKey = str;
        return this;
    }

    public DLEWConfig setOneLinkID(String str) {
        this.oneLinkID = str;
        return this;
    }

    public DLEWConfig setVideoUnitId(String str) {
        this.videoUnitId = str;
        return this;
    }

    public DLEWConfig setInsUnitId(String str) {
        this.insUnitId = str;
        return this;
    }

    public DLEWConfig setNativeUnitId(String str) {
        this.nativeUnitId = str;
        return this;
    }

    public DLEWConfig setBannerUnitId(String str) {
        this.bannerUnitId = str;
        return this;
    }

    public DLEWConfig setPrintLog(boolean z) {
        this.printLog = z;
        return this;
    }

    public DLEWConfig setEnableLocalValue(boolean z) {
        this.enableLocalValue = z;
        return this;
    }

    public DLEWConfig setForceLocalValueConfig(boolean z) {
        this.forceLocalValueConfig = z;
        return this;
    }

    public DLEWConfig setLocalValueConfig(String str) {
        this.localValueConfig = str;
        return this;
    }

    public DLEWConfig setAppOpenUnitId(String str) {
        this.openUnitId = str;
        return this;
    }
}