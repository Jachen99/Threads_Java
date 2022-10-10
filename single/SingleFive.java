package the_advanced.single;

public class SingleFive {
    private SingleFive(){}

    //静态内部类
    private static class Inner{
        static SingleFive instance = new SingleFive();
    }

    //再外部类调用这个方法时，才会对内部类进行初始化，所以instance对象的创建仍然是获取对象时创建
    //而不是在外部类初始化时创建，所以仍然是懒汉式
    public static SingleFive getInstance(){
        return Inner.instance;
    }

}
