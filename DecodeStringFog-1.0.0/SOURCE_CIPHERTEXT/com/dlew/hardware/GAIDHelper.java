package com.dlew.hardware;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import com.dlew.StringFog;
import com.elvishew.xlog.BuildConfig;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/GAIDHelper.class */
public class GAIDHelper {

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/GAIDHelper$v1vLvLE.class */
    public static final class v1vLvLE implements ServiceConnection {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public boolean f2EvLveE1;
        public final LinkedBlockingQueue<IBinder> v1vLvLE;

        public v1vLvLE() {
            this.f2EvLveE1 = false;
            this.v1vLvLE = new LinkedBlockingQueue<>(1);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                this.v1vLvLE.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }

        public IBinder EvLveE1() throws InterruptedException {
            if (this.f2EvLveE1) {
                throw new IllegalStateException();
            }
            this.f2EvLveE1 = true;
            return this.v1vLvLE.take();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v0, types: [android.content.Context] */
    public static String getGoogleId(Context context) throws Exception {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            return BuildConfig.VERSION_NAME;
        }
        context.getPackageManager().getPackageInfo(StringFog.decrypt("WGY5h8ZAFGRUYDCH0UseclJnMw==\n", "OwlUqacucBY=\n"), 0);
        v1vLvLE v1vlvle = new v1vLvLE();
        Intent intent = new Intent(StringFog.decrypt("cfV9y9WgA1J+/z6E3KseWnv+PoLfvEJUduk+jNaqAkF7/HmAwOEfUGDseYbX4T9hU8hE\n", "EpoQ5bLPbDU=\n"));
        intent.setPackage(StringFog.decrypt("gUdU+dxZWOWOTRe21VJF7YtMF7DWRQ==\n", "4ig517s2N4I=\n"));
        ?? bindService = context.bindService(intent, v1vlvle, 1);
        if (bindService == 0) {
            return BuildConfig.VERSION_NAME;
        }
        try {
            bindService = new evleeEelE(v1vlvle.EvLveE1()).EvLveE1();
            context.unbindService(v1vlvle);
            return bindService;
        } catch (Throwable th) {
            th.unbindService(v1vlvle);
            throw bindService;
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/hardware/GAIDHelper$evleeEelE.class */
    public static final class evleeEelE implements IInterface {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public IBinder f1EvLveE1;

        public evleeEelE(IBinder iBinder) {
            this.f1EvLveE1 = iBinder;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.f1EvLveE1;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [android.os.Parcel] */
        /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r2v3, types: [android.os.IBinder] */
        public String EvLveE1() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            ?? obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(StringFog.decrypt("AX9z9QQlV3AOdTC6DS5KeAt0MLwOORZ2BmMwsgcvVmMLdne+EWRReRZ1bLUCJhZeI3RovhE+UWQL\nfnmSBxldZRR5fb4=\n", "YhAe22NKOBc=\n"));
                this.f1EvLveE1.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                obtain2 = obtain2.readString();
                obtain2.recycle();
                obtain.recycle();
                return obtain2;
            } catch (Throwable th) {
                obtain2.recycle();
                th.recycle();
                throw obtain2;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0 */
        /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
        /* JADX WARN: Type inference failed for: r0v4, types: [int] */
        public boolean EvLveE1(boolean z) throws RemoteException {
            ?? r0 = z;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(StringFog.decrypt("Xp0ZtnQ2jj1Rl1r5fT2TNVSWWv9+Ks87WYFa8Xc8jy5UlB39YXeINEmXBvZyNc8TfJYC/WEtiClU\nnBPRdwqEKEubF/0=\n", "PfJ0mBNZ4Vo=\n"));
                obtain.writeInt(r0 != 0 ? 1 : 0);
                this.f1EvLveE1.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                r0 = obtain2.readInt();
                boolean z2 = r0 != 0;
                obtain2.recycle();
                obtain.recycle();
                return z2;
            } catch (Throwable th) {
                obtain2.recycle();
                th.recycle();
                throw r0;
            }
        }
    }
}