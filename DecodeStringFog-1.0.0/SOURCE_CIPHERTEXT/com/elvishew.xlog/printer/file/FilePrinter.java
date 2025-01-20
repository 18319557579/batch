package com.elvishew.xlog.printer.file;

import com.elvishew.xlog.flattener.Flattener;
import com.elvishew.xlog.flattener.Flattener2;
import com.elvishew.xlog.internal.DefaultsFactory;
import com.elvishew.xlog.internal.Platform;
import com.elvishew.xlog.internal.printer.file.backup.BackupStrategyWrapper;
import com.elvishew.xlog.internal.printer.file.backup.BackupUtil;
import com.elvishew.xlog.printer.Printer;
import com.elvishew.xlog.printer.file.backup.BackupStrategy;
import com.elvishew.xlog.printer.file.backup.BackupStrategy2;
import com.elvishew.xlog.printer.file.clean.CleanStrategy;
import com.elvishew.xlog.printer.file.naming.FileNameGenerator;
import com.elvishew.xlog.printer.file.writer.Writer;
import java.io.File;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/FilePrinter.class */
public class FilePrinter implements Printer {
    private static final boolean USE_WORKER = true;
    private final String folderPath;
    private final FileNameGenerator fileNameGenerator;
    private final BackupStrategy2 backupStrategy;
    private final CleanStrategy cleanStrategy;
    private final boolean cleanInRealTime;
    private Flattener2 flattener;
    private Writer writer;
    private volatile evleeEelE worker = new evleeEelE();

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/FilePrinter$Builder.class */
    public static class Builder {
        String folderPath;
        FileNameGenerator fileNameGenerator;
        BackupStrategy2 backupStrategy;
        CleanStrategy cleanStrategy;
        boolean cleanInRealTime;
        Flattener2 flattener;
        Writer writer;

        /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/FilePrinter$Builder$EvLveE1.class */
        public class EvLveE1 implements Flattener2 {

            /* renamed from: EvLveE1, reason: collision with root package name */
            public final /* synthetic */ Flattener f73EvLveE1;

            public EvLveE1(Flattener flattener) {
                this.f73EvLveE1 = flattener;
            }

            @Override // com.elvishew.xlog.flattener.Flattener2
            public CharSequence flatten(long j, int i, String str, String str2) {
                return this.f73EvLveE1.flatten(i, str, str2);
            }
        }

        public Builder(String str) {
            this.folderPath = str;
        }

        private void fillEmptyFields() {
            if (this.fileNameGenerator == null) {
                this.fileNameGenerator = DefaultsFactory.createFileNameGenerator();
            }
            if (this.backupStrategy == null) {
                this.backupStrategy = DefaultsFactory.createBackupStrategy();
            }
            if (this.cleanStrategy == null) {
                this.cleanStrategy = DefaultsFactory.createCleanStrategy();
            }
            if (this.flattener == null) {
                this.flattener = DefaultsFactory.createFlattener2();
            }
            if (this.writer == null) {
                this.writer = DefaultsFactory.createWriter();
            }
        }

        public Builder fileNameGenerator(FileNameGenerator fileNameGenerator) {
            this.fileNameGenerator = fileNameGenerator;
            return this;
        }

        public Builder backupStrategy(BackupStrategy backupStrategy) {
            if (!(backupStrategy instanceof BackupStrategy2)) {
                backupStrategy = new BackupStrategyWrapper(backupStrategy);
            }
            BackupStrategy2 backupStrategy2 = (BackupStrategy2) backupStrategy;
            this.backupStrategy = backupStrategy2;
            BackupUtil.verifyBackupStrategy(backupStrategy2);
            return this;
        }

        public Builder cleanStrategy(CleanStrategy cleanStrategy) {
            this.cleanStrategy = cleanStrategy;
            return this;
        }

        @Deprecated
        public Builder logFlattener(Flattener flattener) {
            return flattener(new EvLveE1(flattener));
        }

        public Builder flattener(Flattener2 flattener2) {
            this.flattener = flattener2;
            return this;
        }

        public Builder writer(Writer writer) {
            this.writer = writer;
            return this;
        }

        public FilePrinter build() {
            fillEmptyFields();
            return new FilePrinter(this);
        }

        public Builder cleanStrategy(CleanStrategy cleanStrategy, boolean z) {
            this.cleanStrategy = cleanStrategy;
            this.cleanInRealTime = z;
            return this;
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/FilePrinter$v1vLvLE.class */
    public static class v1vLvLE {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public long f75EvLveE1;
        public int v1vLvLE;
        public String evleeEelE;
        public String EeleE;

        public v1vLvLE(long j, int i, String str, String str2) {
            this.f75EvLveE1 = j;
            this.v1vLvLE = i;
            this.evleeEelE = str;
            this.EeleE = str2;
        }
    }

    public FilePrinter(Builder builder) {
        this.folderPath = builder.folderPath;
        this.fileNameGenerator = builder.fileNameGenerator;
        this.backupStrategy = builder.backupStrategy;
        this.cleanStrategy = builder.cleanStrategy;
        this.cleanInRealTime = builder.cleanInRealTime;
        this.flattener = builder.flattener;
        this.writer = builder.writer;
        checkLogFolder();
    }

    private void checkLogFolder() {
        File file = new File(this.folderPath);
        if (file.exists()) {
            return;
        }
        file.mkdirs();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doPrintln(long j, int i, String str, String str2) {
        String openedFileName = this.writer.getOpenedFileName();
        String str3 = openedFileName;
        boolean z = !this.writer.isOpened();
        if (openedFileName == null || z || this.fileNameGenerator.isFileNameChangeable()) {
            String generateFileName = this.fileNameGenerator.generateFileName(i, System.currentTimeMillis());
            if (generateFileName == null || generateFileName.trim().length() == 0) {
                Platform.get().error("File name should not be empty, ignore log: " + str2);
                return;
            } else if (!generateFileName.equals(str3) || z) {
                this.writer.close();
                cleanLogFilesIfNecessary(null);
                if (!this.writer.open(new File(this.folderPath, generateFileName))) {
                    return;
                } else {
                    str3 = generateFileName;
                }
            }
        }
        File openedFile = this.writer.getOpenedFile();
        if (this.backupStrategy.shouldBackup(openedFile)) {
            this.writer.close();
            BackupUtil.backup(openedFile, this.backupStrategy);
            if (!this.writer.open(new File(this.folderPath, str3))) {
                return;
            }
        }
        this.writer.appendLog(this.flattener.flatten(j, i, str, str2).toString());
    }

    private void cleanLogFilesIfNecessary(String str) {
        File[] listFiles = new File(this.folderPath).listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            if ((str == null || !str.equals(file.getName())) && this.cleanStrategy.shouldClean(file)) {
                file.delete();
            }
        }
    }

    @Override // com.elvishew.xlog.printer.Printer
    public void println(int i, String str, String str2) {
        long currentTimeMillis = System.currentTimeMillis();
        if (!this.worker.EvLveE1()) {
            this.worker.v1vLvLE();
        }
        this.worker.EvLveE1(new v1vLvLE(currentTimeMillis, i, str, str2));
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/elvishew/xlog/printer/file/FilePrinter$evleeEelE.class */
    public class evleeEelE implements Runnable {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public BlockingQueue<v1vLvLE> f74EvLveE1;
        public volatile boolean v1vLvLE;

        public evleeEelE() {
            this.f74EvLveE1 = new LinkedBlockingQueue();
        }

        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable, java.util.concurrent.BlockingQueue, java.util.concurrent.BlockingQueue<com.elvishew.xlog.printer.file.FilePrinter$v1vLvLE>] */
        public void EvLveE1(v1vLvLE v1vlvle) {
            ?? r0;
            try {
                r0 = this.f74EvLveE1;
                r0.put(v1vlvle);
            } catch (InterruptedException unused) {
                r0.printStackTrace();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void v1vLvLE() {
            synchronized (this) {
                if (this.v1vLvLE) {
                    return;
                }
                new Thread(this).start();
                this.v1vLvLE = true;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v6, types: [com.elvishew.xlog.printer.file.FilePrinter$v1vLvLE] */
        /* JADX WARN: Type inference failed for: r0v8, types: [com.elvishew.xlog.printer.file.FilePrinter] */
        /* JADX WARN: Type inference failed for: r7v0, types: [com.elvishew.xlog.printer.file.FilePrinter$evleeEelE, java.lang.Throwable] */
        @Override // java.lang.Runnable
        public void run() {
            v1vLvLE v1vlvle;
            while (true) {
                try {
                    v1vlvle = this.f74EvLveE1.take();
                    if (v1vlvle == 0) {
                        return;
                    }
                    v1vlvle = FilePrinter.this;
                    v1vlvle.doPrintln(v1vlvle.f75EvLveE1, v1vlvle.v1vLvLE, v1vlvle.evleeEelE, v1vlvle.EeleE);
                } catch (InterruptedException unused) {
                    v1vlvle.printStackTrace();
                    synchronized (this) {
                        this.v1vLvLE = false;
                        return;
                    }
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [com.elvishew.xlog.printer.file.FilePrinter$evleeEelE] */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
        public boolean EvLveE1() {
            ?? r0 = this;
            synchronized (r0) {
                r0 = r0.v1vLvLE;
                return r0;
            }
        }
    }
}