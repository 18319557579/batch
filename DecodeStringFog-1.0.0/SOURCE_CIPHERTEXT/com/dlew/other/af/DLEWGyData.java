package com.dlew.other.af;

import android.text.TextUtils;
import com.dlew.StringFog;
import com.google.gson.annotations.SerializedName;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/other/af/DLEWGyData.class */
public class DLEWGyData {

    @SerializedName("success")
    public boolean success;

    @SerializedName("errorMsg")
    public String errorMsg;

    @SerializedName("data")
    public String data;

    @SerializedName("channel")
    public String channel = StringFog.decrypt("B64Dh4XLLw==\n", "csBo6eq8QVA=\n");

    @SerializedName("agency")
    public String agency;

    @SerializedName("inviteCode")
    public String inviteCode;

    public boolean IsUnknow() {
        if (TextUtils.isEmpty(this.channel)) {
            this.channel = StringFog.decrypt("xoqNjy3Oww==\n", "s+Tm4UK5rZ0=\n");
        }
        return this.channel.equals(StringFog.decrypt("iAGqkWDxWA==\n", "/W/B/w+GNuc=\n"));
    }
}