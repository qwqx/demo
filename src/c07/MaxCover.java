package c07;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author TK
 */
public class MaxCover {

    public static int maxCover(int[][] lines) {
        sortByLeft(lines);

        final PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(lines[0][1]);
        int max = 1;
        for (int i = 1; i < lines.length; i++) {
            Integer minRight = heap.peek();
            while(null != minRight && minRight.intValue() <= lines[i][0]) {
                heap.poll();
                minRight = heap.peek();
            }

            heap.add(lines[i][1]);

            max = Math.max(max, heap.size());
        }

        return max;
    }


    public static void sortByLeft(int[][] lines) {
        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));
    }

    public static int maxCoverViolence(int[][] lines) {
        int min = 0;
        int max = 0;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }

        int maxCover= 0;
        for(int i = min; i < max; i++) {
            int maxTemp = 0;
            for (int j = 0; j < lines.length; j++) {
                double temp = i + 0.5;
                if(temp > lines[j][0] && temp < lines[j][1]) {
                    maxTemp++;
                }
            }
            maxCover = Math.max(maxTemp, maxCover);
        }
        return maxCover;

    }

    public static int[][] genLines(int maxValue, int len) {
        int [][] lines = new int[len][];
        for (int i = 0; i < len; i++) {
            int end1 = (int) (Math.random() * maxValue + 1);
            int end2 = (int) (Math.random() * maxValue + 1);
            if(end1 == end2) {
                end2++;
            }
            lines[i] = new int[]{Math.min(end1, end2), Math.max(end1, end2)};
        }
        return lines;

    }


    public static void main(String[] args) {

        int testTimes = 10000;
        int maxValue = 100;
        int len = 6;


        for (int i = 0; i < testTimes; i++) {
            final int[][] lines = /*{{1,10}, {2,15}, {5,20}, {10,20}, {16,19}, {18,25}};*/genLines(maxValue, len);
            int s1 = maxCover(lines);
            int s2 = maxCoverViolence(lines);
            if(s1!= s2) {
                System.out.println("failed");
                return;
            }
        }
        System.out.println("success");
    }
}
