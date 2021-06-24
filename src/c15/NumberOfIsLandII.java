package c15;

import java.util.*;

/**
 * @author TK
 */
public class NumberOfIsLandII {

    public static List<Integer> numberOfIsLand(int m, int n, int[][] positions) {

        final UnionFind unionFind = new UnionFind(m, n);
        final List<Integer> list = new LinkedList<>();
        for (int[] pos : positions) {
            list.add(unionFind.connect(pos[0], pos[1]));
        }
        return list;
    }

    static class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int row;
        int col;
        int setCount;

        UnionFind(int m, int n) {
            parent = new int[m * n];
            size = new int[m * n];
            help = new int[m * n];
            row = m;
            col = n;
            setCount = 0;
        }

        public int getIndex(int r, int c) {
            return r * col + c;
        }

        public int findFather(int i) {

            int idx = 0;
            while (parent[i] != i) {
                help[idx++] = i;
                i = parent[i];
            }

            while (idx > 0) {
                parent[help[--idx]] = i;
            }

            return i;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 >= row || c1 < 0 || c1 >= col || r2 < 0 || r2 >= row || c2 < 0 || c2 >= col) {
                return;
            }

            final int index1 = getIndex(r1, c1);
            final int index2 = getIndex(r2, c2);
            if(size[index1] == 0 || size[index2] == 0) {
                return;
            }

            final int father1 = findFather(index1);
            final int father2 = findFather(index2);

            if (father1 != father2) {
                int max = size[father1] >= size[father2] ? father1 : father2;
                int min = max == father1 ? father2 : father1;

                parent[min] = max;
                size[max] += size[min];
                //size[min] = 0;
                setCount--;
            }
        }

        public int connect(int r, int c) {
            final int index = getIndex(r, c);

            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                setCount++;

                union(r, c, r - 1, c);
                union(r, c, r + 1, c);
                union(r, c, r, c - 1);
                union(r, c, r, c + 1);
            }

            return setCount;
        }
    }


    public static List<Integer> numberOfIsLand2(int m, int n, int[][] positions) {
        final UnionFind2 unionFind2 = new UnionFind2(m, n);
        final List<Integer> list = new LinkedList<>();

        for(int[] pos : positions) {
            list.add(unionFind2.connect(pos[0], pos[1]));
        }
        return list;
    }

    static class UnionFind2 {
        Map<String, String> parent = new HashMap<>();
        Map<String, Integer> size = new HashMap<>();
        Stack<String> help = new Stack();

        int row;
        int col;
        int setCount;

        UnionFind2(int m, int n) {
            row = m;
            col = n;
            setCount= 0;
        }

        public String findFather(String index) {

            while(index != parent.get(index)) {
                help.push(index);
                index = parent.get(index);
            }

            while(!help.isEmpty()) {
                parent.put(help.pop(), index);
            }

            return index;
        }

        public void union(int r1, int c1, int r2, int c2) {
            if (r1 < 0 || r1 >= row || c1 < 0 || c1 >= col || r2 < 0 || r2 >= row || c2 < 0 || c2 >= col) {
                return;
            }

            String index1 = r1 + "-" + c1;
            String index2 = r2 + "-" + c2;

            if(!size.containsKey(index1) || !size.containsKey(index2)) {
                return;
            }

            final String father1 = findFather(index1);
            final String father2 = findFather(index2);

            if(!father1.equals(father2)) {
                String max = size.get(father1) >= size.get(father2) ? father1 : father2;
                String min = max.equals(father1) ? father2 : father1;

                parent.put(min, max);
                size.put(max, size.get(father1) + size.get(father2));
                setCount--;
            }
        }

        public int connect(int r, int c) {
            String index = r + "-" + c;

            if(null == size.get(index)) {
                parent.put(index, index);
                size.put(index, 1);
                setCount++;

                union(r, c, r - 1, c);
                union(r, c, r + 1, c);
                union(r, c, r, c - 1);
                union(r, c, r, c + 1);

            }
            return setCount;
        }
    }

    public static int[][] genMatrixPoint(int m, int n) {
        final int pointNum = (int) (Math.random() * (m * n / 4));
        int[][] point = new int[pointNum][];
        for(int i = 0; i < pointNum; i++) {
            int x = (int) (Math.random() * m);
            int y = (int) (Math.random() * n);
            point[i] = new int[]{x, y};
        }
        return point;
    }

    public static boolean listEqual(List<Integer> list1, List<Integer> list2) {
        if(list1 == null || list2 == null || list1.size() != list2.size()) {
            return false;
        }

        for(int i = 0; i < list1.size(); i++) {
            if(!list1.get(i).equals(list2.get(i))) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {

        int testTime = 100000;
        int m = 10;
        int n = 10;

        for (int i = 0; i < testTime; i++) {
            final int[][] point = genMatrixPoint(m, n);

            final List<Integer> ans1 = numberOfIsLand(m, n, point);
            final List<Integer> ans2 = numberOfIsLand2(m, n, point);

            if(!listEqual(ans1, ans2)) {
                System.out.println("failed");
                return;
            }
        }

        System.out.println("success");
    }
}
