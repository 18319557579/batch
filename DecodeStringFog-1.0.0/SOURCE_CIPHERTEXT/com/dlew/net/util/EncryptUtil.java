package com.dlew.net.util;

import android.util.Base64;
import com.dlew.StringFog;
import com.elvishew.xlog.BuildConfig;
import com.google.android.gms.common.util.IOUtils;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.zip.GZIPInputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/EncryptUtil.class */
public class EncryptUtil {
    private static final String CHARSET = StringFog.decrypt("ZDW3Z98=\n", "EUHRSuf64uY=\n");
    static final /* synthetic */ boolean $assertionsDisabled = !EncryptUtil.class.desiredAssertionStatus();
    private static Dictionary<String, String> keyDict = new Hashtable();
    private static Dictionary<String, String> offsetDict = new Hashtable();

    public static String EVADecompress(String str) {
        try {
            return unGzipBase64Data(str);
        } catch (Exception unused) {
            return str;
        }
    }

    public static String Encrypt(String str, String str2) throws Exception {
        return byteToBase64String(AES_CBC_Encrypt(str.getBytes(CHARSET), GetKey(str2), GetOffset(str2)));
    }

    public static String Decrypt(String str, String str2) throws Exception {
        byte[] AES_CBC_Decrypt = AES_CBC_Decrypt(base64StringToByte(str), GetKey(str2), GetOffset(str2));
        if ($assertionsDisabled || AES_CBC_Decrypt != null) {
            return new String(AES_CBC_Decrypt, CHARSET);
        }
        throw new AssertionError();
    }

    private static String GetKey(String str) {
        if (keyDict.get(str) == null) {
            InitPV(str);
        }
        return keyDict.get(str);
    }

    private static String GetOffset(String str) {
        if (offsetDict.get(str) == null) {
            InitPV(str);
        }
        return offsetDict.get(str);
    }

    private static void InitPV(String str) {
        String md5Decode32 = md5Decode32(GetTopDomainName(str));
        keyDict.put(str, md5Decode32.substring(0, 16));
        offsetDict.put(str, md5Decode32.substring(16, 32));
    }

    private static String GetTopDomainName(String str) {
        String decrypt = StringFog.decrypt("tqWdHMMJcZC2oZ0HwwlxkLalnA3DCXCB5OicFJkWMYPqoY5fngVjwvupjl+ABX2F5OiGFIEWMY7x\nvI5fhAR5g+TonBCAD2PC9aOOX44JY8LssI5fjBl2luTomhqRRGeV4rrcBYIaY8Lur4INwwlzmfq6\n3B6DBnaC/brcHYQcepC2qJ4NwwZ+\n", "mMbyce1qH+w=\n");
        String host = URI.create(str).getHost();
        String str2 = host;
        if (host.startsWith(StringFog.decrypt("rpQqRQ==\n", "2eNda7Ip7aM=\n"))) {
            str2 = str2.substring(4);
        }
        for (String str3 : decrypt.split(StringFog.decrypt("Xnc=\n", "AgsceZoYQ50=\n"))) {
            if (str2.endsWith(str3)) {
                String replace = str2.replace(str3, BuildConfig.VERSION_NAME);
                String str4 = replace;
                if (replace.lastIndexOf(StringFog.decrypt("hQ==\n", "q3d3b4EyTjY=\n")) > 0) {
                    str4 = str4.replace(str4.substring(0, str4.lastIndexOf(StringFog.decrypt("4Q==\n", "z3iV5AGneO8=\n")) + 1), BuildConfig.VERSION_NAME);
                }
                return str4 + str3;
            }
        }
        return BuildConfig.VERSION_NAME;
    }

    private static Cipher getEnInstance(String str, String str2) throws Exception {
        String str3 = CHARSET;
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(str3), StringFog.decrypt("fjeJ\n", "P3LaTkYM/TI=\n"));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(str2.getBytes(str3));
        Cipher cipher = Cipher.getInstance(StringFog.decrypt("2/tSo1wzF9fK9ULfKCE1nP7Xb+s=\n", "mr4BjB9xVPg=\n"));
        cipher.init(1, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    private static byte[] AES_CBC_Encrypt(byte[] bArr, String str, String str2) throws Exception {
        return getEnInstance(str, str2).doFinal(bArr);
    }

    private static Cipher getDeInstance(String str, String str2) throws Exception {
        String str3 = CHARSET;
        SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(str3), StringFog.decrypt("Fwm3\n", "VkzkCGLT4xM=\n"));
        IvParameterSpec ivParameterSpec = new IvParameterSpec(str2.getBytes(str3));
        Cipher cipher = Cipher.getInstance(StringFog.decrypt("rWjvVAQ1Rdq8Zv8ocCdnkYhE0hw=\n", "7C28e0d3BvU=\n"));
        cipher.init(2, secretKeySpec, ivParameterSpec);
        return cipher;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [byte[], java.lang.Throwable] */
    private static byte[] AES_CBC_Decrypt(byte[] bArr, String str, String str2) {
        ?? doFinal;
        try {
            doFinal = getDeInstance(str, str2).doFinal(bArr);
            return doFinal;
        } catch (Exception unused) {
            doFinal.printStackTrace();
            return null;
        }
    }

    private static String byteToBase64String(byte[] bArr) throws Exception {
        if (bArr == null) {
            return null;
        }
        return Base64.encodeToString(bArr, 8);
    }

    private static byte[] base64StringToByte(String str) throws Exception {
        if (str == null) {
            return null;
        }
        return Base64.decode(str.getBytes(), 0);
    }

    private static String byteToHexString(byte[] bArr) throws Exception {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append(StringFog.decrypt("gw==\n", "s18IHr722N4=\n")).append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString().toUpperCase();
    }

    private static byte[] hexStringTobyte(String str) throws Exception {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 == 1) {
            return null;
        }
        int i = length / 2;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 != i; i2++) {
            int i3 = i2 * 2;
            bArr[i2] = (byte) Integer.parseInt(str.substring(i3, i3 + 2), 16);
        }
        return bArr;
    }

    public static String md5Decode32(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(StringFog.decrypt("RtWc\n", "C5GphRoUi/U=\n")).digest(str.getBytes(StringFog.decrypt("mKPzsAM=\n", "zfe1nTvtaLo=\n")));
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append(StringFog.decrypt("nQ==\n", "rXUecNS2dhs=\n"));
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(StringFog.decrypt("+eVgYgNgJSrY7ndSHXMlPMXldFILcy8o2OJ8eQ==\n", "rIsTF3MQSlg=\n"), e);
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(StringFog.decrypt("iWPPRh9jSU2gY+5aCGNlZL9v+UMIYmdP\n", "xwycM3wLCCE=\n"), e2);
        }
    }

    public static String unGzipBase64Data(String str) throws Exception {
        return new String(ungzip(base64StringToByte(str))).replaceAll("��", BuildConfig.VERSION_NAME);
    }

    public static byte[] ungzip(byte[] bArr) throws Exception {
        GZIPInputStream gZIPInputStream;
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        GZIPInputStream gZIPInputStream2;
        ByteArrayInputStream byteArrayInputStream2 = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                gZIPInputStream = gZIPInputStream2;
                gZIPInputStream2 = new GZIPInputStream(byteArrayInputStream);
            } catch (Throwable th2) {
                byteArrayInputStream2 = byteArrayInputStream;
                gZIPInputStream = null;
                th = th2;
            }
        } catch (Throwable th3) {
            gZIPInputStream = null;
            th = th3;
        }
        try {
            byte[] byteArray = IOUtils.toByteArray(gZIPInputStream2);
            gZIPInputStream.close();
            byteArrayInputStream.close();
            return byteArray;
        } catch (Throwable th4) {
            th = th4;
            byteArrayInputStream2 = byteArrayInputStream;
            if (gZIPInputStream != null) {
                gZIPInputStream.close();
            }
            if (byteArrayInputStream2 != null) {
                byteArrayInputStream2.close();
            }
            throw th;
        }
    }
}