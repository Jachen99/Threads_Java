package the_advanced.threads.movie.unlocked;

/**
 * @ClassName version4
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 16:57
 * @Version 1.0
 *
 * 同一个对象的实例变量共享
 *
 * 多个Thread线程使用同一个Runnable对象
 * 结果：发现卖出近100张票。
 * 问题：但是有重复票或负数票问题。
 * 原因：线程安全问题
 */
public class version4 {
    public static void main(String[] args) {
        TicketSaleRunnable tr = new TicketSaleRunnable();
        // 一个对象调用三个线程
        Thread t1 = new Thread(tr, "窗口一");
        Thread t2 = new Thread(tr, "窗口二");
        Thread t3 = new Thread(tr, "窗口三");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketSaleRunnable implements Runnable {
    private int total = 100;
    public void run() {
        while (total > 0) {
            try {
                Thread.sleep(100);//加入这个，使得问题暴露的更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余:" + --total);
        }
    }
}
