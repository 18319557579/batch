package com.elvishew.xlog.printer.file.backup;

import java.io.File;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/backup/NeverBackupStrategy.class */
public class NeverBackupStrategy implements BackupStrategy {
    @Override // com.elvishew.xlog.printer.file.backup.BackupStrategy
    public boolean shouldBackup(File file) {
        return false;
    }
}