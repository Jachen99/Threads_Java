package space.jachen.juc.safe;

/**
 * 饿汉单例   非原子性  线程安全
 *
 * @author JaChen
 * @date 2023/2/26 16:07
 */
public class HungrySingleton {

    private static final HungrySingleton ourInstance = new HungrySingleton();

    private HungrySingleton(){}   // 私有化

    public static HungrySingleton getInstance(){
        return ourInstance;
    }

    // 测试是否线程安全
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                System.out.println(HungrySingleton.getInstance());
            },i+"").start();
        }
    }

}
