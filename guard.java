package the_advanced.threads.code;

/**
 * @ClassName guard
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 15:20
 * @Version 1.0
 *
 * 有一种线程，它是在后台运行的，它的任务是为其他线程提供服务的，这种线程被称为“守护线程”。JVM的垃圾回收线程就是典型的守护线程。
 *
 * 守护线程有个特点，就是如果所有非守护线程都死亡，那么守护线程自动死亡。
 *
 * 调用setDaemon(true)方法可将指定线程设置为守护线程。必须在线程启动之前设置，否则会报IllegalThreadStateException异常。
 *
 * 调用isDaemon()可以判断线程是否是守护线程。
 */
public class guard {
    public static void main(String[] args) {
        MyDaemon m = new MyDaemon();
        m.setDaemon(true);//必须在启动前。
        m.start();

        for (int i = 1; i <= 100; i++) {
            System.out.println("main线程:" + i);
        }
    }
}

class MyDaemon extends Thread {
    public void run() {
        while (true) {
            System.out.println("我一直守护者你...");
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
