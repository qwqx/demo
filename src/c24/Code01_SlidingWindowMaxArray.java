package c24;

import util.TestUtil;

import java.util.*;

/**
 * 每个窗口最大值
 * 以一个双端链表记录 窗口最大值更新记录，列表从大到小，每次移动窗口都更新记录
 * @author TK
 */
public class Code01_SlidingWindowMaxArray {

    //O(n2)
    public static int[] right(int[] arr, int w) {
        if(null == arr || w < 1 || arr.length < w) {
            return null;
        }
        int r = w - 1;
        int length = arr.length;
        int[] ans = new int[length - w + 1];
        int idx = 0;
        for (; r < length; r++, idx++) {
            int l = r - w + 1;
            int max = 0;
            for (int i = l; i <= r; i++) {
                max = Math.max(max, arr[i]);
            }
            ans[idx] = max;
        }

        return ans;
    }


    //O(n)
    public static int[] maxWindow(int[] arr, int w) {
        if(null == arr || w < 1 || arr.length < w) {
            return null;
        }
        final Deque<Integer> help = new LinkedList<>();

        int l = 0;
        int r = 0;
        int idx = 0;
        int[] ans = new int[arr.length - w + 1];
        for (; r < arr.length; r++) {

            while (!help.isEmpty() && arr[help.peekLast()] <= arr[r]) {
                help.pollLast();
            }
            help.addLast(r);

            if (r - w == help.peekFirst()) {
                help.pollFirst();
            }

            if (r >= w - 1) {
                ans[idx++] = arr[help.peekFirst()];
            }
        }
        return ans;

    }


    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = TestUtil.generateRandomArray(maxSize, maxValue, false);
            int w = (int) (Math.random() * (arr.length + 1));
            int[] ans1 = maxWindow(arr, w);
            int[] ans2 = right(arr, w);
            if (!TestUtil.equals(ans1, ans2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");

    }
}
