package com.dlew.other.ad.typeBid;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/typeBid/DLEWClientBidInfo.class */
public class DLEWClientBidInfo {

    @SerializedName("bidding")
    public List<DLEWClientBidUnitInfo> bidding;

    @SerializedName("ad_id")
    public String ad_id;

    @SerializedName("ad_type")
    public String ad_type;

    @SerializedName("code")
    public String code;
}