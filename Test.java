package the_advanced.threads.code;

/**
 * @ClassName Test
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/7 10:41
 * @Version 1.0
 */
public class Test{

    public static void main(String[] args) {
        A a = new A();
        a.start();

        for (int i = 1; i < 100; i+=2) {
            System.out.println("奇数：" + i);
        }
    }
}
class A extends Thread{
    @Override
    public void run() {
        for (int i = 2; i < 101; i+=2) {
            System.out.println("偶数：" + i);
        }
    }
}
