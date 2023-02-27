package space.jachen.juc.thread;

/**
 *
 * 创建线程的方式
 * lambda表达式
 *
 * @author JaChen
 * @date 2023/2/25 9:24
 */
public class Demo {
    public static void main(String[] args) {

        // 1、继承Tread类
        new Thread().start();

        // 2.1  方式一  实现Runnable接口
        new Thread(new MyThreadRunnable(),"Thread-ccc").start();

        // 2.2 方式二  匿名内部类实现
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("匿名内部类实现:"+Thread.currentThread().getName());
            }
        }).start();

        // 2.3   方式三  lambda表达式
        new Thread(()->{
            System.out.println("lambda表达式:"+Thread.currentThread().getName());
        },"Thread-aaa").start();
    }
}

// 1、继承Tread类
class MyThread extends Thread{
}

// 2、实现Runnable接口
// 2.1  方式一  直接实现接口
class MyThreadRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("实现Runnable接口:"+Thread.currentThread().getName());
    }
}
