package the_advanced.consumers_producers.one_one;

//服务员：取菜
public class Waiter extends Thread{
    private Workbench workbench ;

    public Waiter(String name, Workbench workbench) {
        super(name);//调用Thread(String name)给线程取名字
        this.workbench = workbench;
    }

    @Override
    public void run() {
        while(true) {
            workbench.take();
            try {
//                Thread.sleep(1000);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
