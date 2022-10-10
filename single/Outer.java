package the_advanced.single;

public class Outer {
    static {
        System.out.println("外部类的静态代码块");
    }

    private static class Inner{
        static Outer instance = new Outer();
        static {
            System.out.println("内部类的静态代码块");
        }
    }

    public static void method(){
        System.out.println("外部类的静态方法");
    }

    public static Outer getInstance(){
        return Inner.instance;
    }
}
