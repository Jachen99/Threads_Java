package space.jachen.juc.synchronize;

import java.util.concurrent.TimeUnit;

/**
 *
 * 多线程的8个问题：
 * 1. 标准访问，先打印短信还是邮件
 * 2. 停4秒在短信方法内，先打印短信还是邮件
 * 3. 普通的hello方法，是先打短信还是hello
 * 4. 现在有两部手机，先打印短信还是邮件
 * 5. 两个静态同步方法，1部手机，先打印短信还是邮件
 * 6. 两个静态同步方法，2部手机，先打印短信还是邮件
 * 7. 1个静态同步方法，1个普通同步方法，1部手机，先打印短信还是邮件
 * 8. 1个静态同步方法，1个普通同步方法，2部手机，先打印短信还是邮件
 * 【synchronized出现异常会自动释放锁资源】
 *
 * @author JaChen
 * @date 2023/2/25 10:40
 */
class Phone {

    public synchronized void sendSMS() throws Exception {
//        TimeUnit.SECONDS.sleep(4);
        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() {
        System.out.println("------getHello");
    }

}

public class Lock_8 {

    public static void main(String[] args) throws Exception {

        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "AA").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
//                phone.sendEmail();
//                phone.getHello();
                phone2.sendEmail();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "BB").start();
    }
}