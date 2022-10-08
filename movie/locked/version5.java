package the_advanced.threads.movie.locked;

/**
 * @ClassName version5
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 19:19
 * @Version 1.0
 *
 * 正确的锁
 * 其他对象
 */
public class version5 {
    public static void main(String[] args) {
        WThread w1 = new WThread();
        WThread w2 = new WThread();
        WThread w3 = new WThread();

        w1.start();
        w2.start();
        w3.start();
    }
}

class WThread extends Thread{
    private static int total = 1000;//静态变量可以被所有对象共享
    private static Object lock = new Object();

    @Override
    public void run() {
        while(true){
            synchronized (lock) {
                if (total > 0) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    total--;
                    System.out.println(getName() + "卖出一张票，剩余：" + total);
                } else {
                    break;
                }
            }
        }
    }
}