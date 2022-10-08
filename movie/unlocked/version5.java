package the_advanced.threads.movie.unlocked;

/**
 * @ClassName version5
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/8 17:07
 * @Version 1.0
 *
 * 抽取资源类，共享同一个资源对象
 */
public class version5 {
    public static void main(String[] args) {
        //2、创建资源对象
        Ticket ticket = new Ticket();

        //3、启动多个线程操作资源类的对象
        Thread t1 = new Thread("窗口一") {
            public void run() {
                while (true) {
                    ticket.sale();
                }
            }
        };
        Thread t2 = new Thread("窗口二") {
            public void run() {
                while (true) {
                    ticket.sale();
                }
            }
        };
        Thread t3 = new Thread(new Runnable() {
            public void run() {
                ticket.sale();
            }
        }, "窗口三");


        t1.start();
        t2.start();
        t3.start();
    }
}

//1、编写资源类
class Ticket {
    private int total = 100;

    public void sale() {
        if (total > 0) {
            try {
                Thread.sleep(200);//加入这个，使得问题暴露的更明显
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "卖出一张票，剩余:" + --total);
        } else {
            throw new RuntimeException("没有票了");
        }
    }

    public int getTotal() {
        return total;
    }
}