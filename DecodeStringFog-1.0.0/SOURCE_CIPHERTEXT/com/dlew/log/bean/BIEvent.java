package com.dlew.log.bean;

import android.content.pm.PackageInfo;
import com.dlew.StringFog;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWConfig;
import com.dlew.lib.DLEWSDK;
import com.elvishew.xlog.LogLevel;
import java.util.UUID;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/bean/BIEvent.class */
public class BIEvent extends BIBase {

    @BeanFieldAnnotation(order = 1)
    public String log_time;

    @BeanFieldAnnotation(order = 2)
    public String create_date;

    @BeanFieldAnnotation(order = LogLevel.DEBUG)
    public String sdk_version_code;

    @BeanFieldAnnotation(order = 4)
    public String sdk_version_name;

    @BeanFieldAnnotation(order = LogLevel.WARN)
    public String product_version_code;

    @BeanFieldAnnotation(order = LogLevel.ERROR)
    public String product_version_name;

    @BeanFieldAnnotation(order = 7)
    public int service_version;

    @BeanFieldAnnotation(order = 8)
    public int data_version;

    @BeanFieldAnnotation(order = 9)
    public String imei_id;

    @BeanFieldAnnotation(order = 10)
    public String android_id;

    @BeanFieldAnnotation(order = 11)
    public String google_id;

    @BeanFieldAnnotation(order = 12)
    public String bat_id;

    @BeanFieldAnnotation(order = 13)
    public String appkey;

    @BeanFieldAnnotation(order = 14)
    public String conf_version_id;

    @BeanFieldAnnotation(order = 15)
    public String event_name;

    @BeanFieldAnnotation(order = 16)
    public String event_info;

    @BeanFieldAnnotation(order = 17)
    public String event_value;

    @BeanFieldAnnotation(order = 18)
    public int event_status;

    @BeanFieldAnnotation(order = 19)
    public String error_msg;

    @BeanFieldAnnotation(order = 20)
    public int event_id;

    @BeanFieldAnnotation(order = 21)
    public String request_id;

    @BeanFieldAnnotation(order = 22)
    public String od_id;

    @BeanFieldAnnotation(order = 23)
    public String user_id;

    @BeanFieldAnnotation(order = 24)
    public String idfa;

    @BeanFieldAnnotation(order = 25)
    public String idfv;

    @BeanFieldAnnotation(order = 26)
    public int ivpn;

    @BeanFieldAnnotation(order = 27)
    public int ivm;

    @BeanFieldAnnotation(order = 28)
    public int iagent;

    @BeanFieldAnnotation(order = 29)
    public int iroot;

    public BIEvent(String str, String str2, int i, int i2, String str3, String str4) {
        String beiJinTime = PhoneInfoWrapper.getBeiJinTime(System.currentTimeMillis());
        this.create_date = beiJinTime;
        this.log_time = beiJinTime;
        this.request_id = UUID.randomUUID().toString();
        this.sdk_version_code = StringFog.decrypt("w5I=\n", "8KQfeb6LhD0=\n");
        this.sdk_version_name = StringFog.decrypt("MsSw0to=\n", "AOqI/O4MsGY=\n");
        try {
            PackageInfo packageInfo = DLEWSDK.getApplication().getPackageManager().getPackageInfo(DLEWSDK.getApplication().getPackageName(), 0);
            this.product_version_code = String.valueOf(packageInfo.versionCode);
            this.product_version_name = packageInfo.versionName;
        } catch (Exception unused) {
        }
        this.service_version = 13;
        this.data_version = 13;
        this.imei_id = StringFog.decrypt("oSKFGw==\n", "z1fpd/sM3eg=\n");
        this.android_id = StringFog.decrypt("ng1LVw==\n", "8HgnO22p9Fs=\n");
        this.google_id = PhoneInfoWrapper.getGAID();
        this.bat_id = PhoneInfoWrapper.getBid();
        this.idfa = StringFog.decrypt("YJXdwA==\n", "DuCxrF9hefU=\n");
        this.idfv = StringFog.decrypt("Cqqifw==\n", "ZN/OEy3az1c=\n");
        this.appkey = DLEWConfig.getAppKey();
        this.conf_version_id = StringFog.decrypt("IsR2gg==\n", "TLEa7kSHCfw=\n");
        this.event_name = str;
        this.event_status = i2;
        this.user_id = StringFog.decrypt("R8Gz8A==\n", "KbTfnJ9F/8Q=\n");
        this.event_value = str2;
        this.error_msg = str3;
        this.event_info = str4;
        this.event_id = i;
        this.od_id = StringFog.decrypt("dM/FFg==\n", "GrqpekD2KLU=\n");
        this.ivpn = PhoneInfoWrapper.checkvpn() ? 1 : 0;
        this.ivm = PhoneInfoWrapper.IsEmulator() ? 1 : 0;
        this.iagent = PhoneInfoWrapper.checkagency() ? 1 : 0;
        this.iroot = PhoneInfoWrapper.checkroot() ? 1 : 0;
    }

    public String toString() {
        return StringFog.decrypt("wg==\n", "ufKUiWXJzok=\n") + this.log_time + '\'' + this.create_date + '\'' + this.sdk_version_code + '\'' + this.sdk_version_name + '\'' + this.product_version_code + '\'' + this.product_version_name + '\'' + this.service_version + this.data_version + this.imei_id + '\'' + this.android_id + '\'' + this.google_id + '\'' + this.bat_id + '\'' + this.appkey + '\'' + this.conf_version_id + '\'' + this.event_name + '\'' + this.event_info + '\'' + this.event_value + '\'' + this.event_status + this.error_msg + '\'' + this.event_id + this.request_id + '\'' + this.od_id + '\'' + this.user_id + '\'' + this.idfa + '\'' + this.idfv + '\'' + this.ivpn + this.ivm + this.iagent + this.iroot + '}';
    }
}