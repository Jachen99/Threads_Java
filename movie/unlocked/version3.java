package the_advanced.threads.movie.unlocked;

/**
 * @ClassName version3
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 16:49
 * @Version 1.0
 *
 * 结果：发现卖出近100张票。
 *
 * 问题（1）：但是有重复票或负数票问题。
 * 原因：线程安全问题
 *
 * 问题（2）：如果要考虑有两场电影，各卖100张票等
 * 原因：TicketThread类的静态变量，是所有TicketThread类的对象共享
 */
public class version3 {
    public static void main(String[] args) {
        TicketSaleThread t1 = new TicketSaleThread();
        TicketSaleThread t2 = new TicketSaleThread();
        TicketSaleThread t3 = new TicketSaleThread();

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketSaleThread extends Thread{

    // TicketThread类的静态变量，是所有TicketThread类的对象共享
    private static int total = 100;
    public void run(){
        while(total>0) {
            try {
                Thread.sleep(10);//加入这个，使得问题暴露的更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "卖出一张票，剩余:" + --total);
        }
    }
}