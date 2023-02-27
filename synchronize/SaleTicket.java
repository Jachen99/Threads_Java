package space.jachen.juc.synchronize;

/**
 *
 * 卖票
 * 模拟：3个售票员  卖出20张票
 * 实现步骤：
 *      线程 操作 资源类 高内聚低耦合
 *          1、创建资源类 --- 票
 *          2、在资源类定义属性和方法 --- 张数20、方法【卖】
 *          3、创建多线程调用资源类方法
 *
 * @author JaChen
 * @date 2023/2/25 10:12
 */
public
class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        // 售票员1
        new Thread(()->{
            for (int i = 0; i < 30; i++){
                ticket.sale();
            }
        },"AAA").start();

        // 售票员2
        new Thread(()->{
            for (int i = 0; i < 30; i++){
                ticket.sale();
            }
        },"BBB").start();

        // 售票员3
        new Thread(()->{
            for (int i = 0; i < 30; i++){
                ticket.sale();
            }
        },"CCC").start();
    }
}

// 1、创建资源类 --- 票
class Ticket{
    // 2、在资源类定义属性和方法 --- 张数20、方法【卖】
    private Integer num = 20;
    // 卖票
    synchronized
    public void sale(){
        if (num <= 0){
            System.out.println("票卖完了~   num = " + num);
            return;
        }
        try {
            Thread.sleep(50);
            num--;
            System.out.println(Thread.currentThread().getName()+"剩余   num = " + num);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}