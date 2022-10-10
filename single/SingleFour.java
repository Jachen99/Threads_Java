package the_advanced.single;

public class SingleFour {
    private static SingleFour instance;
    //这个静态变量的作用是记录这个类的唯一对象，这个对象这里还没new

    private SingleFour(){

    }

    public static synchronized SingleFour getInstance(){
        if(instance == null) {
            instance = new SingleFour();
        }
        return instance;
    }
}
