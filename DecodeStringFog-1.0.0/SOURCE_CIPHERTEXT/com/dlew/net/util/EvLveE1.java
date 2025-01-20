package com.dlew.net.util;

/* compiled from: CpuManager.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:com/dlew/net/util/EvLveE1.class */
public class EvLveE1 {

    /* renamed from: EvLveE1, reason: collision with root package name */
    public static int f28EvLveE1 = -1;

    public static int EvLveE1() {
        int i = f28EvLveE1;
        if (i != -1) {
            return i;
        }
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        f28EvLveE1 = availableProcessors;
        return availableProcessors;
    }
}