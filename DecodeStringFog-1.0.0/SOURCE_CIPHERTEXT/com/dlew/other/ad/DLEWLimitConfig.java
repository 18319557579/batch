package com.dlew.other.ad;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWLimitConfig.class */
public class DLEWLimitConfig implements Serializable {

    @SerializedName("appkey")
    public String appkey;

    @SerializedName("token")
    public String token;

    @SerializedName("videoID")
    public String videoID;

    @SerializedName("insID")
    public String insID;

    @SerializedName("banID")
    public String banID;

    @SerializedName("openID")
    public String openID;

    @SerializedName("nativeID")
    public String nativeID;

    @SerializedName("adSceneStr")
    public String adSceneStr;

    @SerializedName("invalidChannelStr")
    public String invalidChannelStr;

    @SerializedName("networkNames")
    public String[] networkNames;

    @SerializedName("networkNameStr")
    public String networkNameStr;

    @SerializedName("illegalClickRate_MinCount")
    public int illegalClickRate_MinCount;

    @SerializedName("illegalClickRate_MaxRate")
    public int illegalClickRate_MaxRate;

    @SerializedName("illegalClickRate")
    public String illegalClickRate;

    @SerializedName("illegalMultiClick_MinShowCount")
    public int illegalMultiClick_MinShowCount;

    @SerializedName("illegalMultiClick_MaxClickCount")
    public int illegalMultiClick_MaxClickCount;

    @SerializedName("illegalMultiClick")
    public String illegalMultiClick;

    @SerializedName("maxDisplay")
    public int maxDisplay;

    @SerializedName("showLoading")
    public int showLoading;

    @SerializedName("isDefault")
    public boolean isDefault;

    @SerializedName("extra")
    public String extra;

    @SerializedName("priority")
    public int priority;

    @SerializedName("firstShow")
    public int firstShow;

    @SerializedName("intervalShow")
    public int intervalShow;

    @SerializedName("defaultPrice")
    public double defaultPrice;

    public void init() {
        if (!TextUtils.isEmpty(this.networkNameStr)) {
            this.networkNames = this.networkNameStr.split(StringFog.decrypt("Ug==\n", "cctwjsnXFXg=\n"));
        }
        if (!TextUtils.isEmpty(this.illegalClickRate)) {
            String[] split = this.illegalClickRate.split(StringFog.decrypt("pQ==\n", "hk5atVx/sQs=\n"));
            this.illegalClickRate_MinCount = Double.valueOf(split[0]).intValue();
            this.illegalClickRate_MaxRate = Double.valueOf(split[1]).intValue();
        }
        if (TextUtils.isEmpty(this.illegalClickRate)) {
            return;
        }
        String[] split2 = this.illegalMultiClick.split(StringFog.decrypt("NQ==\n", "Fj4M7yY1v3c=\n"));
        this.illegalMultiClick_MinShowCount = Double.valueOf(split2[0]).intValue();
        this.illegalMultiClick_MaxClickCount = Double.valueOf(split2[1]).intValue();
    }
}