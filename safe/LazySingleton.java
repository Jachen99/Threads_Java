package space.jachen.juc.safe;

/**
 * 懒汉单例  线程不安全  要加锁
 *
 * 如何避免线程安全性问题
 * 线程安全性问题成因
 * 1:多线程环境
 * 2:多个线程操作同一共享资源
 * 3:对该共享资源进行了非原子性操作
 * 如何避免
 * 打破成因中三点任意一点
 * 1:多线程环境-将多线程改单线程（必要的代码，加锁访问)
 * 2:多个线程操作同一共享资源--不共享资源(ThreadLocal、不共享、操作无状态化、不可变)
 * 3：对该共享资源进行了非原子性操作~·将非原子性操作改成原子性操作（加锁、使用D水自带的原子性操作的类、UC提供的相应的并发工具类)
 *
 * @author JaChen
 * @date 2023/2/26 16:14
 */
public class LazySingleton {

    private static volatile LazySingleton ourInstance = null;

    private LazySingleton(){}  // 私有化构造方法

    public static LazySingleton getInstance(){
        if (null == ourInstance){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized(LazySingleton.class){
                if (null == ourInstance){
                    ourInstance =  new LazySingleton();
                }
            }
        }
        return ourInstance;
    }

    // 测试是否线程安全
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(LazySingleton.getInstance());
            },i+"").start();
        }
    }

}
