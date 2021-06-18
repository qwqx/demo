package c14;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author TK
 */
public class BestArrange {

    public static class Program implements Comparable{
        int start;
        int end;
        Program(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return start + "----" + end;
        }

        @Override
        public int compareTo(Object o) {
            Program oth = (Program) o;
            return this.end - oth.end;
        }
    }

    /**
     * 按end 排序，先取end最早的
     * @param programs
     * @return
     */
    public static int bestRange(Program[] programs) {
        Arrays.sort(programs, (p1, p2) -> p1.end - p2.end);
        int ans = 0;
        int deadLine = 0;
        for (int i = 0; i < programs.length; i++) {
            if(programs[i].start >= deadLine) {
                ans++;
                deadLine = programs[i].end;
            }
        }
        return ans;
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

    public static int bestRange2(Program[] programs) {
        return process(programs, 0);
    }

    public static int process(Program[] programs, int deadLine) {
        if(null == programs || programs.length == 0) {
            return 0;
        }

        int ans = 0;
        for(int i = 0; i < programs.length; i++) {
            ans = Math.max(ans, 1 + process(getlevel(programs, programs[i].end), programs[i].end));
        }

        return ans;
    }

    public static Program[] getlevel(Program[] programs, int deadLine) {
        final ArrayList<Program> list = new ArrayList<>();
        for (int i = 0; i < programs.length; i++) {
            if(programs[i].start >= deadLine) {
                list.add(programs[i]);
            }
        }
        return list.toArray(new Program[list.size()]);
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int size = 10;
        int timeMax = 100;

        for (int i = 0; i < testTimes; i++) {
            final Program[] programs = generatePrograms(size, timeMax);



            final int bestRange = bestRange(programs);
            final int bestRange2 = bestRange2(programs);

            if(bestRange != bestRange2) {
                Arrays.stream(programs).sorted().forEach(System.out::println);
                System.out.println(bestRange);
                System.out.println(bestRange2);
                System.out.println("failed");
                return;
            }

        }
        System.out.println("success");
    }

}
