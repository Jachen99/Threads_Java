package the_advanced.single;

public class TestSingleFour {
   static SingleFour s1 ;
    static SingleFour s2 ;
    public static void main(String[] args) {
        /*SingleFour s1 = SingleFour.getInstance();
        SingleFour s2 =SingleFour.getInstance();*/


         Thread t1 = new Thread(){
             @Override
             public void run() {
                 s1 = SingleFour.getInstance();
             }
         };
         t1.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                s2 = SingleFour.getInstance();
            }
        };
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);
        System.out.println(s1 == s2);//如果地址值相同，说明是同一个对象
    }
}
