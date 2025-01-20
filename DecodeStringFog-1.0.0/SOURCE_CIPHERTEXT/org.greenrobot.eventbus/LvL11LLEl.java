package org.greenrobot.eventbus;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.greenrobot.eventbus.meta.SubscriberInfo;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

/* compiled from: SubscriberMethodFinder.java */
/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/LvL11LLEl.class */
public class LvL11LLEl {
    public static final int EeleE = 64;
    public static final int LvL11LLEl = 4096;
    public static final int EvLeE11LLE = 5192;
    public static final int vl1L = 4;

    /* renamed from: EvLveE1, reason: collision with root package name */
    public List<SubscriberInfoIndex> f83EvLveE1;
    public final boolean v1vLvLE;
    public final boolean evleeEelE;
    public static final Map<Class<?>, List<SubscriberMethod>> vvlEEv1lv = new ConcurrentHashMap();
    public static final EvLveE1[] vv11e = new EvLveE1[4];

    public LvL11LLEl(List<SubscriberInfoIndex> list, boolean z, boolean z2) {
        this.f83EvLveE1 = list;
        this.v1vLvLE = z;
        this.evleeEelE = z2;
    }

    public List<SubscriberMethod> EvLveE1(Class<?> cls) {
        Map<Class<?>, List<SubscriberMethod>> map = vvlEEv1lv;
        List<SubscriberMethod> list = map.get(cls);
        if (list != null) {
            return list;
        }
        List<SubscriberMethod> evleeEelE = this.evleeEelE ? evleeEelE(cls) : v1vLvLE(cls);
        if (evleeEelE.isEmpty()) {
            throw new EventBusException("Subscriber " + cls + " and its super classes have no public methods with the @Subscribe annotation");
        }
        List<SubscriberMethod> list2 = evleeEelE;
        map.put(cls, evleeEelE);
        return list2;
    }

    public final List<SubscriberMethod> v1vLvLE(Class<?> cls) {
        EvLveE1 v1vLvLE = v1vLvLE();
        v1vLvLE.EvLveE1(cls);
        while (v1vLvLE.EvLeE11LLE != null) {
            SubscriberInfo evleeEelE = evleeEelE(v1vLvLE);
            v1vLvLE.vl1L = evleeEelE;
            if (evleeEelE != null) {
                for (SubscriberMethod subscriberMethod : evleeEelE.getSubscriberMethods()) {
                    if (v1vLvLE.EvLveE1(subscriberMethod.method, subscriberMethod.eventType)) {
                        v1vLvLE.f84EvLveE1.add(subscriberMethod);
                    }
                }
            } else {
                EvLveE1(v1vLvLE);
            }
            v1vLvLE.EvLveE1();
        }
        return v1vLvLE(v1vLvLE);
    }

    public final SubscriberInfo evleeEelE(EvLveE1 evLveE1) {
        SubscriberInfo subscriberInfo = evLveE1.vl1L;
        if (subscriberInfo != null && subscriberInfo.getSuperSubscriberInfo() != null) {
            SubscriberInfo superSubscriberInfo = evLveE1.vl1L.getSuperSubscriberInfo();
            if (evLveE1.EvLeE11LLE == superSubscriberInfo.getSubscriberClass()) {
                return superSubscriberInfo;
            }
        }
        List<SubscriberInfoIndex> list = this.f83EvLveE1;
        if (list == null) {
            return null;
        }
        Iterator<SubscriberInfoIndex> it = list.iterator();
        while (it.hasNext()) {
            SubscriberInfo subscriberInfo2 = it.next().getSubscriberInfo(evLveE1.EvLeE11LLE);
            if (subscriberInfo2 != null) {
                return subscriberInfo2;
            }
        }
        return null;
    }

    /* compiled from: SubscriberMethodFinder.java */
    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/LvL11LLEl$EvLveE1.class */
    public static class EvLveE1 {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final List<SubscriberMethod> f84EvLveE1 = new ArrayList();
        public final Map<Class, Object> v1vLvLE = new HashMap();
        public final Map<String, Class> evleeEelE = new HashMap();
        public final StringBuilder EeleE = new StringBuilder(128);
        public Class<?> LvL11LLEl;
        public Class<?> EvLeE11LLE;
        public boolean vvlEEv1lv;
        public SubscriberInfo vl1L;

        public void EvLveE1(Class<?> cls) {
            this.EvLeE11LLE = cls;
            this.LvL11LLEl = cls;
            this.vvlEEv1lv = false;
            this.vl1L = null;
        }

        public void v1vLvLE() {
            this.f84EvLveE1.clear();
            this.v1vLvLE.clear();
            this.evleeEelE.clear();
            this.EeleE.setLength(0);
            this.LvL11LLEl = null;
            this.EvLeE11LLE = null;
            this.vvlEEv1lv = false;
            this.vl1L = null;
        }

        public boolean EvLveE1(Method method, Class<?> cls) {
            Object put = this.v1vLvLE.put(cls, method);
            if (put == null) {
                return true;
            }
            if (put instanceof Method) {
                if (v1vLvLE((Method) put, cls)) {
                    this.v1vLvLE.put(cls, this);
                } else {
                    throw new IllegalStateException();
                }
            }
            return v1vLvLE(method, cls);
        }

        public final boolean v1vLvLE(Method method, Class<?> cls) {
            this.EeleE.setLength(0);
            this.EeleE.append(method.getName());
            this.EeleE.append('>').append(cls.getName());
            String sb = this.EeleE.toString();
            Class<?> declaringClass = method.getDeclaringClass();
            Class put = this.evleeEelE.put(sb, declaringClass);
            if (put == null || put.isAssignableFrom(declaringClass)) {
                return true;
            }
            this.evleeEelE.put(sb, put);
            return false;
        }

        public void EvLveE1() {
            if (this.vvlEEv1lv) {
                this.EvLeE11LLE = null;
                return;
            }
            Class<? super Object> superclass = this.EvLeE11LLE.getSuperclass();
            this.EvLeE11LLE = superclass;
            String name = superclass.getName();
            if (name.startsWith("java.") || name.startsWith("javax.") || name.startsWith("android.") || name.startsWith("androidx.")) {
                this.EvLeE11LLE = null;
            }
        }
    }

    public final List<SubscriberMethod> evleeEelE(Class<?> cls) {
        EvLveE1 v1vLvLE = v1vLvLE();
        v1vLvLE.EvLveE1(cls);
        while (v1vLvLE.EvLeE11LLE != null) {
            EvLveE1(v1vLvLE);
            v1vLvLE.EvLveE1();
        }
        return v1vLvLE(v1vLvLE);
    }

    public final void EvLveE1(EvLveE1 evLveE1) {
        String str;
        Method[] methods;
        try {
            methods = evLveE1.EvLeE11LLE.getDeclaredMethods();
        } catch (Throwable unused) {
            try {
                methods = evLveE1.EvLeE11LLE.getMethods();
                evLveE1.vvlEEv1lv = true;
            } catch (LinkageError e) {
                String str2 = "Could not inspect methods of " + evLveE1.EvLeE11LLE.getName();
                if (this.evleeEelE) {
                    str = str2 + ". Please consider using EventBus annotation processor to avoid reflection.";
                } else {
                    str = str2 + ". Please make this class visible to EventBus annotation processor to avoid reflection.";
                }
                throw new EventBusException(str, e);
            }
        }
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            if ((modifiers & 1) != 0 && (modifiers & EvLeE11LLE) == 0) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                if (parameterTypes.length == 1) {
                    Subscribe subscribe = (Subscribe) method.getAnnotation(Subscribe.class);
                    if (subscribe != null) {
                        Class<?> cls = parameterTypes[0];
                        if (evLveE1.EvLveE1(method, cls)) {
                            evLveE1.f84EvLveE1.add(new SubscriberMethod(method, cls, subscribe.threadMode(), subscribe.priority(), subscribe.sticky()));
                        }
                    }
                } else if (this.v1vLvLE && method.isAnnotationPresent(Subscribe.class)) {
                    throw new EventBusException("@Subscribe method " + (method.getDeclaringClass().getName() + "." + method.getName()) + "must have exactly 1 parameter but has " + parameterTypes.length);
                }
            } else if (this.v1vLvLE && method.isAnnotationPresent(Subscribe.class)) {
                throw new EventBusException((method.getDeclaringClass().getName() + "." + method.getName()) + " is a illegal @Subscribe method: must be public, non-static, and non-abstract");
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.util.List<org.greenrobot.eventbus.SubscriberMethod>] */
    public final List<SubscriberMethod> v1vLvLE(EvLveE1 evLveE1) {
        ?? r0;
        ArrayList arrayList = new ArrayList(evLveE1.f84EvLveE1);
        evLveE1.v1vLvLE();
        synchronized (vv11e) {
            int i = 0;
            while (true) {
                r0 = i;
                if (r0 >= 4) {
                    break;
                }
                EvLveE1[] evLveE1Arr = vv11e;
                if (evLveE1Arr[i] == null) {
                    evLveE1Arr[i] = evLveE1;
                    break;
                }
                i++;
            }
            r0 = arrayList;
        }
        return r0;
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Throwable] */
    public final EvLveE1 v1vLvLE() {
        synchronized (vv11e) {
            for (int i = 0; i < 4; i++) {
                EvLveE1[] evLveE1Arr = vv11e;
                EvLveE1 evLveE1 = evLveE1Arr[i];
                if (evLveE1 != null) {
                    evLveE1Arr[i] = null;
                    return evLveE1;
                }
            }
            return new EvLveE1();
        }
    }

    public static void EvLveE1() {
        vvlEEv1lv.clear();
    }
}