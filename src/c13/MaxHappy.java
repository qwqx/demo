package c13;

import java.util.ArrayList;
import java.util.List;

/**
 * ☆☆☆
 * @author TK
 */
public class MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }

    }

    public static int maxHappy(Employee root) {
        final Info info = process(root);
        return Math.max(info.yes, info.no);
    }

    public static Info process(Employee root) {
        if(null == root) {
            return new Info(0,0);
        }

        int yes = root.happy;
        int no = 0;
        for(Employee e : root.nexts) {
            final Info info = process(e);
            yes += info.no;
            no += Math.max(info.yes, info.no);
        }

        return  new Info(yes, no);
    }

    public static class Info{
        int yes;
        int no;

        Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int maxHappy2(Employee root) {
        if(null == root) {
            return 0;
        }

        return process2(root, false);
    }

    //?????
    public static int process2(Employee root, boolean up) {
        if(up) {
            int no = 0;
            for(Employee e : root.nexts) {
                no += process2(e, false);
            }
            return no;
        }else {
            int yes = root.happy;
            int no = 0;
            for (Employee e : root.nexts) {
                yes += process2(e, true);
                no += process2(e, false);
            }
            return Math.max(yes, no);
        }

    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy(boss) != maxHappy2(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
