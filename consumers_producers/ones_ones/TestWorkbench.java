package the_advanced.consumers_producers.ones_ones;

public class TestWorkbench {
    public static void main(String[] args) {
        Workbench workbench = new Workbench();
        Cook cook = new Cook("大厨师1",workbench);
        Waiter waiter = new Waiter("服务员1",workbench);

        Cook cook2 = new Cook("大厨师2",workbench);
        Waiter waiter2 = new Waiter("服务员2",workbench);

        cook.start();
        cook2.start();
        waiter.start();
        waiter2.start();
    }
}
