package the_advanced.threads.exercise;

import java.util.Scanner;

/**
 * @ClassName TEst
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/7 11:37
 * @Version 1.0
 *
 * 自定义线程类ChatThread：问是否结束（输入Y/y结束），如果输入的不是y，继续问是否结束，直到输入y才结束。
 *
 * 打印[1,10]，每隔10毫秒打印一个数字，现在当主线程打印完5之后，就让自定义线程类加塞，直到自定义线程类结束，主线程再继续。
 */
public class DemoThread {
    public static void main(String[] args) {
        for (int i = 1; i < 11; i++) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if (i==5){
                ChatThread c = new ChatThread();
                c.start();
                try {
                    c.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
class ChatThread extends Thread{

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("是否继续(Y/y)：");
            String s = sc.nextLine();
//            if(!"".equals(answer) && Character.toUpperCase(answer.charAt(0))=='Y'){
            if (!("".equals(s)) && Character.toUpperCase(s.charAt(0))=='Y'){
            break;
        }
        sc.close();
    }
}
}