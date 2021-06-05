package c06;

import util.TestUtil;

import java.util.*;

/**
 * @author TK
 */
public class MaxHeap {
    public int[] arr;
    public int size;
    public int limit;

    MaxHeap(int limit) {
        this.limit = limit;
        arr = new int[limit];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == limit;
    }

    public void push(int value) {
        if (isFull()) {
            throw new RuntimeException("full");
        }
        arr[size] = value;
        heapInsert(arr, size++);
    }

    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("empty");
        }
        int max = arr[0];
        TestUtil.swap(arr, 0, size - 1);
        heapify(arr, 0, --size);
        return max;
    }

    public void heapInsert(int[] arr, int index) {
        int parentIndex = (index - 1) / 2;
        while (parentIndex >= 0 && arr[index] > arr[parentIndex]) {
            TestUtil.swap(arr, index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }

    public void heapify(int[] arr, int index, int size) {

        int left = index * 2 + 1;
        while (left < size) {
            int right = left + 1;
            int maxChildIndex = right >= size ? left : (arr[left] > arr[right] ? left : right);
            if (arr[index] >= arr[maxChildIndex]) {
                break;

            }

            TestUtil.swap(arr, maxChildIndex, index);
            index = maxChildIndex;
            left = index * 2 + 1;
        }
    }

    public void gen(int[] arr, int index) {
        heapInsert(arr, index);
        heapify(arr, index, size);
    }

    public static void main(String[] args) {

       /* int[] arr = {39, 99, 42, 6, 31 };

        final MaxHeap maxHeap1 = new MaxHeap(5);
        maxHeap1.push(39);
        maxHeap1.push(99);
        maxHeap1.push(42);
        maxHeap1.push(6);
        maxHeap1.push(31);
        System.out.println(maxHeap1.pop());
        System.out.println(maxHeap1.pop());
        System.out.println(maxHeap1.pop());
        System.out.println(maxHeap1.pop());
        System.out.println(maxHeap1.pop());*/

       /* int[] arr = {40, 60, 14, 90, 51, 34, 31, 59, 19};
        final MaxHeap maxHeap1 = new MaxHeap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            maxHeap1.push(arr[i]);
        }
*/
        int testTimes = 10000;
        int maxValue = 100;
        int maxLen = 20;

        for (int i = 0; i < testTimes; i++) {
            final int[] array = TestUtil.generateRandomArray(maxLen, maxValue, false);
            TestUtil.print(array);
            final MaxHeap maxHeap = new MaxHeap(maxLen);
            final PriorityQueue<Integer> priorityQue = new PriorityQueue<>((s1, s2) -> s2 - s1);
            for (int j = 0; j < array.length; j++) {
                maxHeap.push(array[j]);
                priorityQue.add(array[j]);
            }

            for (int j = 0; j < array.length; j++) {
                int s1 = maxHeap.pop();
                int s2 = priorityQue.poll();

                if (s1 != s2) {
                    System.out.println("failed!");
                    return;
                }
            }

        }
        System.out.println("success!");

    }


}
