package six;

import util.TestUtil;

import java.util.PriorityQueue;

/**
 * @author TK
 */
public class SortArrDistanceLessK {

    // O(N*logK)
    public static void sort(int[] arr, int k) {
        final PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        for (; index < Math.min(arr.length, k); index++) {
            heap.add(arr[index]);
        }

        int i = 0;
        for(; index < arr.length; index++) {
            heap.add(arr[index]);
            arr[i++] = heap.poll();
        }

        while (!heap.isEmpty()) {
            arr[i++] = heap.poll();
        }
    }


    public static void main(String[] args) {



        int testTimes = 100000;
        int maxValue = 100;
        int maxLen = 20;
        int k = 5;

        final int[] arr = TestUtil.generateRandomArrayInstanceLessK(maxLen, maxValue, k);
        final int[] copy1 = TestUtil.copyArray(arr);
        final int[] copy2 = TestUtil.copyArray(arr);


        for (int i = 0; i < testTimes; i++) {
            sort(copy2, k);
            TestUtil.sort(copy1);

            if(!TestUtil.equals(copy2, copy1)) {
                TestUtil.print(arr);
                TestUtil.print(copy2);
                TestUtil.print(copy1);
                System.out.println("failed");
                return;
            }
        }
        System.out.println("success");
    }


}
