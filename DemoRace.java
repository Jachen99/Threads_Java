package the_advanced.threads.exercise;


/**
 * @ClassName DemoRace
 * @Description TODO
 * @Author JaChen
 * @Date 2022/10/7 12:01
 * @Version 1.0
 * <p>
 * 编写龟兔赛跑多线程程序，设赛跑长度为30米
 * 兔子的速度是10米每秒，兔子每跑完10米休眠的时间10秒
 * 乌龟的速度是1米每秒，乌龟每跑完10米的休眠时间是1秒
 * 要求：要等兔子和乌龟的线程结束，主线程（裁判）才能公布最后的结果。
 * 提示：System.currentTimeMillis()方法可以返回当前时间的毫秒值(long类型)
 */
public class DemoRace {

    public static void main(String[] args) {
        RT r1 = new RT("兔子", 200, 100);
        RT r2 = new RT("乌龟", 100, 100);

        r1.start();
        r2.start();

        try {
            r1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            r2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("兔子的距离：" + r1.getDistance());
        System.out.println("乌龟的距离：" + r2.getDistance());
        System.out.println("兔子用时：" + r1.getTime());
        System.out.println("乌龟用时：" + r2.getTime());

        if(r1.getDistance() > r2.getDistance()){
            System.out.println("兔子赢");
        }else if(r1.getDistance() <r2.getDistance()){
            System.out.println("乌龟赢");
        }else {
            if (r1.getTime() < r2.getTime()) {
                System.out.println("兔子赢");
            } else if (r1.getTime() > r2.getTime()) {
                System.out.println("乌龟赢");
            } else {
                System.out.println("平局");
            }
        }

    }
}

class RT extends Thread {
    private long RTime;
    private long MTime;
    private long time;
    private int distance;//当前运动员一共跑了几米
    private final int MAX_DISTANCE = 30;
    private volatile static boolean flag = true;//true表示跑  去主存读取

    public RT(String name, long RTime, long MTime) {
        super(name);
        this.RTime = RTime;
        this.MTime = MTime;
    }
    @Override
    public void run() {

        long start = System.currentTimeMillis();
        for (int i = 1; i < 31 && flag && distance < MAX_DISTANCE; i++) {
            try {
                Thread.sleep(MTime);
                distance++;
                System.out.println(getName() + "跑了" + i + "米");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                if (i == 10 || i == 20) {
                    System.out.println(getName() + "正在休息....");
                    Thread.sleep(RTime);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if(distance == MAX_DISTANCE){
            flag = false;
            System.out.println(getName() + "到达终点.");
        }

        long end = System.currentTimeMillis();
        time = end - start;
    }

    public long getTime() {
        return time;
    }

    public int getDistance() {
        return distance;
    }

    public static boolean isFlag() {
        return flag;
    }
}

