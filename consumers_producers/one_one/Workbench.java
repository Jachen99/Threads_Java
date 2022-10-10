package the_advanced.consumers_producers.one_one;

public class Workbench {
    private static final int MAX_VALUE = 5;
    private int total;

    public synchronized void put(){
        if(total >= MAX_VALUE){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total++;
        System.out.println(Thread.currentThread().getName() + "做好一份菜，剩余：" + total);
        notify();//默认省略了this.，这里this是同步锁对象才行  //唤醒等待的线程
    }

    //非静态方法，同步锁对象就是this
    public synchronized void take(){
        if(total<=0){
            try {
                wait();//默认省略了this.，这里this是同步锁对象才行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total--;
        System.out.println(Thread.currentThread().getName() + "取走一份菜，剩余：" + total);
        notify();//唤醒等待的线程
    }
}
