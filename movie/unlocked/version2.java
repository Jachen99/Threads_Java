package the_advanced.threads.movie.unlocked;

/**
 * @ClassName version2
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 16:48
 * @Version 1.0
 *
 * 不同对象的实例变量不共享
 */
public class version2 {
    public static void main(String[] args) {
        TicketSale t1 = new TicketSale();
        TicketSale t2 = new TicketSale();
        TicketSale t3 = new TicketSale();

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketSale extends Thread {
    // 不同的实例对象的实例变量是独立的
    private int total = 100;

    public void run() {
        while (total > 0) {
            System.out.println(getName() + "卖出一张票，剩余:" + --total);
        }
    }
}