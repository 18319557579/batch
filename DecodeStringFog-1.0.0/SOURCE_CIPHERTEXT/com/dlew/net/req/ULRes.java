package com.dlew.net.req;

import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.net.bean.DLEWRootReq;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/req/ULRes.class */
public class ULRes extends DLEWRootReq {

    @SerializedName("od")
    public String o;

    @SerializedName("ai")
    public String a;

    @SerializedName("im")
    public String ii;

    @SerializedName("qo")
    public String from;

    @SerializedName("gi")
    public String adid = PhoneInfoWrapper.getGAID();

    @SerializedName("bd")
    public String b = PhoneInfoWrapper.getBid();

    @SerializedName("cy")
    public String local = PhoneInfoWrapper.country();

    @SerializedName("su")
    public String tz = PhoneInfoWrapper.getCurrentTimeZone();
}