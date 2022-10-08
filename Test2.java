package the_advanced.threads.code;

/**
 * @ClassName Test2
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/7 10:47
 * @Version 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                for (int i = 2; i < 101; i+=2) {
                    System.out.println("偶数：" + i);
                }
            }
        }.start();


        new Thread(new Runnable(){
            @Override
            public void run() {
                for (int i = 1; i < 100; i+=2) {
                    System.out.println("奇数：" + i);
                }
            }
        }).start();
    }
}
