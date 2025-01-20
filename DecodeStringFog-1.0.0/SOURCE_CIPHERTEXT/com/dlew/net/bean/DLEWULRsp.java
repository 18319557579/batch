package com.dlew.net.bean;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/bean/DLEWULRsp.class */
public class DLEWULRsp extends DLEWBaseRsp {

    @SerializedName("an")
    public String tk;

    @SerializedName("ku")
    public String uid;

    @SerializedName("ss")
    public int st;

    @SerializedName("ya")
    public String inviteC;

    @SerializedName("zn")
    public String zcsj;

    @SerializedName("wn")
    public String now;

    @SerializedName("cy")
    public String local;

    @SerializedName("su")
    public String tz;

    @SerializedName("es")
    public Map<String, Object> more;
}