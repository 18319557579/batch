package com.elvishew.xlog;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/LogUtils.class */
public class LogUtils {
    public static String formatJson(String str) {
        XLog.assertInitialization();
        return XLog.sLogConfiguration.jsonFormatter.format(str);
    }

    public static String formatXml(String str) {
        XLog.assertInitialization();
        return XLog.sLogConfiguration.xmlFormatter.format(str);
    }

    public static String formatThrowable(Throwable th) {
        XLog.assertInitialization();
        return XLog.sLogConfiguration.throwableFormatter.format(th);
    }

    public static String formatThread(Thread thread) {
        XLog.assertInitialization();
        return XLog.sLogConfiguration.threadFormatter.format(thread);
    }

    public static String formatStackTrace(StackTraceElement[] stackTraceElementArr) {
        XLog.assertInitialization();
        return XLog.sLogConfiguration.stackTraceFormatter.format(stackTraceElementArr);
    }

    public static String addBorder(String[] strArr) {
        XLog.assertInitialization();
        return XLog.sLogConfiguration.borderFormatter.format(strArr);
    }

    public static void compress(String str, String str2) throws IOException {
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            throw new IOException("Folder " + str + " does't exist or isn't a directory");
        }
        File file2 = new File(str2);
        if (!file2.exists()) {
            File parentFile = file2.getParentFile();
            if (!parentFile.exists() && !parentFile.mkdirs()) {
                throw new IOException("Zip folder " + parentFile.getAbsolutePath() + " not created");
            }
            if (!file2.createNewFile()) {
                throw new IOException("Zip file " + str2 + " not created");
            }
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file2)));
        try {
            byte[] bArr = new byte[8192];
            for (String str3 : file.list()) {
                if (!str3.equals(".") && !str3.equals("..")) {
                    File file3 = new File(file, str3);
                    if (file3.isFile()) {
                        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file3), 8192);
                        try {
                            zipOutputStream.putNextEntry(new ZipEntry(str3));
                            while (true) {
                                int read = bufferedInputStream.read(bArr, 0, 8192);
                                if (read == -1) {
                                    break;
                                } else {
                                    zipOutputStream.write(bArr, 0, read);
                                }
                            }
                            try {
                                bufferedInputStream.close();
                            } catch (IOException unused) {
                            }
                        } catch (Throwable th) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException unused2) {
                            }
                            throw th;
                        }
                    } else {
                        continue;
                    }
                }
            }
            try {
                zipOutputStream.close();
            } catch (IOException unused3) {
            }
        } catch (Throwable th2) {
            try {
                zipOutputStream.close();
            } catch (IOException unused4) {
            }
            throw th2;
        }
    }
}