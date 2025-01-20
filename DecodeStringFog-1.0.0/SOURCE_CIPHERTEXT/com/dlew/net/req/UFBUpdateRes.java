package com.dlew.net.req;

import com.dlew.net.bean.DLEWRootReq;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/req/UFBUpdateRes.class */
public class UFBUpdateRes extends DLEWRootReq {

    @SerializedName("gn")
    public String tk;

    @SerializedName("ad")
    public String appid;
}