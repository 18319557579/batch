package com.dlew.net.req;

import android.content.pm.PackageInfo;
import android.os.Build;
import com.dlew.StringFog;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWSDK;
import com.dlew.net.bean.DLEWRootReq;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/req/ABRes.class */
public class ABRes extends DLEWRootReq {

    @SerializedName("bs")
    public String bCodes;

    @SerializedName("ie")
    public String ii;

    @SerializedName("pne")
    public String vname;

    @SerializedName("pce")
    public String vcode;

    @SerializedName("cy")
    public String local;

    @SerializedName("bd")
    public String paizi;

    @SerializedName("ml")
    public String xinghao;

    @SerializedName("lg")
    public String lan;

    @SerializedName("es")
    public Map<String, Object> more;

    @SerializedName("gd")
    public String gid = PhoneInfoWrapper.getGAID();

    @SerializedName("od")
    public String oid = null;

    @SerializedName("ad")
    public String aid = null;

    @SerializedName("pg")
    public String productName = DLEWSDK.getApplication().getPackageName();

    @SerializedName("se")
    public String sdkvcode = StringFog.decrypt("uoQ=\n", "ibK8EOutl5U=\n");

    public ABRes() {
        try {
            PackageInfo packageInfo = DLEWSDK.getApplication().getPackageManager().getPackageInfo(this.productName, 0);
            this.vcode = String.valueOf(packageInfo.versionCode);
            this.vname = packageInfo.versionName;
        } catch (Exception unused) {
        }
        this.local = PhoneInfoWrapper.country();
        this.paizi = Build.BRAND;
        this.xinghao = Build.MODEL;
        this.ii = null;
        this.lan = PhoneInfoWrapper.language();
    }
}