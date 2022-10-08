package the_advanced.threads.movie.locked;

/**
 * @ClassName version2
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 17:34
 * @Version 1.0
 *
 * 锁对象不对   三个对象了
 */

public class version2 {
    public static void main(String[] args) {
        MyThread m1 = new MyThread();
        MyThread m2 = new MyThread();
        MyThread m3 = new MyThread();

        m1.start();
        m2.start();
        m3.start();
    }
}

class MyThread extends Thread{
    private static int total = 1000;//静态变量可以被所有对象共享

    @Override
    public void run() {
        while(true){
            synchronized (this) {//这里有3个this
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