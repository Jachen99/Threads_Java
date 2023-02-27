package space.jachen.juc.lock.myLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 重写自己的Lock锁
 * 1、实现上锁
 * 2、实现可重入
 *
 * @author JaChen
 * @date 2023/2/26 17:08
 */
public class MyLock implements Lock {

    private Thread hasLockThread = null;   // 持有锁的线程

    private boolean hasLock = false;  // 是否持有锁

    private int reentryCount = 0;  // 可重入次数

    /**
     * 同一时刻，能且仅能有一个线程获取到锁，其他线程，只能等待该线程释放锁之后才能获取到锁
     */
    @Override
    synchronized
    public void lock() {
        if (hasLock && Thread.currentThread() != hasLockThread){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hasLockThread = Thread.currentThread();
        hasLock = true;
        reentryCount++;
    }

    @Override
    synchronized
    public void unlock() {
        // 判断当前线程是否是持有锁的线程  如果是  可重入次数减一、
        if (Thread.currentThread() == hasLockThread) {
            reentryCount--;
            if (reentryCount == 0){
                // 随机唤醒一个锁
                notify();
                hasLock = false;
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
