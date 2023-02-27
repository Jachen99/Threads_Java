package space.jachen.juc.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 《定制化通信》
 * 多线程之间按顺序调用，实现AA->BB->CC
 * 三个线程启动，要求如下：
 * AA打印5次，BB打印1次，CC打印15次
 * 接着
 * AA打印5次，BB打印18次，CC打印15次
 * .....来10轮
 *
 * @author JaChen
 * @date 2023/2/27 9:00
 */
class ABC{

    private int flag = 1; // 1:ThreadA；2:ThreadB；3:ThreadC；
    private final Lock lock = new ReentrantLock();  // 获取lock对象
    final Condition condition1 = lock.newCondition();  // 获取锁的条件
    final Condition condition2 = lock.newCondition();  // 获取锁的条件
    final Condition condition3 = lock.newCondition();  // 获取锁的条件

    public void ThreadA(){
        lock.lock();
        try {
            while (flag != 1){
                condition1.await();
            }
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+'\t'+(i+1));
            }
            flag = 2;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
    public void ThreadB(){
        lock.lock();
        try {
            while (flag != 2){
                condition2.await();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+'\t'+(i+1));
            }
            flag = 3;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }
    public void ThreadC(){
        lock.lock();
        try {
            while (flag != 3){
                condition3.await();
            }
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName()+'\t'+(i+1));
            }
            flag = 1;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
    }

}


public class Demo {

    public static void main(String[] args) {
        ABC abc = new ABC();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                abc.ThreadA();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                abc.ThreadB();
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                abc.ThreadC();
            }
        }).start();
    }
}