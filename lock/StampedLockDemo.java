package space.jachen.juc.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

/**
 * 邮戳锁 StampedLock
 * --------------------
 * 背景：
 * 1.8之前，锁已经那么多了，为什么还要有StampedLock?
 * 一般应用，都是读多写少，ReentrantReadWriteLock因读写互斥，故读时阻塞军，因而性能上上不去。可能会使写线程饥饿
 * ------------------------------------------------------------------------------------------
 * StampedLock的特点：
 * 所有获取锁的方法，都返回一个邮戳(Stamp)，Stamp为0表示获取失败，其余都表示成功；
 * 所有释放锁的方法，都需要一个邮戳(Stamp)，这个Stamp必须是和成功获取锁时得到的Stamp一致；
 * StampedLock.是不可重入的；（如果一个线程已经持有了写锁，再去获取写锁的话就会造成死锁）
 * 支持锁升级跟锁降级
 * 可以乐观读也可以悲观读
 * 使用有限次自旋，增加锁获得的几率，避免上下文切换带来的开销
 * 乐观读不阻塞写操作，悲观读，阻塞写得操作
 * ----------------------------------------------------------------------
 * StampedLock的优点：
 * 相此于ReentrantReadWriteLock,吞吐量大幅提升
 * ----------------------------------------------------------------------
 * StampedLock的缺点：
 * api相对复杂，容易用错
 * 内部实现相比于ReentrantReadWriteLock复杂得多
 * -------------------------------------------
 * StampedLock的原理：
 * 每次获取锁的时候，都会返回一个邮戳(stamp),相当于mysql里的version字段
 * 释放锁的时候，再根据之前的获得的邮戳，去进行锁释放
 *
 * @author JaChen
 * @date 2023/2/25 16:39
 */
class MyCache1{

    // ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final StampedLock rel  = new StampedLock();
    private volatile Map<String, String> cache= new HashMap<>();

    public void put(String key, String value){
        long writeLock = rel.writeLock();
        try {
            System.out.println(Thread.currentThread().getName() + " 开始写入！");
            Thread.sleep(300);
            cache.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写入成功！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rel.unlockWrite(writeLock);
        }
    }

    /**
     * 如果使用乐观读，一定要判断返回的邮戳是否是一开始获得到的，
     * 如果不是，要去获取悲观读锁，再次去读取
     * @param key  num
     */
    public void get(String key){
        // 乐观读
        long readLock = rel.tryOptimisticRead();
        // 检查是否被其他线程抢占资源 被抢占返回false
        while (!rel.validate(readLock)){
            // 如果被抢占则进行悲观的读
            readLock = rel.readLock();
            try {
                System.out.println(Thread.currentThread().getName() + " 开始读出！");
                Thread.sleep(300);
                String value = cache.get(key);
                System.out.println(Thread.currentThread().getName() + " 读出成功！" + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                rel.unlockRead(readLock);
            }
        }
    }
}

public class StampedLockDemo {

    public static void main(String[] args) {

        MyCache1 cache = new MyCache1();

        for (int i = 1; i <= 5; i++) {
            String num = String.valueOf(i);
            // 开启5个写线程
            new Thread(()->{
                cache.put(num, num);
            }, num).start();
        }
        for (int i = 1; i <= 5; i++) {
            String num = String.valueOf(i);
            // 开启5个读线程
            new Thread(()->{
                cache.get(num);
            }, num).start();
        }
    }
}