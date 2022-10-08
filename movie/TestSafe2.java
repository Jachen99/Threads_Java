package the_advanced.threads.movie;

/**
 * @ClassName TestSafe2
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 19:28
 * @Version 1.0
 *
 * 静态方法
 */

public class TestSafe2 {
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
    private static int total = 1000;//静态变量可以被所有对象共享
    private static Object lock = new Object();

    @Override
    public void run() {
        while(total > 0){
            saleOneTicket();
        }
    }

    //非静态方法：this
    //静态方法：当前类的Class对象，都是WindowThread类，Class对象是同一个
    private synchronized static void saleOneTicket(){
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