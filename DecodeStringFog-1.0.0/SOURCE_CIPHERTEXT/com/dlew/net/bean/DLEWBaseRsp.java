package com.dlew.net.bean;

import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/bean/DLEWBaseRsp.class */
public class DLEWBaseRsp<T> {

    @SerializedName("code")
    public int code;

    @SerializedName("msg")
    public String msg;

    @SerializedName("msgDetail")
    public String msgDetail;

    @SerializedName("data")
    public T data;
}