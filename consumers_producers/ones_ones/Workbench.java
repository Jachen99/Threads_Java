package the_advanced.consumers_producers.ones_one;

public class Workbench {
//    private static final int MAX_VALUE = 5;
    private static final int MAX_VALUE = 1;//故意把最大值修改为1，让问题暴露的明显1点
    private int total;

    public synchronized void put(){
        //如果被唤醒后，total不满足条件，不会继续循环，结束循环执行下面的代码
       while(total >= MAX_VALUE){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total++;
        System.out.println(Thread.currentThread().getName()
                + "做好一份菜，剩余：" + total);
//        notify();//默认省略了this.，这里this是同步锁对象才行  //唤醒等待的线程
        notifyAll();
    }

    //非静态方法，同步锁对象就是this
    public synchronized void take(){
        while(total<=0){
            try {
                wait();//默认省略了this.，这里this是同步锁对象才行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        total--;
        System.out.println(Thread.currentThread().getName()
                + "取走一份菜，剩余：" + total);
        notifyAll();
    }
}
