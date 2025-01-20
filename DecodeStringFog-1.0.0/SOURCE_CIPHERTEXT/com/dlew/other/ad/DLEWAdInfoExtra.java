package com.dlew.other.ad;

import com.applovin.mediation.MaxAd;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/ad/DLEWAdInfoExtra.class */
public class DLEWAdInfoExtra {

    @SerializedName("requestCode")
    public String requestCode;

    @SerializedName("AdUnitIdentifier")
    public String AdUnitIdentifier;

    @SerializedName("AdFormat")
    public String AdFormat;

    @SerializedName("NetworkName")
    public String NetworkName;

    @SerializedName("NetworkPlacement")
    public String NetworkPlacement;

    @SerializedName("Placement")
    public String Placement;

    @SerializedName("CreativeIdentifier")
    public String CreativeIdentifier;

    @SerializedName("Revenue")
    public double Revenue;

    @SerializedName("RevenuePrecision")
    public String RevenuePrecision;

    @SerializedName("DspName")
    public String DspName;

    public DLEWAdInfoExtra() {
    }

    public String getJson() {
        return new Gson().toJson(this);
    }

    public DLEWAdInfoExtra(MaxAd maxAd, String str) {
        this.requestCode = str;
        this.AdUnitIdentifier = maxAd.getAdUnitId();
        this.AdFormat = maxAd.getFormat().getLabel();
        this.NetworkName = maxAd.getNetworkName();
        this.NetworkPlacement = maxAd.getNetworkPlacement();
        this.Placement = maxAd.getPlacement();
        this.CreativeIdentifier = maxAd.getAdReviewCreativeId();
        this.Revenue = maxAd.getRevenue();
        this.RevenuePrecision = maxAd.getRevenuePrecision();
        this.DspName = maxAd.getDspName();
    }
}