package com.dlew.log.bean;

import android.content.pm.PackageInfo;
import android.os.Build;
import com.dlew.StringFog;
import com.dlew.hardware.PhoneInfoWrapper;
import com.dlew.lib.DLEWConfig;
import com.dlew.lib.DLEWSDK;
import com.elvishew.xlog.LogLevel;
import java.util.UUID;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/log/bean/BIUser.class */
public class BIUser extends BIBase {

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
    public int service_sersion;

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
    public String usr_id;

    @BeanFieldAnnotation(order = 15)
    public String channel_code;

    @BeanFieldAnnotation(order = 16)
    public String country_code_client;

    @BeanFieldAnnotation(order = 17)
    public String province;

    @BeanFieldAnnotation(order = 18)
    public String city;

    @BeanFieldAnnotation(order = 19)
    public String longitude;

    @BeanFieldAnnotation(order = 20)
    public String latitude;

    @BeanFieldAnnotation(order = 21)
    public String languange;

    @BeanFieldAnnotation(order = 22)
    public String brand;

    @BeanFieldAnnotation(order = 23)
    public String model;

    @BeanFieldAnnotation(order = 24)
    public int model_type;

    @BeanFieldAnnotation(order = 25)
    public String network_type;

    @BeanFieldAnnotation(order = 26)
    public String resolution;

    @BeanFieldAnnotation(order = 27)
    public String operators;

    @BeanFieldAnnotation(order = 28)
    public int platform;

    @BeanFieldAnnotation(order = 29)
    public String os_version_code;

    @BeanFieldAnnotation(order = 30)
    public String os_version_name;

    @BeanFieldAnnotation(order = 31)
    public String biz_sdk_version;

    @BeanFieldAnnotation(order = 32)
    public String fb_install;

    @BeanFieldAnnotation(order = 33)
    public String oa_id;

    @BeanFieldAnnotation(order = 34)
    public String request_id;

    @BeanFieldAnnotation(order = 35)
    public String user_id;

    @BeanFieldAnnotation(order = 36)
    public String installer;

    @BeanFieldAnnotation(order = 37)
    public String mac;

    @BeanFieldAnnotation(order = 38)
    public String idfa;

    @BeanFieldAnnotation(order = 39)
    public String idfv;

    @BeanFieldAnnotation(order = 40)
    public String agency;

    @BeanFieldAnnotation(order = 41)
    public String extend_data;

    @BeanFieldAnnotation(order = 42)
    public int ivpn;

    @BeanFieldAnnotation(order = 43)
    public int ivm;

    @BeanFieldAnnotation(order = 44)
    public int iagent;

    @BeanFieldAnnotation(order = 45)
    public int iroot;

    public BIUser() {
        String beiJinTime = PhoneInfoWrapper.getBeiJinTime(System.currentTimeMillis());
        this.create_date = beiJinTime;
        this.log_time = beiJinTime;
        this.request_id = UUID.randomUUID().toString();
        this.channel_code = PhoneInfoWrapper.getConversionData().channel;
        this.agency = PhoneInfoWrapper.getConversionData().agency;
        this.extend_data = PhoneInfoWrapper.getConversionData().data;
        this.sdk_version_code = StringFog.decrypt("eys=\n", "SB2xVJVxfNI=\n");
        this.sdk_version_name = StringFog.decrypt("fTnRVWU=\n", "Txfpe1FJmbw=\n");
        try {
            PackageInfo packageInfo = DLEWSDK.getApplication().getPackageManager().getPackageInfo(DLEWSDK.getApplication().getPackageName(), 0);
            this.product_version_code = String.valueOf(packageInfo.versionCode);
            this.product_version_name = packageInfo.versionName;
        } catch (Exception unused) {
        }
        this.service_sersion = 15;
        this.data_version = 15;
        this.imei_id = StringFog.decrypt("avIPqQ==\n", "BIdjxeMc80Y=\n");
        this.android_id = StringFog.decrypt("pFt3qA==\n", "yi4bxMkU59s=\n");
        this.google_id = PhoneInfoWrapper.getGAID();
        this.bat_id = PhoneInfoWrapper.getBid();
        this.idfa = StringFog.decrypt("dJXNyw==\n", "GuChp/xeqJE=\n");
        this.idfv = StringFog.decrypt("f/bGBg==\n", "EYOqagwCKfk=\n");
        this.appkey = DLEWConfig.getAppKey();
        this.oa_id = StringFog.decrypt("C6kPGA==\n", "ZdxjdG01UG0=\n");
        this.user_id = StringFog.decrypt("Wxxvug==\n", "NWkD1vukOzo=\n");
        this.usr_id = StringFog.decrypt("gOVbAA==\n", "7pA3bHip+bk=\n");
        this.country_code_client = PhoneInfoWrapper.country();
        this.province = StringFog.decrypt("Tm4lUA==\n", "IBtJPJkvu50=\n");
        this.city = StringFog.decrypt("sUWSyw==\n", "3zD+p3GaXrs=\n");
        this.longitude = StringFog.decrypt("I5b/4Q==\n", "TeOTjcd+hY4=\n");
        this.latitude = StringFog.decrypt("mvbcrg==\n", "9IOwwrgNPkU=\n");
        this.languange = PhoneInfoWrapper.language();
        this.brand = Build.BRAND;
        this.model = Build.MODEL;
        this.model_type = 1;
        this.network_type = PhoneInfoWrapper.getNetworkType();
        this.resolution = PhoneInfoWrapper.getPhoneScreen();
        this.operators = PhoneInfoWrapper.getOperator();
        this.platform = 1;
        this.os_version_code = PhoneInfoWrapper.getOsVersionCode();
        this.os_version_name = PhoneInfoWrapper.getOsVersionName();
        this.biz_sdk_version = StringFog.decrypt("zVgnng==\n", "oy1L8hXnv3g=\n");
        this.fb_install = StringFog.decrypt("9U3whg==\n", "mzic6mgTZPo=\n");
        this.installer = StringFog.decrypt("190ilw==\n", "uahO+/y9YvI=\n");
        this.mac = PhoneInfoWrapper.getMac();
        this.ivpn = PhoneInfoWrapper.checkvpn() ? 1 : 0;
        this.ivm = PhoneInfoWrapper.IsEmulator() ? 1 : 0;
        this.iagent = PhoneInfoWrapper.checkagency() ? 1 : 0;
        this.iroot = PhoneInfoWrapper.checkroot() ? 1 : 0;
    }

    public String toString() {
        return StringFog.decrypt("ew==\n", "ADc2q+HUIFM=\n") + this.log_time + '\'' + this.create_date + '\'' + this.sdk_version_code + '\'' + this.sdk_version_name + '\'' + this.product_version_code + '\'' + this.product_version_name + '\'' + this.service_sersion + this.data_version + this.imei_id + '\'' + this.android_id + '\'' + this.google_id + '\'' + this.bat_id + '\'' + this.appkey + '\'' + this.usr_id + '\'' + this.channel_code + '\'' + this.country_code_client + '\'' + this.province + '\'' + this.city + '\'' + this.longitude + '\'' + this.latitude + '\'' + this.languange + '\'' + this.brand + '\'' + this.model + '\'' + this.model_type + this.network_type + '\'' + this.resolution + '\'' + this.operators + '\'' + this.platform + this.os_version_code + '\'' + this.os_version_name + '\'' + this.biz_sdk_version + '\'' + this.fb_install + '\'' + this.oa_id + '\'' + this.request_id + '\'' + this.user_id + '\'' + this.installer + '\'' + this.mac + '\'' + this.idfa + '\'' + this.idfv + '\'' + this.agency + '\'' + this.extend_data + '\'' + this.ivpn + this.ivm + this.iagent + this.iroot + '}';
    }
}