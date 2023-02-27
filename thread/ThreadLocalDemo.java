package space.jachen.juc.thread;

import java.util.concurrent.*;

/**
 * ThreadLocal
 *
 * @author JaChen
 * @date 2023/2/27 20:46
 */
public class ThreadLocalDemo {

    // 设置初始值
    ThreadLocal<Integer> num = ThreadLocal.withInitial(()->0);

    /**
     * 自增并输出num
     */
    public void inCreat(){
        Integer myNum = num.get();
        myNum++;
        System.out.println(Thread.currentThread().getName()+"\tmyNum = " + myNum);
        num.set(myNum);
    }

}

class Test{

    public static void main(String[] args) {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                2, 4, 30, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(),
                (Runnable r, ThreadPoolExecutor executor)-> System.out.println("My拒绝策略")
        );
        ThreadLocalDemo localDemo = new ThreadLocalDemo();
        try {
            poolExecutor.execute(()->{
                while (true){
                    localDemo.inCreat();
                }
            });
            poolExecutor.execute(()->{
                while (true){
                    localDemo.inCreat();
                }
            });
        }finally {
            poolExecutor.shutdown();
        }
    }
}
