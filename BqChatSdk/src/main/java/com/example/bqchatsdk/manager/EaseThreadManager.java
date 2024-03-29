package com.example.bqchatsdk.manager;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Thread manager
 */
public class EaseThreadManager {
    private static volatile EaseThreadManager instance;
    private Executor mIOThreadExecutor;
    private Handler mMainThreadHandler;

    private EaseThreadManager() { init();}

    public static EaseThreadManager getInstance() {
        if(instance == null) {
            synchronized (EaseThreadManager.class) {
                if(instance == null) {
                    instance = new EaseThreadManager();
                }
            }
        }
        return instance;
    }

    private void init() {
        int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();
        int KEEP_ALIVE_TIME = 1;
        TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;
        BlockingQueue<Runnable> taskQueue = new LinkedBlockingDeque<>();
        mIOThreadExecutor = new ThreadPoolExecutor(NUMBER_OF_CORES,
                NUMBER_OF_CORES * 2,
                                KEEP_ALIVE_TIME,
                                KEEP_ALIVE_TIME_UNIT,
                                taskQueue,
                                new BackgroundThreadFactory(Process.THREAD_PRIORITY_BACKGROUND));
        mMainThreadHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Switch to an asynchronous thread
     * @param runnable
     */
    public void runOnIOThread(Runnable runnable) {
        mIOThreadExecutor.execute(runnable);
    }

    /**
     * Switch to the UI thread
     * @param runnable
     */
    public void runOnMainThread(Runnable runnable) {
        mMainThreadHandler.post(runnable);
    }

    /**
     * Determine if it is the main thread
     * @return true is main thread
     */
    public boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
