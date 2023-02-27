package space.jachen.juc.arrayList;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Vector
 *
 * @author JaChen
 * @date 2023/2/27 9:46
 */
public class VectorDemo {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();

        /**
         * public synchronized boolean add(E e) {
         *         modCount++;
         *         ensureCapacityHelper(elementCount + 1);
         *         elementData[elementCount++] = e;
         *         return true;
         *     }
         */
        vector.add("1");

        System.out.println(vector);
    }
}
