package com.elvishew.xlog.printer.file.backup;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/backup/BackupStrategy2.class */
public interface BackupStrategy2 extends BackupStrategy {
    public static final int NO_LIMIT = 0;

    int getMaxBackupIndex();

    String getBackupFileName(String str, int i);
}