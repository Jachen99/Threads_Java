package the_advanced.consumers_producers.one_one;

public class TestWorkbench {
    public static void main(String[] args) {
        Workbench workbench = new Workbench();
        Cook cook = new Cook("厨  师",workbench);
        Waiter waiter = new Waiter("服务员",workbench);

        cook.start();
        waiter.start();
    }
}
