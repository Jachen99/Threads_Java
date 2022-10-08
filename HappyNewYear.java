package the_advanced.threads.exercise;

/**
 * @ClassName HappyNewYear
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/7 11:33
 * @Version 1.0
 *
 * 模拟新年倒计时，每隔1秒输出一个数字，依次输出10,9,8......1，最后输出：新年快乐！
 */
public class HappyNewYear {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            System.out.print(10-i+",");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
