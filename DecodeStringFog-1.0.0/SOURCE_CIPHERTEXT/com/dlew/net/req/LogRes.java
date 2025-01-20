package com.dlew.net.req;

import com.dlew.StringFog;
import com.dlew.net.bean.DLEWRootReq;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/req/LogRes.class */
public class LogRes extends DLEWRootReq {

    @SerializedName("bd")
    public String ku = StringFog.decrypt("hBBdZsldMzeEGg==\n", "63QuOaoyXlo=\n");

    @SerializedName("bt")
    public String biao;

    @SerializedName("vn")
    public int banben;

    @SerializedName("ls")
    public String event;
}