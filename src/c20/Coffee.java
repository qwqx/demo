package c20;

import c16.TopologicalOrderBFS;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * ☆☆☆☆☆
 *
 * @author TK
 */
public class Coffee {

    public static int right(int[] arr, int n, int a, int b) {
        int[] drink = new int[n];
        int[] times = new int[arr.length];

        return forceMake(arr, times, 0, drink, n, a, b);
    }


    public static int forceMake(int[] arr, int[] times, int kth, int[] drink, int n, int a, int b) {
        if (kth == n) {
            final int[] drinkCopy = Arrays.copyOf(drink, kth);
            Arrays.sort(drinkCopy);
            return forceWash(drink, a, b, 0, 0, 0);
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int work = arr[i];
            int pre = times[i];
            drink[kth] = pre + work;
            times[i] = pre + work;
            min = Math.min(min, forceMake(arr, times, kth + 1, drink, n, a, b));
            drink[kth] = 0;
            times[i] = pre;
        }
        return min;
    }

    public static int forceWash(int[] drink, int a, int b, int index, int washLine, int time) {
        if (index == drink.length) {
            return time;
        }

        int wash = Math.max(drink[index], washLine) + a;
        final int p1 = forceWash(drink, a, b, index + 1, wash, Math.max(time, wash));

        final int notWash = drink[index] + b;
        final int p2 = forceWash(drink, a, b, index + 1, washLine, Math.max(time, notWash));

        return Math.min(p1, p2);
    }


    // 以下为贪心+优良暴力
    public static class Machine {
        public int timePoint;
        public int workTime;

        public Machine(int t, int w) {
            timePoint = t;
            workTime = w;
        }
    }

    public static class MachineComparator implements Comparator<Machine> {

        @Override
        public int compare(Machine o1, Machine o2) {
            return (o1.timePoint + o1.workTime) - (o2.timePoint + o2.workTime);
        }

    }

    public static int minTime1(int[] arr, int n, int a, int b) {
        final PriorityQueue<Machine> stack = new PriorityQueue<>(new MachineComparator());

        for (int i = 0; i < arr.length; i++) {
            stack.add(new Machine(0, arr[i]));
        }

        final int[] drink = new int[n];
        for (int i = 0; i < n; i++) {
            final Machine curr = stack.poll();
            curr.timePoint += curr.workTime;
            stack.add(curr);
            drink[i] = curr.timePoint;
        }

        return bestTime1(drink, a, b, 0, 0);
    }

    public static int bestTime1(int[] drinks, int wash, int air, int index, int free) {
        if (index == drinks.length) {
            return 0;
        }

        int selfClean = Math.max(drinks[index], free) + wash;
        int restClean = bestTime1(drinks, wash, air, index + 1, selfClean);
        int p1 = Math.max(selfClean, restClean);

        final int selfAir = drinks[index] + air;
        final int restClean2 = bestTime1(drinks, wash, air, index + 1, free);
        int p2 = Math.max(selfAir, restClean2);

        return Math.min(p1, p2);
    }

    public static int minTime2(int[] arr, int n, int a, int b) {
        final PriorityQueue<Machine> stack = new PriorityQueue<>(new MachineComparator());

        for (int i = 0; i < arr.length; i++) {
            stack.add(new Machine(0, arr[i]));
        }

        final int[] drink = new int[n];
        for (int i = 0; i < n; i++) {
            final Machine curr = stack.poll();
            curr.timePoint += curr.workTime;
            stack.add(curr);
            drink[i] = curr.timePoint;
        }


        return bestTimeDp(drink, a, b);
    }

    public static int bestTimeDp(int[] drinks, int wash, int air) {
        int N = drinks.length;
        int maxFree = 0;
        for (int i = 0; i < drinks.length; i++) {
            maxFree = Math.max(maxFree, drinks[i]) + wash;
        }

        final int[][] dp = new int[N + 1][maxFree + 1];

        for (int index = N - 1; index >= 0; index--) {
            for (int free = 0; free < maxFree; free++) {
                //dp[i][j] =
                int selfClean = Math.max(drinks[index], free) + wash;
                //?
                if (selfClean > maxFree) {
                    break; // 因为后面的也都不用填了
                }

                int restClean = dp[index + 1][selfClean];
                int p1 = Math.max(selfClean, restClean);

                final int selfAir = drinks[index] + air;
                final int restClean2 = dp[index + 1][free];
                int p2 = Math.max(selfAir, restClean2);

                dp[index][free] = Math.min(p1, p2);
            }
        }

        return dp[0][0];
    }


    // for test
    public static int[] randomArray(int len, int max) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * max) + 1;
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        System.out.print("arr : ");
        for (int j = 0; j < arr.length; j++) {
            System.out.print(arr[j] + ", ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 10;
        int max = 10;
        int testTime = 10;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(len, max);
            int n = (int) (Math.random() * 7) + 1;
            int a = (int) (Math.random() * 7) + 1;
            int b = (int) (Math.random() * 10) + 1;
            int ans1 = right(arr, n, a, b);
            int ans2 = minTime1(arr, n, a, b);
            int ans3 = minTime2(arr, n, a, b);
            if (ans1 != ans2) {
                printArray(arr);
                System.out.println("n : " + n);
                System.out.println("a : " + a);
                System.out.println("b : " + b);
                System.out.println(ans1 + " , " + ans2 + " , " + ans3);
                System.out.println("===============");
                break;
            }
        }
        System.out.println("测试结束");

    }
}
