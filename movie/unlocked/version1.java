package the_advanced.threads.movie.unlocked;

/**
 * @ClassName version1
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 16:39
 * @Version 1.0
 *
 * 局部变量不能共享
 * 存在问题：
 *      局部变量是每次调用方法都是独立的，那么每个线程的run()的total是独立的，不是共享数据。
 *      我们的需求是多个线程共卖出100个票。
 */
public class version1 {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();

        w1.start();
        w2.start();
        w3.start();
    }
}
class Window extends Thread {
    /**
     * @param :
     * @return void  卖出300张票。
     */
    public void run() {
        // total不能被共享
        int total = 100;
        while (total > 0) {
            System.out.println(getName() + "卖出一张票，剩余:" + --total);
        }
    }
}
