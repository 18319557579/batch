package com.elvishew.xlog.printer.file.backup;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/backup/AbstractBackupStrategy.class */
public abstract class AbstractBackupStrategy implements BackupStrategy2 {
    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy2
    public String getBackupFileName(String str, int i) {
        return str + ".bak." + i;
    }
}