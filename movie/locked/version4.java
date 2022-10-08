package the_advanced.threads.movie.locked;

/**
 * @ClassName version4
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 18:14
 * @Version 1.0
 *
 * 正确的锁
 * this对象（不是所有this都可以）
 */
public class version4 {
    public static void main(String[] args) {
        WindowRunnable w  =new WindowRunnable();

        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);
        Thread t3 = new Thread(w);

        t1.start();
        t2.start();
        t3.start();
    }
}
class WindowRunnable implements Runnable{
    private int total = 1000;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {//this是WindowRunnable的对象
                if (total > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    total--;
                    System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余：" + total);
                } else {
                    break;
                }
            }
        }
    }
}