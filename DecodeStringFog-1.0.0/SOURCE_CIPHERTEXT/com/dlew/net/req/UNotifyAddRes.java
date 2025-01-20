package com.dlew.net.req;

import com.dlew.net.bean.DLEWRootReq;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/req/UNotifyAddRes.class */
public class UNotifyAddRes extends DLEWRootReq {

    @SerializedName("ad")
    public String a;

    @SerializedName("tn")
    public String tk;

    @SerializedName("dy")
    public float wandian;

    @SerializedName("by")
    public String content;

    @SerializedName("da")
    public String more;

    @SerializedName("te")
    public String bt;

    @SerializedName("di")
    public String id;
}