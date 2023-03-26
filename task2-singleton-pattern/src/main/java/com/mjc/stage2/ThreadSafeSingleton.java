package com.mjc.stage2;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeSingleton {
    
    private static ThreadSafeSingleton INSTANCE;

    private static final ReentrantLock INSTANCE_LOCK = new ReentrantLock();

    private ThreadSafeSingleton() {
    }

    public static ThreadSafeSingleton getInstance() {
        ThreadSafeSingleton localInstance = INSTANCE;
        if (localInstance == null) {
            INSTANCE_LOCK.lock();
            try {
                localInstance = INSTANCE;
                if (localInstance == null) {
                    localInstance = INSTANCE = new ThreadSafeSingleton();
                }
            } finally {
                INSTANCE_LOCK.unlock();
            }
        }
        return localInstance;
    }
}
