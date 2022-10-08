package the_advanced.threads.movie.locked;

/**
 * @ClassName version1
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 17:28
 * @Version 1.0
 *
 * 加锁的范围不对 引起只有一个线程在执行任务
 */
public class version1 {
    public static void main(String[] args) {
        MyRunnable my = new MyRunnable();//创建1个MyRunnable的对象
        Thread t1 = new Thread(my);
        Thread t2 = new Thread(my);
        Thread t3 = new Thread(my);

        t1.start();
        t2.start();
        t3.start();
    }
}

class MyRunnable implements Runnable {
    private int total = 100;

    @Override
    public void run() {
        // 加锁 只有一个线程进去 所有票卖完才可以结束
        synchronized (this) {
            while (total > 0) {
                try {
                    Thread.sleep(10);//为了让问题暴露明显一点
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                total--;
                System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余：" + total);
            }
        }
    }
}