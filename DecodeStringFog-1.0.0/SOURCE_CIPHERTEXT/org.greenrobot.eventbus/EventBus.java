package org.greenrobot.eventbus;

import com.elvishew.xlog.LogLevel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import org.greenrobot.eventbus.android.AndroidDependenciesDetector;
import org.greenrobot.eventbus.meta.SubscriberInfoIndex;

/* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EventBus.class */
public class EventBus {
    public static String TAG = "EventBus";
    static volatile EventBus defaultInstance;
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap();
    private final Map<Class<?>, CopyOnWriteArrayList<EvLeE11LLE>> subscriptionsByEventType;
    private final Map<Object, List<Class<?>>> typesBySubscriber;
    private final Map<Class<?>, Object> stickyEvents;
    private final ThreadLocal<EeleE> currentPostingThreadState;
    private final MainThreadSupport mainThreadSupport;
    private final Poster mainThreadPoster;
    private final org.greenrobot.eventbus.v1vLvLE backgroundPoster;
    private final org.greenrobot.eventbus.EvLveE1 asyncPoster;
    private final LvL11LLEl subscriberMethodFinder;
    private final ExecutorService executorService;
    private final boolean throwSubscriberException;
    private final boolean logSubscriberExceptions;
    private final boolean logNoSubscriberMessages;
    private final boolean sendSubscriberExceptionEvent;
    private final boolean sendNoSubscriberEvent;
    private final boolean eventInheritance;
    private final int indexCount;
    private final Logger logger;

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EventBus$EeleE.class */
    public static final class EeleE {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public final List<Object> f80EvLveE1 = new ArrayList();
        public boolean v1vLvLE;
        public boolean evleeEelE;
        public EvLeE11LLE EeleE;
        public Object LvL11LLEl;
        public boolean EvLeE11LLE;
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EventBus$EvLveE1.class */
    public class EvLveE1 extends ThreadLocal<EeleE> {
        public EvLveE1() {
        }

        @Override // java.lang.ThreadLocal
        /* renamed from: EvLveE1, reason: merged with bridge method [inline-methods] */
        public EeleE initialValue() {
            return new EeleE();
        }
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EventBus$evleeEelE.class */
    public interface evleeEelE {
        void EvLveE1(List<SubscriberExceptionEvent> list);
    }

    /* loaded from: DLEWSDK_2.8.4.aar:classes.jar:org/greenrobot/eventbus/EventBus$v1vLvLE.class */
    public static /* synthetic */ class v1vLvLE {

        /* renamed from: EvLveE1, reason: collision with root package name */
        public static final /* synthetic */ int[] f82EvLveE1;

        static {
            int[] iArr = new int[ThreadMode.values().length];
            f82EvLveE1 = iArr;
            try {
                iArr[ThreadMode.POSTING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f82EvLveE1[ThreadMode.MAIN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f82EvLveE1[ThreadMode.MAIN_ORDERED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f82EvLveE1[ThreadMode.BACKGROUND.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f82EvLveE1[ThreadMode.ASYNC.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Class<org.greenrobot.eventbus.EventBus>] */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v5 */
    public static EventBus getDefault() {
        EventBus eventBus;
        EventBus eventBus2 = defaultInstance;
        EventBus eventBus3 = eventBus2;
        if (eventBus2 == null) {
            ?? r0 = EventBus.class;
            synchronized (r0) {
                EventBus eventBus4 = defaultInstance;
                eventBus3 = eventBus4;
                if (eventBus4 == null) {
                    eventBus3 = eventBus;
                    eventBus = new EventBus();
                    defaultInstance = eventBus;
                }
                r0 = r0;
            }
        }
        return eventBus3;
    }

    public static EventBusBuilder builder() {
        return new EventBusBuilder();
    }

    public static void clearCaches() {
        LvL11LLEl.EvLveE1();
        eventTypesCache.clear();
    }

    public EventBus() {
        this(DEFAULT_BUILDER);
    }

    private void subscribe(Object obj, SubscriberMethod subscriberMethod) {
        Class<?> cls = subscriberMethod.eventType;
        EvLeE11LLE evLeE11LLE = new EvLeE11LLE(obj, subscriberMethod);
        CopyOnWriteArrayList<EvLeE11LLE> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        CopyOnWriteArrayList<EvLeE11LLE> copyOnWriteArrayList2 = copyOnWriteArrayList;
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList2 = r1;
            CopyOnWriteArrayList<EvLeE11LLE> copyOnWriteArrayList3 = new CopyOnWriteArrayList<>();
            this.subscriptionsByEventType.put(cls, copyOnWriteArrayList2);
        } else if (copyOnWriteArrayList2.contains(evLeE11LLE)) {
            throw new EventBusException("Subscriber " + obj.getClass() + " already registered to event " + cls);
        }
        int size = copyOnWriteArrayList2.size();
        for (int i = 0; i <= size; i++) {
            if (i == size || subscriberMethod.priority > copyOnWriteArrayList2.get(i).v1vLvLE.priority) {
                copyOnWriteArrayList2.add(i, evLeE11LLE);
                break;
            }
        }
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        List<Class<?>> list2 = list;
        if (list == null) {
            list2 = r1;
            ArrayList arrayList = new ArrayList();
            this.typesBySubscriber.put(obj, list2);
        }
        list2.add(cls);
        if (subscriberMethod.sticky) {
            if (!this.eventInheritance) {
                checkPostStickyEventToSubscription(evLeE11LLE, this.stickyEvents.get(cls));
                return;
            }
            for (Map.Entry<Class<?>, Object> entry : this.stickyEvents.entrySet()) {
                if (cls.isAssignableFrom(entry.getKey())) {
                    checkPostStickyEventToSubscription(evLeE11LLE, entry.getValue());
                }
            }
        }
    }

    private void checkPostStickyEventToSubscription(EvLeE11LLE evLeE11LLE, Object obj) {
        if (obj != null) {
            postToSubscription(evLeE11LLE, obj, isMainThread());
        }
    }

    private boolean isMainThread() {
        MainThreadSupport mainThreadSupport = this.mainThreadSupport;
        return mainThreadSupport == null || mainThreadSupport.isMainThread();
    }

    private void unsubscribeByEventType(Object obj, Class<?> cls) {
        CopyOnWriteArrayList<EvLeE11LLE> copyOnWriteArrayList = this.subscriptionsByEventType.get(cls);
        if (copyOnWriteArrayList != null) {
            int size = copyOnWriteArrayList.size();
            int i = 0;
            while (i < size) {
                EvLeE11LLE evLeE11LLE = copyOnWriteArrayList.get(i);
                if (evLeE11LLE.f78EvLveE1 == obj) {
                    evLeE11LLE.evleeEelE = false;
                    copyOnWriteArrayList.remove(i);
                    i--;
                    size--;
                }
                i++;
            }
        }
    }

    private void postSingleEvent(Object obj, EeleE eeleE) throws Error {
        Class<?> cls = obj.getClass();
        boolean z = false;
        if (this.eventInheritance) {
            List<Class<?>> lookupAllEventTypes = lookupAllEventTypes(cls);
            int size = lookupAllEventTypes.size();
            for (int i = 0; i < size; i++) {
                z |= postSingleEventForEventType(obj, eeleE, lookupAllEventTypes.get(i));
            }
        } else {
            z = postSingleEventForEventType(obj, eeleE, cls);
        }
        if (z) {
            return;
        }
        if (this.logNoSubscriberMessages) {
            this.logger.log(Level.FINE, "No subscribers registered for event " + cls);
        }
        if (!this.sendNoSubscriberEvent || cls == NoSubscriberEvent.class || cls == SubscriberExceptionEvent.class) {
            return;
        }
        post(new NoSubscriberEvent(this, obj));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [org.greenrobot.eventbus.EventBus] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v14, types: [org.greenrobot.eventbus.EventBus$EeleE] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v16, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.concurrent.CopyOnWriteArrayList] */
    private boolean postSingleEventForEventType(Object obj, EeleE eeleE, Class<?> cls) {
        ?? r0 = this;
        synchronized (r0) {
            r0 = (CopyOnWriteArrayList) r0.subscriptionsByEventType.get(cls);
            if (r0 == 0 || r0.isEmpty()) {
                return false;
            }
            Iterator it = r0.iterator();
            while (it.hasNext()) {
                ?? r02 = eeleE;
                EvLeE11LLE evLeE11LLE = (EvLeE11LLE) it.next();
                eeleE.LvL11LLEl = obj;
                eeleE.EeleE = evLeE11LLE;
                try {
                    postToSubscription(evLeE11LLE, obj, eeleE.evleeEelE);
                    r02 = r02.EvLeE11LLE;
                    eeleE.LvL11LLEl = null;
                    eeleE.EeleE = null;
                    eeleE.EvLeE11LLE = false;
                    if (r02 != 0) {
                        return true;
                    }
                } catch (Throwable th) {
                    th.LvL11LLEl = null;
                    th.EeleE = null;
                    th.EvLeE11LLE = false;
                    throw r02;
                }
            }
            return true;
        }
    }

    private void postToSubscription(EvLeE11LLE evLeE11LLE, Object obj, boolean z) {
        switch (v1vLvLE.f82EvLveE1[evLeE11LLE.v1vLvLE.threadMode.ordinal()]) {
            case 1:
                invokeSubscriber(evLeE11LLE, obj);
                return;
            case 2:
                if (z) {
                    invokeSubscriber(evLeE11LLE, obj);
                    return;
                } else {
                    this.mainThreadPoster.enqueue(evLeE11LLE, obj);
                    return;
                }
            case LogLevel.DEBUG /* 3 */:
                Poster poster = this.mainThreadPoster;
                if (poster != null) {
                    poster.enqueue(evLeE11LLE, obj);
                    return;
                } else {
                    invokeSubscriber(evLeE11LLE, obj);
                    return;
                }
            case 4:
                if (z) {
                    this.backgroundPoster.enqueue(evLeE11LLE, obj);
                    return;
                } else {
                    invokeSubscriber(evLeE11LLE, obj);
                    return;
                }
            case LogLevel.WARN /* 5 */:
                this.asyncPoster.enqueue(evLeE11LLE, obj);
                return;
            default:
                throw new IllegalStateException("Unknown thread mode: " + evLeE11LLE.v1vLvLE.threadMode);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.util.Map, java.util.Map<java.lang.Class<?>, java.util.List<java.lang.Class<?>>>] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.util.List<java.lang.Class<?>>] */
    private static List<Class<?>> lookupAllEventTypes(Class<?> cls) {
        ArrayList arrayList;
        ?? r0 = eventTypesCache;
        synchronized (r0) {
            List<Class<?>> list = (List) r0.get(cls);
            List<Class<?>> list2 = list;
            if (list == null) {
                list2 = arrayList;
                arrayList = new ArrayList();
                Class<?> cls2 = cls;
                while (true) {
                    Class<?> cls3 = cls2;
                    if (cls3 == null) {
                        break;
                    }
                    list2.add(cls3);
                    addInterfaces(list2, cls3.getInterfaces());
                    cls2 = cls3.getSuperclass();
                }
                eventTypesCache.put(cls, list2);
            }
            r0 = list2;
        }
        return r0;
    }

    public static void addInterfaces(List<Class<?>> list, Class<?>[] clsArr) {
        for (Class<?> cls : clsArr) {
            if (!list.contains(cls)) {
                list.add(cls);
                addInterfaces(list, cls.getInterfaces());
            }
        }
    }

    private void handleSubscriberException(EvLeE11LLE evLeE11LLE, Object obj, Throwable th) {
        if (obj instanceof SubscriberExceptionEvent) {
            if (this.logSubscriberExceptions) {
                Logger logger = this.logger;
                Level level = Level.SEVERE;
                logger.log(level, "SubscriberExceptionEvent subscriber " + evLeE11LLE.f78EvLveE1.getClass() + " threw an exception", th);
                SubscriberExceptionEvent subscriberExceptionEvent = (SubscriberExceptionEvent) obj;
                this.logger.log(level, "Initial event " + subscriberExceptionEvent.causingEvent + " caused exception in " + subscriberExceptionEvent.causingSubscriber, subscriberExceptionEvent.throwable);
                return;
            }
            return;
        }
        if (this.throwSubscriberException) {
            throw new EventBusException("Invoking subscriber failed", th);
        }
        if (this.logSubscriberExceptions) {
            this.logger.log(Level.SEVERE, "Could not dispatch event: " + obj.getClass() + " to subscribing class " + evLeE11LLE.f78EvLveE1.getClass(), th);
        }
        if (this.sendSubscriberExceptionEvent) {
            post(new SubscriberExceptionEvent(this, th, obj, evLeE11LLE.f78EvLveE1));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v8 */
    public void register(Object obj) {
        if (AndroidDependenciesDetector.isAndroidSDKAvailable() && !AndroidDependenciesDetector.areAndroidComponentsAvailable()) {
            throw new RuntimeException("It looks like you are using EventBus on Android, make sure to add the \"eventbus\" Android library to your dependencies.");
        }
        ?? EvLveE12 = this.subscriberMethodFinder.EvLveE1(obj.getClass());
        synchronized (this) {
            Iterator it = EvLveE12.iterator();
            while (it.hasNext()) {
                subscribe(obj, (SubscriberMethod) it.next());
            }
            EvLveE12 = this;
        }
    }

    public synchronized boolean isRegistered(Object obj) {
        return this.typesBySubscriber.containsKey(obj);
    }

    public synchronized void unregister(Object obj) {
        List<Class<?>> list = this.typesBySubscriber.get(obj);
        if (list == null) {
            this.logger.log(Level.WARNING, "Subscriber to unregister was not registered before: " + obj.getClass());
            return;
        }
        Iterator<Class<?>> it = list.iterator();
        while (it.hasNext()) {
            unsubscribeByEventType(obj, it.next());
        }
        this.typesBySubscriber.remove(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Throwable] */
    public void post(Object obj) {
        EeleE eeleE = this.currentPostingThreadState.get();
        List<Object> list = eeleE.f80EvLveE1;
        list.add(obj);
        if (eeleE.v1vLvLE) {
            return;
        }
        eeleE.evleeEelE = isMainThread();
        eeleE.v1vLvLE = true;
        boolean z = eeleE.EvLeE11LLE;
        ?? r0 = z;
        if (z) {
            throw new EventBusException("Internal error. Abort state was not reset");
        }
        while (true) {
            try {
                r0 = list.isEmpty();
                if (r0 != 0) {
                    eeleE.v1vLvLE = false;
                    eeleE.evleeEelE = false;
                    return;
                } else {
                    EventBus eventBus = this;
                    eventBus.postSingleEvent(list.remove(0), eeleE);
                    r0 = eventBus;
                }
            } catch (Throwable th) {
                th.v1vLvLE = false;
                th.evleeEelE = false;
                throw r0;
            }
        }
    }

    public void cancelEventDelivery(Object obj) {
        EeleE eeleE = this.currentPostingThreadState.get();
        if (!eeleE.v1vLvLE) {
            throw new EventBusException("This method may only be called from inside event handling methods on the posting thread");
        }
        if (obj == null) {
            throw new EventBusException("Event may not be null");
        }
        if (eeleE.LvL11LLEl != obj) {
            throw new EventBusException("Only the currently handled event may be aborted");
        }
        if (eeleE.EeleE.v1vLvLE.threadMode != ThreadMode.POSTING) {
            throw new EventBusException(" event handlers may only abort the incoming event");
        }
        eeleE.EvLeE11LLE = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void postSticky(Object obj) {
        synchronized (this.stickyEvents) {
            this.stickyEvents.put(obj.getClass(), obj);
        }
        post(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    public <T> T getStickyEvent(Class<T> cls) {
        ?? r0 = (T) cls;
        synchronized (this.stickyEvents) {
            r0 = (T) r0.cast(this.stickyEvents.get(cls));
        }
        return r0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Class] */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r0v2, types: [T, java.lang.Object] */
    public <T> T removeStickyEvent(Class<T> cls) {
        ?? r0 = (T) cls;
        synchronized (this.stickyEvents) {
            r0 = (T) r0.cast(this.stickyEvents.remove(cls));
        }
        return r0;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Throwable, java.util.Map<java.lang.Class<?>, java.lang.Object>] */
    public void removeAllStickyEvents() {
        synchronized (this.stickyEvents) {
            this.stickyEvents.clear();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.util.concurrent.CopyOnWriteArrayList] */
    /* JADX WARN: Type inference failed for: r0v8, types: [org.greenrobot.eventbus.EventBus] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Throwable] */
    public boolean hasSubscriberForEvent(Class<?> cls) {
        List<Class<?>> lookupAllEventTypes = lookupAllEventTypes(cls);
        if (lookupAllEventTypes == null) {
            return false;
        }
        int size = lookupAllEventTypes.size();
        for (int i = 0; i < size; i++) {
            ?? r0 = this;
            Class<?> cls2 = lookupAllEventTypes.get(i);
            synchronized (r0) {
                r0 = (CopyOnWriteArrayList) r0.subscriptionsByEventType.get(cls2);
                if (r0 != 0 && !r0.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    public void invokeSubscriber(org.greenrobot.eventbus.evleeEelE evleeeele) {
        Object obj = evleeeele.f85EvLveE1;
        EvLeE11LLE evLeE11LLE = evleeeele.v1vLvLE;
        org.greenrobot.eventbus.evleeEelE.EvLveE1(evleeeele);
        if (evLeE11LLE.evleeEelE) {
            invokeSubscriber(evLeE11LLE, obj);
        }
    }

    public ExecutorService getExecutorService() {
        return this.executorService;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public String toString() {
        return "EventBus[indexCount=" + this.indexCount + ", eventInheritance=" + this.eventInheritance + "]";
    }

    public EventBus(EventBusBuilder eventBusBuilder) {
        this.currentPostingThreadState = new EvLveE1();
        this.logger = eventBusBuilder.getLogger();
        this.subscriptionsByEventType = new HashMap();
        this.typesBySubscriber = new HashMap();
        this.stickyEvents = new ConcurrentHashMap();
        MainThreadSupport mainThreadSupport = eventBusBuilder.getMainThreadSupport();
        this.mainThreadSupport = mainThreadSupport;
        this.mainThreadPoster = mainThreadSupport != null ? mainThreadSupport.createPoster(this) : null;
        this.backgroundPoster = new org.greenrobot.eventbus.v1vLvLE(this);
        this.asyncPoster = new org.greenrobot.eventbus.EvLveE1(this);
        List<SubscriberInfoIndex> list = eventBusBuilder.subscriberInfoIndexes;
        this.indexCount = list != null ? list.size() : 0;
        this.subscriberMethodFinder = new LvL11LLEl(eventBusBuilder.subscriberInfoIndexes, eventBusBuilder.strictMethodVerification, eventBusBuilder.ignoreGeneratedIndex);
        this.logSubscriberExceptions = eventBusBuilder.logSubscriberExceptions;
        this.logNoSubscriberMessages = eventBusBuilder.logNoSubscriberMessages;
        this.sendSubscriberExceptionEvent = eventBusBuilder.sendSubscriberExceptionEvent;
        this.sendNoSubscriberEvent = eventBusBuilder.sendNoSubscriberEvent;
        this.throwSubscriberException = eventBusBuilder.throwSubscriberException;
        this.eventInheritance = eventBusBuilder.eventInheritance;
        this.executorService = eventBusBuilder.executorService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean removeStickyEvent(Object obj) {
        synchronized (this.stickyEvents) {
            Class<?> cls = obj.getClass();
            if (!obj.equals(this.stickyEvents.get(cls))) {
                return false;
            }
            this.stickyEvents.remove(cls);
            return true;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: PHI empty after try-catch fix!
        	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiInTryCatch(SSATransform.java:222)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixLastAssignInTry(SSATransform.java:202)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:58)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:44)
        */
    public void invokeSubscriber(
    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: PHI empty after try-catch fix!
        	at jadx.core.dex.visitors.ssa.SSATransform.fixPhiInTryCatch(SSATransform.java:222)
        	at jadx.core.dex.visitors.ssa.SSATransform.fixLastAssignInTry(SSATransform.java:202)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:58)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r8v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:238)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:223)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:168)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:401)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:335)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:301)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
        	at java.base/java.util.stream.ReferencePipeline$7$1FlatMap.end(Unknown Source)
        	at java.base/java.util.stream.AbstractPipeline.copyInto(Unknown Source)
        	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(Unknown Source)
        	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(Unknown Source)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(Unknown Source)
        	at java.base/java.util.stream.AbstractPipeline.evaluate(Unknown Source)
        	at java.base/java.util.stream.ReferencePipeline.forEach(Unknown Source)
        	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:297)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:286)
        	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:270)
        	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:161)
        	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:103)
        	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:45)
        	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:34)
        	at jadx.core.codegen.CodeGen.generate(CodeGen.java:22)
        	at jadx.core.ProcessClass.process(ProcessClass.java:79)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:117)
        	at jadx.core.dex.nodes.ClassNode.generateClassCode(ClassNode.java:402)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:390)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:310)
        */
}