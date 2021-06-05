package c05;

import util.TestUtil;

import java.util.Stack;

/**
 * @author TK
 */
public class Partition {


    // two part
    // <=arr[length-1]
    // > arr[length-1]
    public static int partitionTwo(int[] arr, int l, int r) {
        /*TestUtil.print(arr);
        System.out.println("l:" + l + ", r:" + r);*/
        int R = arr[r];

        int mixIdx = l - 1;
        for (int i = l; i <= r; i++) {
            if (arr[i] <= R) {
                TestUtil.swap(arr, mixIdx + 1, i);
                mixIdx++;
            }
        }
        return mixIdx;
    }

    // two part
    // < arr[length-1]
    // = arr[length-1]
    // > arr[length-1]
    //3 45 6 23 5 71 23 55 66 90 23 12 23
    //3 12 6 23 5 71 23 55 66 90 23 45 23
    public static int[] netherlandsFlag(int[] arr, int l, int r) {
        int R = arr[r];

        int less = l - 1;
        int more = r;
        for (int i = l; i < more; i++) {
            if (arr[i] < R) {
                TestUtil.swap(arr, ++less, i);
            } else if (arr[i] > R) {
                TestUtil.swap(arr, --more, i--);
            }
        }
        TestUtil.swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static void sort(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }


    public static void process(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        final int mid = partitionTwo(arr, l, r);
        process(arr, l, mid - 1);
        process(arr, mid + 1, r);
    }

    public static void QuickSortRecursive(int[] arr) {
        if (null == arr || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }


    public static void process1(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }

        TestUtil.swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        final int[] eq = netherlandsFlag(arr, l, r);
        process(arr, l, eq[0] - 1);
        process(arr, eq[1] + 1, r);
    }

    public static class Op {
        public int l;
        public int r;

        Op(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void quikSortUnRecursive(int[] arr) {

        if(null == arr || arr.length < 2) {
            return;
        }

        int[] eq = netherlandsFlag(arr, 0, arr.length - 1);

        final Stack<Op> stack = new Stack<>();
        stack.push(new Op(0, eq[0] -1 ));
        stack.push(new Op(eq[1] + 1, arr.length - 1));
        while (!stack.isEmpty()) {
            final Op pop = stack.pop();
            if(pop.l >= pop.r) {
                continue;
            }
            eq = netherlandsFlag(arr, pop.l, pop.r);

            stack.push(new Op(pop.l, eq[0] -1 ));
            stack.push(new Op(eq[1] + 1, pop.r));
        }

    }


    public static void main(String[] args) {


        int maxValue = 20;
        int maxLen = 10;
        int testTime = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            int[] copyArray = TestUtil.copyArray(arr);

            sort(arr);
            TestUtil.sort(copyArray);
            if (!TestUtil.equals(arr, copyArray)) {
                System.out.println("1 failed!");
                return;
            }

            //
            arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            copyArray = TestUtil.copyArray(arr);

            sort(arr);
            TestUtil.sort(copyArray);
            if (!TestUtil.equals(arr, copyArray)) {
                System.out.println("2 failed!");
                return;
            }

            //
            arr = TestUtil.generateRandomArray(maxLen, maxValue, false);
            copyArray = TestUtil.copyArray(arr);

            quikSortUnRecursive(arr);
            TestUtil.sort(copyArray);
            if (!TestUtil.equals(arr, copyArray)) {
                System.out.println("2 failed!");
                return;
            }

        }


        System.out.println("success!");

    }
}
