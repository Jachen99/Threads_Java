package space.jachen.juc.test;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：两个线程打印
 * 两个线程，一个线程打印1-52，另一个打印字母A-Z
 * 打印顺序为12A34B  5152Z,要求用线程间通信
 *
 * @author JaChen
 * @date 2023/2/27 8:59
 */
public class PrintData{
    public static void main(String[] args) {

        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(3);  // 阻塞队列
        /*
         * @线程池参数说明：
         * corePoolSize – the number of threads to keep in the pool, even if they are idle, unless allowCoreThreadTimeOut is set
         * maximumPoolSize – the maximum number of threads to allow in the pool
         * keepAliveTime – when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating.
         * unit – the time unit for the keepAliveTime argument
         * workQueue – the queue to use for holding tasks before they are executed. This queue will hold only the Runnable tasks submitted by the execute method.
         * threadFactory – the factory to use when the executor creates a new thread
         * handler – the handler to use when execution is blocked because the thread bounds and queue capacities are reached
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(  // 自定义线程池
                2,4,30, TimeUnit.SECONDS,
                blockingQueue,Executors.defaultThreadFactory(),
                //(Runnable r, ThreadPoolExecutor executor)-> System.out.println("自定义拒绝策略")
                new ThreadPoolExecutor.AbortPolicy()
        );

        Letter letter = new Letter();
        try {
            poolExecutor.execute(()->{
                for (int i = 0; i < 26; i++) {  // 每个循环执行两次打印 26*2 = 52
                    letter.One();
                }
            });
            poolExecutor.execute(()->{
                for (int i = 0; i < 26; i++) {
                    letter.Two();
                }
            });
        } finally {
            poolExecutor.shutdown();
        }
    }
}

class Letter{

    final Lock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    private int flag = 1;  // 1 - 打印数字；2 - 打印字母
    private static int k = 0;  // 记录打印数字
    private static char c = 'A';  // 记录打印字母
    private static int num = 0;  // 记录打印数字或字母个数  最大值为6

    // 打印顺序为12A34B  5152Z,要求用线程间通信
    public void One() {
        lock.lock();
        try {
            while (flag != 1){
                c1.await();
            }
            for (int j = 0; j < 2; j++) {
                k++;
                num++;
                System.out.print(k);
                if (num % 6 == 0) System.out.println();
            }
            flag = 2;
            c2.signalAll();  // 唤醒c2
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void Two() {
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            num++;
            System.out.print(c);
            c = (char) (c + 1);
            flag = 1;
            c1.signalAll();
            if (num % 6 == 0) System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}