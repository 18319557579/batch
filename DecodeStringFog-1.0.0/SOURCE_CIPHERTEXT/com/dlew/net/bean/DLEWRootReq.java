package com.dlew.net.bean;

import com.google.gson.annotations.SerializedName;
import java.util.Random;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/bean/DLEWRootReq.class */
public class DLEWRootReq {

    @SerializedName("cc")
    public String cc = String.valueOf(new Random().nextInt(100000000) + 0);

    @SerializedName("new")
    public String aa;

    @SerializedName("method")
    public String bb;
}