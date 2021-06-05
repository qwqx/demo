package c06;

import util.TestUtil;

/**
 * @author TK
 */
public class HeapSort {


    public static void sort1(int[] arr) {
        final MaxHeap maxHeap = new MaxHeap(arr.length);
        //
        /*for (int i = 0; i < arr.length; i++) {
            maxHeap.heapInsert(arr, i);
        }*/

        //
        for (int i = arr.length - 1; i >= 0; i--) {
            maxHeap.heapify(arr, i, arr.length);
        }


        for (int i = arr.length - 1; i > 0; i--) {
            TestUtil.swap(arr, 0, i);
            maxHeap.heapify(arr, 0, i);
        }

    }


    public static void main(String[] args) {

        int testTimes = 100000;
        int maxValue = 100;
        int maxLen = 20;

        for (int i = 0; i < testTimes; i++) {
            final int[] array = TestUtil.generateRandomArray(maxLen, maxValue, false);
            final int[] copy = TestUtil.copyArray(array);

            sort1(array);
            TestUtil.print(array);
            TestUtil.sort(copy);
            if (!TestUtil.equals(array, copy)) {
                System.out.println("failed");
                return;
            }

        }
        System.out.println("success!");

    }

}
