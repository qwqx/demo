package c24;

import util.TestUtil;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author TK
 */
public class Code02_AllLessNumSubArray {

    public static int right(int[] arr, int sum) {

        int count = 0;
        for (int l = 0; l < arr.length; l++) {
            for (int r = l + 1; r <= arr.length; r++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int i = l; i < r; i++) {
                    max = Math.max(max, arr[i]);
                    min = Math.min(min, arr[i]);
                }
                if (max - min <= sum) {
                    count++;
                }
            }
        }

        return count;
    }

    //1.l-r满足 ---> 缩小范围都满足--> l-r所有的子数组都满足
    //2.l-r不满足---> 增加范围也不会满足--->开始缩小l
    public static int max(int[] arr, int sum) {
        if (null == arr || sum < 0) {
            return 0;
        }

        Deque<Integer> maxQueue = new LinkedList<>();
        Deque<Integer> minQueue = new LinkedList<>();

        int r = 0;
        int count = 0;
        for (int l = 0; l < arr.length; l++) {
            while (r < arr.length) {
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[r]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(r);

                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[r]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(r);

                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] <= sum) {
                    r++;
                }else {
                    break;
                }
            }

            count += r - l;
            if (maxQueue.peekFirst() == l) {
                maxQueue.pollFirst();
            }

            if (minQueue.peekFirst() == l) {
                minQueue.pollFirst();
            }
        }


        return count;

    }

    public static void main(String[] args) {
        int maxLen = 100;
        int maxValue = 200;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            int sum = (int) (Math.random() * (maxValue + 1));
            int ans1 = right(arr, sum);
            int ans2 = max(arr, sum);
            if (ans1 != ans2) {
                System.out.println("Oops!");
                TestUtil.print(arr);
                System.out.println(sum);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("测试结束");
    }
}
