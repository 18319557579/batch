package com.dlew.net.bean;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/bean/DLEWABData.class */
public class DLEWABData {

    @SerializedName("vid")
    public String vid;

    @SerializedName("biz")
    public String biz;

    @SerializedName("fields")
    public List<DLEWABField> fields;
}