package space.jachen.juc.arrayList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Collections.synchronizedList
 *
 * @author JaChen
 * @date 2023/2/27 9:49
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        /**
         * public void add(int index, E element) {
         *   synchronized (mutex) {list.add(index, element);}
         * }
         */
        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 200; i++) {
            new Thread(()->{
                synchronizedList.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(synchronizedList);
            }, String.valueOf(i)).start();
        }
    }
}
