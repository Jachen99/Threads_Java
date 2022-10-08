package the_advanced.threads.movie.locked;

/**
 * @ClassName version3
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 17:48
 * @Version 1.0
 *
 * 锁不对   静态变量可以被所有对象共享
 */
public class version3 {
    public static void main(String[] args) {
        WindowThread w1 = new WindowThread();
        WindowThread w2 = new WindowThread();
        WindowThread w3 = new WindowThread();

        w1.start();
        w2.start();
        w3.start();
    }
}

class WindowThread extends Thread{
    private static int total = 100;//静态变量可以被所有对象共享

    @Override
    public void run() {
        while(total > 0){
            saleOneTicket();
        }
    }

    private synchronized void saleOneTicket(){

        if (total > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            total--;
            System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余：" + total);
        }
    }
}