package com.dlew.other.ad.typeBid;

import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/typeBid/DLEWClientBidUnitInfo.class */
public class DLEWClientBidUnitInfo {

    @SerializedName("source")
    public String source;

    @SerializedName("cpm")
    public double cpm;

    @SerializedName("type")
    public String type;

    public DLEWClientBidUnitInfo(String str, double d, String str2) {
        this.source = str;
        this.cpm = d;
        this.type = str2;
    }
}