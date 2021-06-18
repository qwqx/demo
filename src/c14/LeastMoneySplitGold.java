package c14;

import util.TestUtil;

import java.util.PriorityQueue;

/**
 * @author TK
 */
public class LeastMoneySplitGold {

    /**
     * 哈夫曼树建造过程
     * @param gold
     * @return
     */
    public static int leastMoneySplitGold(int[] gold) {
        final PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int i = 0; i < gold.length; i++) {
            heap.add(gold[i]);
        }

        int money = 0;
        while(heap.size() > 1) {
            final int combine = heap.poll() + heap.poll();
            heap.add(combine);
            money += combine;
        }

        return money;
    }

    public static int leastMoneySplitGold2(int[] gold) {
        if(null == gold || gold.length <= 1) {
            return 0;
        }
        return process(gold);
    }


    public static int process(int[] gold) {
        if(gold.length == 1) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < gold.length; i++) {
            for(int j = i+1; j< gold.length; j++) {
                int money = gold[i] + gold[j];
                ans = Math.min(ans, money + process(copyAndMergeTwo(gold, i, j)));
            }
        }
        return ans;
    }

    public static int[] copyAndMergeTwo(int[] gold, int i, int j) {
        final int[] newGold = new int[gold.length - 1];
        for (int idx1 = 0, idx2 = 0; idx1 < gold.length; idx1++) {
            if(idx1 == i || idx1 == j) {
                continue;
            }
            newGold[idx2] = gold[idx1];
            idx2++;
        }
        newGold[newGold.length - 1] = gold[i] + gold[j];
        return newGold;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }


    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxSize = 6;
        int maxValue = 1000;

        for (int i = 0; i < testTimes; i++) {
            final int[] array = generateRandomArray(maxSize, maxValue);
            final int money = leastMoneySplitGold(array);
            final int money2 = leastMoneySplitGold2(array);

            if(money2 != money) {

                TestUtil.print(array);
                System.out.println(money);
                System.out.println(money2);
                System.out.println("failed");
                return;
            }

        }

        System.out.println("success");
    }
}
