package com.example.zetatask;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    // For Singleton instantiation
    private static final Object LOCK = new Object();
    private static AppExecutors sInstance;
    private final Executor mainThread;
    private final Executor networkIO;
    private final Executor otherTask;


    private AppExecutors(Executor networkIO, Executor mainThread,
                         Executor other) {
        this.networkIO = networkIO;
        this.mainThread = mainThread;
        this.otherTask = other;
    }

    public static AppExecutors getInstance() {
        if (sInstance == null) {
            synchronized (LOCK) {
                if(sInstance == null) {
                    sInstance = new AppExecutors(
                            Executors.newFixedThreadPool(2),
                            new MainThreadExecutor(),
                            Executors.newFixedThreadPool(5));
                }
            }
        }
        return sInstance;
    }

    public Executor mainThread() {
        return mainThread;
    }

    public Executor networkIO() {
        return networkIO;
    }

    public Executor computation() {
        return otherTask;
    }


    private static class MainThreadExecutor implements Executor {
        private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(@NonNull Runnable command) {
            mainThreadHandler.post(command);
        }
    }
}
