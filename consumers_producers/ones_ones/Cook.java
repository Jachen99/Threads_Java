package the_advanced.consumers_producers.ones_one;

//厨师：做菜
public class Cook extends Thread {
    private Workbench workbench ;

    public Cook(String name, Workbench workbench) {
        super(name);
        this.workbench = workbench;
    }

    @Override
    public void run() {
        while(true){
            workbench.put();
            try {
//                Thread.sleep(2000);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
