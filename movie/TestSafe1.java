package the_advanced.threads.movie;

/**
 * @ClassName TestSafe1
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 19:24
 * @Version 1.0
 *
 * 非静态方法
 */

public class TestSafe1 {
    public static void main(String[] args) {
        WindowRunnable w = new WindowRunnable();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.start();
        t2.start();
        t3.start();
    }
}

class WindowRunnable implements Runnable {
    private int total = 100;

    @Override
    public void run() {
        while (total > 0) {
            saleOneTicket();
        }
    }

    //这里的锁对象是谁？
    //非静态方法：this
    private synchronized void saleOneTicket() {
        if (total > 0) {
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