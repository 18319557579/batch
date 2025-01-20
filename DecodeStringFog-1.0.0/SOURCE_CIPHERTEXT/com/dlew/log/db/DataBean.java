package com.dlew.log.db;

import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/db/DataBean.class */
public class DataBean {

    @SerializedName("requestCode")
    private String requestCode;

    @SerializedName("data")
    private String data;

    @SerializedName("firstCacheTime")
    private long firstCacheTime;

    @SerializedName("retryNum")
    private int retryNum;

    public DataBean(String str, String str2, long j, int i) {
        this.requestCode = str;
        this.data = str2;
        this.firstCacheTime = j;
        this.retryNum = i;
    }

    public int getRetryNum() {
        return this.retryNum;
    }

    public long getFirstCacheTime() {
        return this.firstCacheTime;
    }

    public String getRequestCode() {
        return this.requestCode;
    }

    public String getData() {
        return this.data;
    }
}