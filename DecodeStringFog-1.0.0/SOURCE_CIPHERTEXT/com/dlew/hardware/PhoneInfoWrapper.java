package com.dlew.hardware;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.Proxy;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import com.dlew.StringFog;
import com.dlew.common.SPUtil;
import com.dlew.net.util.DLEWNetCallback;
import com.dlew.other.af.DLEWGyData;
import com.elvishew.xlog.BuildConfig;
import com.elvishew.xlog.LogLevel;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.NetworkInterface;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/PhoneInfoWrapper.class */
public class PhoneInfoWrapper {
    private static Context context;
    private static DLEWGyData afData;
    private static final String GAID_KEY = StringFog.decrypt("N+s9vLXsQOY=\n", "cKp0+OqnBb8=\n");
    private static String gid = BuildConfig.VERSION_NAME;
    private static Toast toast = null;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/PhoneInfoWrapper$EvLveE1.class */
    public class EvLveE1 extends Thread {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final /* synthetic */ DLEWNetCallback f3EvLveE1;

        public EvLveE1(DLEWNetCallback dLEWNetCallback) {
            this.f3EvLveE1 = dLEWNetCallback;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                try {
                    String unused = PhoneInfoWrapper.gid = GAIDHelper.getGoogleId(PhoneInfoWrapper.context);
                    SPUtil.putString(StringFog.decrypt("7fgLztUmxqw=\n", "qrlCioptg/U=\n"), PhoneInfoWrapper.gid);
                    this.f3EvLveE1.OnCompleted(true, PhoneInfoWrapper.gid);
                } catch (Exception unused2) {
                    printStackTrace();
                    SPUtil.putString(StringFog.decrypt("x67E+duX9Ng=\n", "gO+NvYTcsYE=\n"), PhoneInfoWrapper.gid);
                    this.f3EvLveE1.OnCompleted(true, PhoneInfoWrapper.gid);
                }
            } catch (Throwable unused3) {
                SPUtil.putString(StringFog.decrypt("RXIYIf3O9kI=\n", "AjNRZaKFsxs=\n"), PhoneInfoWrapper.gid);
                this.f3EvLveE1.OnCompleted(true, PhoneInfoWrapper.gid);
                throw this;
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/PhoneInfoWrapper$evleeEelE.class */
    public class evleeEelE extends TimerTask {
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (PhoneInfoWrapper.toast != null) {
                PhoneInfoWrapper.toast.cancel();
            }
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/PhoneInfoWrapper$v1vLvLE.class */
    public class v1vLvLE extends TimerTask {
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (PhoneInfoWrapper.toast != null) {
                PhoneInfoWrapper.toast.cancel();
            }
        }
    }

    public static void putContext(Context context2) {
        context = context2;
    }

    public static Context getContext() {
        return context;
    }

    public static void Init(DLEWNetCallback<String> dLEWNetCallback) {
        new EvLveE1(dLEWNetCallback).start();
    }

    public static String getGAID() {
        if (TextUtils.isEmpty(gid)) {
            gid = SPUtil.getString(GAID_KEY, null);
        }
        return gid;
    }

    public static DLEWGyData getConversionData() {
        if (afData == null) {
            DLEWGyData dLEWGyData = (DLEWGyData) SPUtil.getObject(StringFog.decrypt("SiC3YHTSgtNCKaNkdcSU30M=\n", "DWf1ISeX3ZA=\n"), DLEWGyData.class);
            afData = dLEWGyData;
            if (dLEWGyData == null) {
                afData = new DLEWGyData();
            }
        }
        return afData;
    }

    public static void putConversionData(DLEWGyData dLEWGyData) {
        afData = dLEWGyData;
        SPUtil.putString(StringFog.decrypt("KTqQ32syYfwpPZXU\n", "SlLxsQVXDaM=\n"), afData.channel);
        SPUtil.putObject(StringFog.decrypt("4MjzESYFQoXowecVJxNUiek=\n", "p4+xUHVAHcY=\n"), afData);
    }

    public static String getBid() {
        String string = SPUtil.getString(StringFog.decrypt("uukZ\n", "2IB973MLnio=\n"), null);
        String str = string;
        if (TextUtils.isEmpty(string)) {
            str = UUID.randomUUID().toString();
            SPUtil.putString(StringFog.decrypt("njfZ\n", "/F69ryMAarE=\n"), str);
        }
        return str;
    }

    public static String getBeiJinTime(long j) {
        String str = BuildConfig.VERSION_NAME;
        try {
            Date date = new Date(j);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(StringFog.decrypt("Jt4OO5GDw1Q7w1cK9PTjFGXUBA==\n", "X6d3QrzOjnk=\n"), Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(StringFog.decrypt("B75l568=\n", "QPMxzJfRwVg=\n")));
            str = simpleDateFormat.format(date);
        } catch (Exception unused) {
        }
        return str;
    }

    public static String android_id() {
        return Settings.System.getString(context.getContentResolver(), StringFog.decrypt("XuV/IKl1v4pW7w==\n", "P4sbUsYc29U=\n"));
    }

    public static String country() {
        return Locale.getDefault().getCountry();
    }

    public static String language() {
        return Locale.getDefault().getLanguage();
    }

    public static String getCurrentTimeZone() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static boolean isIpad() {
        return (context.getResources().getConfiguration().screenLayout & 15) > 3;
    }

    public static String getNetworkType() {
        if (((ConnectivityManager) context.getSystemService(StringFog.decrypt("q4Q+DZue2B6+giQa\n", "yOtQY/79rHc=\n"))) == null) {
            return StringFog.decrypt("9sl9oA==\n", "mKYTxcOMgDQ=\n");
        }
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(StringFog.decrypt("9KrbJQ==\n", "g8O9TGVwY1Q=\n"));
        if (wifiManager != null && wifiManager.isWifiEnabled()) {
            return StringFog.decrypt("LuwlvA==\n", "WYVD1dVEQJo=\n");
        }
        if (ContextCompat.checkSelfPermission(context, StringFog.decrypt("cOxXnmFlnPlh50GBZ3+Lvn7sHa1NT72EQt1wo09eq5JOznyvT1ixmF8=\n", "EYIz7A4M+Nc=\n")) != 0) {
            return StringFog.decrypt("qE+fcQ==\n", "xiDxFKsvn5s=\n");
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(StringFog.decrypt("oKv7wow=\n", "0MOUrOkqh24=\n"));
        if (telephonyManager == null) {
            return StringFog.decrypt("DsjbLA==\n", "YKe1SRB6gvc=\n");
        }
        try {
            int networkType = telephonyManager.getNetworkType();
            if (networkType == 20) {
                return StringFog.decrypt("RAU=\n", "cUKtOynKGu8=\n");
            }
            switch (networkType) {
                case 0:
                    return StringFog.decrypt("Iizk0w==\n", "TEOKthph3q4=\n");
                case 1:
                case 2:
                case 4:
                case 7:
                case 11:
                    return StringFog.decrypt("n7k=\n", "rf7hPGRCMfI=\n");
                case LogLevel.DEBUG /* 3 */:
                case LogLevel.WARN /* 5 */:
                case LogLevel.ERROR /* 6 */:
                case 8:
                case 9:
                case 10:
                case 12:
                case 14:
                case 15:
                    return StringFog.decrypt("6y4=\n", "2Gn3lpXJZTo=\n");
                case 13:
                    return StringFog.decrypt("Nng=\n", "Aj8PRdoXxnc=\n");
                default:
                    return StringFog.decrypt("rb0=\n", "nvpfoGo2LcU=\n");
            }
        } catch (Exception unused) {
            return StringFog.decrypt("j1JkVw==\n", "4T0KMusfzVU=\n");
        }
    }

    public static String getPhoneScreen() {
        WindowManager windowManager = (WindowManager) context.getSystemService(StringFog.decrypt("bP7KlKtP\n", "G5ek8MQ4Q3Q=\n"));
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
        String decrypt = StringFog.decrypt("OlHartg/kpx7HA==\n", "HzWKhv1b6rk=\n");
        Object[] objArr = new Object[3];
        int i = point.x;
        int i2 = i;
        int i3 = point.y;
        if (i > i3) {
            i2 = i3;
        }
        objArr[0] = Integer.valueOf(i2);
        objArr[1] = Integer.valueOf(point.x);
        objArr[2] = Integer.valueOf(point.y);
        return String.format(decrypt, objArr);
    }

    public static String getOperator() {
        return ((TelephonyManager) context.getSystemService(StringFog.decrypt("mVUpua0=\n", "6T1G18hya3U=\n"))).getSimOperatorName();
    }

    public static String getOsVersionCode() {
        return Build.VERSION.SDK_INT + BuildConfig.VERSION_NAME;
    }

    public static String getOsVersionName() {
        return Build.VERSION.RELEASE;
    }

    public static boolean getAppInstall(String str) {
        List<PackageInfo> installedPackages;
        if (str == null || str.isEmpty() || (installedPackages = context.getPackageManager().getInstalledPackages(0)) == null || installedPackages.isEmpty()) {
            return false;
        }
        for (int i = 0; i < installedPackages.size(); i++) {
            if (str.equals(installedPackages.get(i).packageName)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    public static String getMac() {
        String str = BuildConfig.VERSION_NAME;
        ?? r0 = Build.VERSION.SDK_INT;
        if (r0 < 24) {
            try {
                LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec(StringFog.decrypt("/gWuSE95Xb/+CLsUTy9A9elLrQtdbh6//AC+FVlzXQ==\n", "nWTaZzwALpA=\n")).getInputStream()));
                String str2 = BuildConfig.VERSION_NAME;
                while (str2 != null) {
                    String readLine = lineNumberReader.readLine();
                    str2 = readLine;
                    if (readLine != null) {
                        r0 = str2.trim();
                        return r0;
                    }
                }
            } catch (Exception unused) {
                r0.printStackTrace();
            }
        } else {
            try {
                Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
                while (true) {
                    r0 = it.hasNext();
                    if (r0 == 0) {
                        break;
                    }
                    NetworkInterface networkInterface = (NetworkInterface) it.next();
                    if (networkInterface.getName().equals(StringFog.decrypt("tseXfgM=\n", "wav2EDNerLo=\n"))) {
                        byte[] hardwareAddress = networkInterface.getHardwareAddress();
                        if (hardwareAddress == null) {
                            return null;
                        }
                        StringBuilder sb = new StringBuilder();
                        for (byte b : hardwareAddress) {
                            String decrypt = StringFog.decrypt("vRZXvWo=\n", "mCZlxVAGGEE=\n");
                            Object[] objArr = new Object[1];
                            objArr[0] = Byte.valueOf(b);
                            sb.append(String.format(decrypt, objArr));
                        }
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        str = sb.toString();
                    }
                }
            } catch (Exception unused2) {
                r0.printStackTrace();
            }
        }
        return str;
    }

    /* JADX WARN: Type inference failed for: r0v8, types: [boolean, java.lang.Throwable] */
    public static boolean checkvpn() {
        ?? hasNext;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces == null) {
                return false;
            }
            Iterator it = Collections.list(networkInterfaces).iterator();
            while (true) {
                hasNext = it.hasNext();
                if (hasNext == 0) {
                    return false;
                }
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.isUp() && networkInterface.getInterfaceAddresses().size() != 0) {
                    Log.d(StringFog.decrypt("vizzU8U=\n", "kwHefuil51A=\n"), StringFog.decrypt("Rr0j8xp3dfJL5lyjOkdy4EC8HsoaVmPlSa8W5lRsZ/pK9FU=\n", "L851g3QiBpc=\n") + networkInterface.getName());
                    if (networkInterface.getName().contains(StringFog.decrypt("u9Ia\n", "z6d0vXWcUl4=\n")) || networkInterface.getName().contains(StringFog.decrypt("T2Mr\n", "PxNbbBHdKIo=\n")) || networkInterface.getName().contains(StringFog.decrypt("YW6ACw==\n", "DVz0e0ZBvC4=\n")) || networkInterface.getName().contains(StringFog.decrypt("YlXcRTc=\n", "CyWvIFTbzQ8=\n")) || networkInterface.getName().contains(StringFog.decrypt("4vSuMg==\n", "koTaQjUbqfE=\n"))) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
            hasNext.printStackTrace();
            return false;
        }
    }

    public static boolean checkroot() {
        String str = Build.TAGS;
        if (str != null && str.contains(StringFog.decrypt("V6BZOwOARzBQ\n", "I8UqTy7rIkk=\n"))) {
            return true;
        }
        String[] strArr = {StringFog.decrypt("YZk64Kp1X+4vmjO8jWVCpDyfMPasPlOxJQ==\n", "TupDk94QMsE=\n"), StringFog.decrypt("o6LlIoYB2+c=\n", "jNGHS+guqJI=\n"), StringFog.decrypt("ZMrh4bD5IZ8p0Pa9t+k=\n", "S7mYksScTLA=\n"), StringFog.decrypt("J24sRpqhtJlwfzxbwbes\n", "CB1VNe7E2bY=\n"), StringFog.decrypt("L6uhktq9kWhjrqzJw/CUaS+8tQ==\n", "AM/A5ruS/Qc=\n"), StringFog.decrypt("IHPE9pQe+h1sdsmtl1j4XXxi\n", "DxelgvUxlnI=\n"), StringFog.decrypt("eJKiL/o7bvskhfQk7Ddt+ySU\n", "V+HbXI5eA9Q=\n"), StringFog.decrypt("twCLKMyLwBf6Gpx03o/EVOsSlD6Xndg=\n", "mHPyW7jurTg=\n"), StringFog.decrypt("4eVMj0Teh06t4EHUVoQ=\n", "zoEt+yXx6yE=\n"), StringFog.decrypt("sKRJe8FWqAnsog==\n", "n9c8VKM/xiY=\n")};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        Process process = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            String[] strArr2 = new String[2];
            strArr2[0] = StringFog.decrypt("+7bucCDm37+sp/5te/Ta+bet\n", "1MWXA1SDspA=\n");
            strArr2[1] = StringFog.decrypt("xtk=\n", "tay+8bbaK6w=\n");
            Process exec = runtime.exec(strArr2);
            if (new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine() != null) {
                exec.destroy();
                return true;
            }
            exec.destroy();
            return false;
        } catch (Throwable unused) {
            if (0 != 0) {
                process.destroy();
            }
            return false;
        }
    }

    public static boolean checkagency() {
        String host;
        int port;
        if (Build.VERSION.SDK_INT >= 14) {
            host = System.getProperty(StringFog.decrypt("H2t0KQHzGyUPZkg2XPc=\n", "dx8AWS+DaUo=\n"));
            String property = System.getProperty(StringFog.decrypt("SbPDLoUZhZJZvucx2R0=\n", "Ice3Xqtp9/0=\n"));
            String str = property;
            if (property == null) {
                str = StringFog.decrypt("FMU=\n", "OfTxw9XdXjk=\n");
            }
            port = Integer.parseInt(str);
        } else {
            host = Proxy.getHost(context);
            port = Proxy.getPort(context);
        }
        return (TextUtils.isEmpty(host) || port == -1) ? false : true;
    }

    public static String VersionCode() {
        long j = 0;
        try {
            j = Build.VERSION.SDK_INT >= 28 ? context.getApplicationContext().getPackageManager().getPackageInfo(context.getPackageName(), 0).getLongVersionCode() : r0.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(StringFog.decrypt("NBqs8g==\n", "d3XIl3lOn5c=\n"), e.getMessage());
        }
        return String.valueOf(j);
    }

    public static void Toast(String str, float f) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast makeText = Toast.makeText(context, str, 1);
        toast = makeText;
        makeText.setGravity(80, 0, 0);
        toast.setText(str);
        toast.show();
        new Timer().schedule(new v1vLvLE(), (long) (f * 1000.0f));
    }

    public static void CenterToast(String str, float f) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast makeText = Toast.makeText(context, str, 1);
        toast = makeText;
        makeText.setGravity(17, 0, 0);
        toast.setText(str);
        toast.show();
        new Timer().schedule(new evleeEelE(), (long) (f * 1000.0f));
    }

    public static boolean IsEmulator() {
        return EmulatorCheck.isEmulator(context);
    }

    public static String GetUserAgent() {
        return AgentInfo.getDefaultUserAgent(context);
    }

    public static void Toast(String str) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast makeText = Toast.makeText(context, str, 1);
        toast = makeText;
        makeText.setText(str);
        toast.show();
    }
}