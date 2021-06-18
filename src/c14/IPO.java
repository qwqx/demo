package c14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author TK
 */
public class IPO {

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }

        @Override
        public String toString() {
            return p + "----" + c;
        }
    }

    public static int findMaxProfit(Program[] programs, int w, int k) {
        if(null == programs || programs.length == 0) {
            return w;
        }
        final PriorityQueue<Program> smallCost = new PriorityQueue<>(Comparator.comparingInt(s -> s.c));
        final PriorityQueue<Program> bigProfit = new PriorityQueue<>((s1, s2)-> s2.p - s1.p);

        for (int i = 0; i < programs.length; i++) {
            smallCost.add(programs[i]);
        }

        int ownerMoney = w;
        int times = 0;
        while(times < k) {
            while (!smallCost.isEmpty() && smallCost.peek().c <= ownerMoney) {
                bigProfit.add(smallCost.poll());
            }
            if(bigProfit.isEmpty()) {
                break;
            }
            final Program poll = bigProfit.poll();
            ownerMoney += poll.p;

            times++;
        }

        return ownerMoney;
    }

    public static Program[] generatePrograms(int programSize, int timeMax) {
        Program[] ans = new Program[(int) (Math.random() * (programSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Program(r1, r1 + 1);
            } else {
                ans[i] = new Program(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int size = 5;
        int timeMax = 100;

        final Program[] programs = generatePrograms(size, timeMax);
        Arrays.stream(programs).forEach(System.out::println);
        int profit = findMaxProfit(programs, 70, 3);
        System.out.println(profit);


    }
}
