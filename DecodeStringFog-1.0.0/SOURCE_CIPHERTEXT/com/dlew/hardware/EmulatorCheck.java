package com.dlew.hardware;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.dlew.StringFog;
import java.io.File;
import java.io.FileInputStream;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/EmulatorCheck.class */
public class EmulatorCheck {
    private static String[] known_pipes = {StringFog.decrypt("9xVJvCwtDbOzFFjlcjsPpbw=\n", "2HEsygNeYtA=\n"), StringFog.decrypt("kF/DUSSCL37KZNZOe5Y=\n", "vzumJwvzShM=\n")};
    private static String[] known_qemu_drivers = {StringFog.decrypt("pAPcHXGEP98=\n", "w2yweRftTLc=\n")};
    private static String[] known_files = {StringFog.decrypt("0WodX8VRqwiScAYD3V2kRKF0BUDdW6V4mnwGWdZrt0KTbEpf3g==\n", "/hlkLLE0xic=\n"), StringFog.decrypt("VkqDAIZQ5PkMZo4ByELk\n", "eTn6c6khgZQ=\n"), StringFog.decrypt("WA5+FqLeYM0VFGlKp95gl1oNdQqmyA==\n", "d30HZda7DeI=\n")};
    private static String[] known_numbers = {StringFog.decrypt("o25bi04WqQCnblo=\n", "kltuvnskmDU=\n"), StringFog.decrypt("ww3xoRj0qibHDfI=\n", "8jjElC3GmxM=\n"), StringFog.decrypt("QLjBa2bLXMpEuMw=\n", "cY30XlP5bf8=\n"), StringFog.decrypt("bd4dyEuJ6Utp3Rg=\n", "XOso/X672H4=\n"), StringFog.decrypt("2kI6L0IKfPTeQT0=\n", "63cPGnc4TcE=\n"), StringFog.decrypt("m1VkPzAGY/WfVmU=\n", "qmBRCgU0UsA=\n"), StringFog.decrypt("nMaONdouQIaYxY0=\n", "rfO7AO8ccbM=\n"), StringFog.decrypt("FvizMskS1zwS+74=\n", "J82GB/wg5gk=\n"), StringFog.decrypt("SGQVKVNd1bxMZhA=\n", "eVEgHGZv5Ik=\n"), StringFog.decrypt("UBsZzdPFIYRUGR4=\n", "YS4s+Ob3ELE=\n"), StringFog.decrypt("Eza8hAAwdiEXNL0=\n", "IgOJsTUCRxQ=\n"), StringFog.decrypt("/rAwZHkT0xD6sjM=\n", "z4UFUUwh4iU=\n"), StringFog.decrypt("IUWF3mAaydclR4g=\n", "EHCw61Uo+OI=\n"), StringFog.decrypt("jCjePMGgsvGIJds=\n", "vR3rCfSSg8Q=\n"), StringFog.decrypt("1rX8Gr2vsRDSuPs=\n", "54DJL4idgCU=\n"), StringFog.decrypt("0JNTh94KW2rUnlI=\n", "4aZmsus4al8=\n")};
    private static String[] known_device_ids = {StringFog.decrypt("kWumMCPsX72Ra6YwI+xf\n", "oVuWABPcb40=\n")};
    private static String[] known_imsi_ids = {StringFog.decrypt("czq4WQhlwXBwO7hbDmXB\n", "QAuIaz5V8UA=\n")};
    private final int REQUEST_READ_PHONE_STATE = 0;

    public static boolean checkPipes() {
        int i = 0;
        while (true) {
            int i2 = i;
            String[] strArr = known_pipes;
            if (i2 >= strArr.length) {
                Log.i(StringFog.decrypt("TL5mc7Xp8Q==\n", "HtsVBtmdy08=\n"), StringFog.decrypt("Rw+40Z4wPngpEKWBvSpx\n", "CWDM8dhZUBw=\n"));
                return false;
            }
            if (new File(strArr[i]).exists()) {
                Log.v(StringFog.decrypt("Z+mc6JtwFA==\n", "NYzvnfcELgg=\n"), StringFog.decrypt("v/7lZJoNg/ec5Ko=\n", "+ZeLALp96oc=\n"));
                return true;
            }
            i++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v22, types: [java.io.FileInputStream, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v9, types: [byte[]] */
    public static Boolean checkQEmuDriverFile() {
        File file = new File(StringFog.decrypt("7WKtTEphhty7PbtRQDiX2rE=\n", "whLfIylO8qg=\n"));
        if (file.exists() && file.canRead()) {
            ?? r0 = new byte[1024];
            try {
                r0 = new FileInputStream(file);
                r0.read(r0);
                r0.close();
            } catch (Exception unused) {
                r0.printStackTrace();
            }
            String str = new String((byte[]) r0);
            for (String str2 : known_qemu_drivers) {
                if (str.indexOf(str2) != -1) {
                    Log.i(StringFog.decrypt("uZ2T/k8q+w==\n", "6/jgiyNewaA=\n"), StringFog.decrypt("EyzkbiUUEaEiGvtvaAogqics/G93DF4=\n", "VUWKCgV/f84=\n"));
                    return Boolean.TRUE;
                }
            }
        }
        Log.i(StringFog.decrypt("02OMk0gOpw==\n", "gQb/5iR6nbw=\n"), StringFog.decrypt("VX5RjICC8jU7ekvDsYXDIH58UPOimfUnfmNWjQ==\n", "GxElrMbrnFE=\n"));
        return Boolean.FALSE;
    }

    public static Boolean CheckEmulatorFiles() {
        int i = 0;
        while (true) {
            int i2 = i;
            String[] strArr = known_files;
            if (i2 >= strArr.length) {
                Log.v(StringFog.decrypt("ZQ6NnRVFjA==\n", "N2v+6Hkxtsk=\n"), StringFog.decrypt("G1a78R1IEjF1fKKkN0AIOicZibg3RA90\n", "VTnP0VshfFU=\n"));
                return Boolean.FALSE;
            }
            if (new File(strArr[i]).exists()) {
                Log.v(StringFog.decrypt("42IfmPObTw==\n", "sQds7Z/vdbE=\n"), StringFog.decrypt("IwV5GavvKB8JDWMS+YoDAwkJZFw=\n", "ZWwXfYuqRWo=\n"));
                return Boolean.TRUE;
            }
            i++;
        }
    }

    public static Boolean CheckPhoneNumber(Context context) {
        String line1Number = ((TelephonyManager) context.getSystemService(StringFog.decrypt("Cdj3PIo=\n", "ebCYUu8wUfs=\n"))).getLine1Number();
        for (String str : known_numbers) {
            if (str.equalsIgnoreCase(line1Number)) {
                Log.v(StringFog.decrypt("RdbVdTVskA==\n", "F7OmAFkYqpU=\n"), StringFog.decrypt("tfstkj42DYyd9w2DcwQAkdI=\n", "85JD9h5mZeM=\n"));
                return Boolean.TRUE;
            }
        }
        Log.v(StringFog.decrypt("mYwIRCwoog==\n", "y+l7MUBcmMo=\n"), StringFog.decrypt("MMJ0uy3x/cFe/Wj0Bf3d0BPPZelK\n", "fq0Am2uYk6U=\n"));
        return Boolean.FALSE;
    }

    public static Boolean CheckDeviceIDS(Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService(StringFog.decrypt("6J57xXw=\n", "mPYUqxmv04Y=\n"))).getDeviceId();
        for (String str : known_device_ids) {
            if (str.equalsIgnoreCase(deviceId)) {
                Log.v(StringFog.decrypt("X7Nj/ISLCw==\n", "DdYQiej/MSo=\n"), StringFog.decrypt("omMFGNtSrX3eKltMywv5PtQ6W0zLC/k+1Cs=\n", "5AprfPs7yQ4=\n"));
                return Boolean.TRUE;
            }
        }
        Log.v(StringFog.decrypt("82wW5V/Hzw==\n", "oQllkDOz9cE=\n"), StringFog.decrypt("QTHmScVWObkvN/YauR9n7T9uolmzD2ftP26iWbMe\n", "D16SaYM/V90=\n"));
        return Boolean.FALSE;
    }

    public static Boolean CheckImsiIDS(Context context) {
        String subscriberId = ((TelephonyManager) context.getSystemService(StringFog.decrypt("vayqg0M=\n", "zcTF7SbpBIE=\n"))).getSubscriberId();
        for (String str : known_imsi_ids) {
            if (str.equalsIgnoreCase(subscriberId)) {
                Log.v(StringFog.decrypt("46BjPn4/pQ==\n", "scUQSxJLn7k=\n"), StringFog.decrypt("jkXGz6JL5GKhDMHP8RipIvkcmp2yErkh+ByYm7ISqA==\n", "yCyoq4IiiRE=\n"));
                return Boolean.TRUE;
            }
        }
        Log.v(StringFog.decrypt("WfqKRqKR0w==\n", "C5/5M87l6cw=\n"), StringFog.decrypt("TptpFF0q8z4gnXBHcmP0PnPOPQcqc69sMMQtBCtzrWowxDw=\n", "APQdNBtDnVo=\n"));
        return Boolean.FALSE;
    }

    public static Boolean CheckEmulatorBuild(Context context) {
        String str = Build.BRAND;
        String str2 = Build.DEVICE;
        String str3 = Build.HARDWARE;
        String str4 = Build.MODEL;
        String str5 = Build.PRODUCT;
        if (str != StringFog.decrypt("hnwP/czBHg==\n", "4RlhmL6ofco=\n") && str2 != StringFog.decrypt("vyeR3RdOtA==\n", "2EL/uGUn16I=\n") && str4 != StringFog.decrypt("tmCu\n", "xQTFTBXm/Wo=\n") && str5 != StringFog.decrypt("8/NP\n", "gJckZ9N5kJQ=\n") && str3 != StringFog.decrypt("ajX4tarPm1M=\n", "DVqU0cym6Ds=\n")) {
            String str6 = Build.FINGERPRINT;
            if ((!str6.startsWith(StringFog.decrypt("r7i1rqcsaLCsvIWuuyEora2I\n", "yNfayctJR8M=\n")) || !str6.endsWith(StringFog.decrypt("rxD3efRI3Nr5AOVv40rF2uwW\n", "lWWEHIZnrr8=\n")) || Build.MANUFACTURER != StringFog.decrypt("nyTn6jKN\n", "2EuIjV7orLQ=\n") || !str5.startsWith(StringFog.decrypt("tfyC63pHSbGo/bY=\n", "xpjptB03Id4=\n")) || str != StringFog.decrypt("aYVn73Jl\n", "DuoIiB4ABbs=\n") || !str4.startsWith(StringFog.decrypt("fgAiHX0s/wxjARY=\n", "DWRJQhpcl2M=\n"))) && !str6.startsWith(StringFog.decrypt("ZYUH3lf8Mw==\n", "AuBpuyWVUN0=\n")) && !str6.startsWith(StringFog.decrypt("UBhi5kcjxw==\n", "JXYJiChUqU8=\n")) && !str4.contains(StringFog.decrypt("HPVi0WCSGekf8Q==\n", "e5oNtgz3Rpo=\n")) && !str4.contains(StringFog.decrypt("1sVdUtB8hoU=\n", "k6goPrEI6fc=\n")) && !str4.contains(StringFog.decrypt("+yoDlEsepj3pACzGRgKrcc5kAYlWV7oljA==\n", "ukRn5iR3wh0=\n")) && ((StringFog.decrypt("36YkUFnEaJ3rixhnY/JlgOCA\n", "juV7AjyiDe8=\n") != Build.BOARD || StringFog.decrypt("6NfCtwtD\n", "sL6j2GYq2Do=\n").equalsIgnoreCase(Build.MANUFACTURER)) && !Build.MANUFACTURER.contains(StringFog.decrypt("EwkRevn53Zo7Ag==\n", "VGx/A5SWqfM=\n")) && !Build.HOST.startsWith(StringFog.decrypt("eyRxHO4=\n", "OVEYcIq9dzE=\n")) && ((!str.startsWith(StringFog.decrypt("tWP7RORhsQ==\n", "0gaVIZYI0ic=\n")) || !str2.startsWith(StringFog.decrypt("1NZYxnNClg==\n", "s7M2owEr9TM=\n"))) && str5 != StringFog.decrypt("DUPXSIw6JBcORw==\n", "aiy4L+Bfe2Q=\n") && System.getProperty(StringFog.decrypt("GnGvvkKZMP8EMPCwSp4=\n", "aB6B1SfrXpo=\n")) != StringFog.decrypt("LA==\n", "Hfg4urtS4GA=\n")))) {
                Log.v(StringFog.decrypt("0bGUS2nKnA==\n", "g9TnPgW+phs=\n"), StringFog.decrypt("LuWnYAjrSMdAz741IuNSzBKqsTlux0vWDOunLzzAU8oM7vI=\n", "YIrTQE6CJqM=\n"));
                return Boolean.FALSE;
            }
        }
        Log.v(StringFog.decrypt("yzowolfG8Q==\n", "mV9D1zuyy8Q=\n"), StringFog.decrypt("Zsma8vhEG1dMwYD5qiEUWwDlmeO0YAJNUuKB/7RlVw==\n", "IKD0ltgBdiI=\n"));
        return Boolean.TRUE;
    }

    public static boolean CheckOperatorNameAndroid(Context context) {
        if (((TelephonyManager) context.getSystemService(StringFog.decrypt("WnLLPX0=\n", "KhqkUxihRJY=\n"))).getNetworkOperatorName().toLowerCase().equals(StringFog.decrypt("H4gzkItC1g==\n", "fuZX4uQrst4=\n"))) {
            Log.v(StringFog.decrypt("rIdoEeWqCg==\n", "/uIbZIneMIo=\n"), StringFog.decrypt("NeBlLsUGuSof6H8ll2O2JlPGey+XIqAwAcdqJ4Bi\n", "c4kLSuVD1F8=\n"));
            return true;
        }
        Log.v(StringFog.decrypt("tUMiSGbuQQ==\n", "5yZRPQqaexA=\n"), StringFog.decrypt("r/wi5pa+qDfB1juzvLayPJOzNL/wmLY2k/IiqaKZpz6Esg==\n", "4ZNWxtDXxlM=\n"));
        return false;
    }

    public static boolean isEmulator(Context context) {
        return checkPipes() || checkQEmuDriverFile().booleanValue() || CheckEmulatorBuild(context).booleanValue() || CheckOperatorNameAndroid(context) || !checkCallPhoneIntent(context);
    }

    private static boolean checkCallPhoneIntent(Context context) {
        String decrypt = StringFog.decrypt("a2IAGWpr4EUqMQ==\n", "HwdsI1tZ03E=\n");
        Intent intent = new Intent();
        intent.setData(Uri.parse(decrypt));
        intent.setAction(StringFog.decrypt("vW3ZJjXRFzi1bckxNMxdd7931Ds0ljdfnU8=\n", "3AO9VFq4cxY=\n"));
        return intent.resolveActivity(context.getPackageManager()) != null;
    }
}