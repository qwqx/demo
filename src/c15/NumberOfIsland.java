package c15;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author TK
 */
public class NumberOfIsland {

    public static int numberOfIsland(char[][] chars) {

        chars.clone();
        int islands = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars[i].length; j++) {
                if (chars[i][j] == '1') {
                    islands++;
                }
                infect(chars, i, j);
            }
        }

        return islands;
    }

    public static void infect(char[][] chars, int i, int j) {
        if (i < 0 || i >= chars.length || j < 0 || j >= chars[i].length || chars[i][j] != '1') {
            return;
        }

        chars[i][j] = 0;

        infect(chars, i, j - 1);
        infect(chars, i, j + 1);
        infect(chars, i - 1, j);
        infect(chars, i + 1, j);

    }

    public static int numberOfIsland2(char[][] chars) {

        final UnionFind unionFind = new UnionFind(chars);

        int m = chars.length;
        int n = chars[0].length;
        for (int i = 1; i < n; i++) {
            if (chars[0][i] == '1' && chars[0][i - 1] == '1') {
                unionFind.union(0, i, 0, i - 1);
            }
        }

        for (int i = 1; i < n; i++) {
            if (chars[i][0] == '1' && chars[i - 1][0] == '1') {
                unionFind.union(i, 0, i - 1, 0);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (chars[i][j] == '1' && chars[i - 1][j] == '1') {
                    unionFind.union(i, j, i - 1, j);
                }

                if (chars[i][j] == '1' && chars[i][j - 1] == '1') {
                    unionFind.union(i, j, i, j - 1);
                }
            }
        }

        return unionFind.setSize;

    }

    static class UnionFind {
        int[] parent;
        int[] size;
        int[] help;
        int col;

        int setSize;

        UnionFind(char[][] chars) {
            int m = chars.length;
            int n = chars[0].length;
            col = n;
            parent = new int[m * n];
            size = new int[m * n];
            help = new int[m * n];
            setSize = 0;

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (chars[i][j] == '1') {
                        final int idx = getIndex(i, j);
                        parent[idx] = idx;
                        size[idx] = 1;
                        setSize++;
                    }
                }
            }
        }

        public int getIndex(int i, int j) {
            return i * col + j;
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
            final int i = getIndex(r1, c1);
            final int j = getIndex(r2, c2);

            final int fatherI = findFather(i);
            final int fatherJ = findFather(j);

            if (fatherI != fatherJ) {
                int max = size[fatherI] >= size[fatherJ] ? fatherI : fatherJ;
                int min = max == fatherI ? fatherJ : fatherI;

                parent[min] = max;
                size[max] += size[min];
                size[min] = 0;

                setSize--;
            }
        }
    }

    public static int numberOfIsland3(char[][] chars) {
        int m = chars.length;
        int n = chars[0].length;

        final HashMap<Integer, Dot> valueMap = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (chars[i][j] == '1') {
                    valueMap.put(i * n + j, new Dot());
                }
            }
        }

        final UnionFind2 unionFind2 = new UnionFind2(valueMap, n);

        for (int i = 1; i < n; i++) {
            if (chars[0][i] == '1' && chars[0][i - 1] == '1') {
                unionFind2.union(0, i, 0, i - 1);
            }
        }

        for (int i = 1; i < n; i++) {
            if (chars[i][0] == '1' && chars[i - 1][0] == '1') {
                unionFind2.union(i, 0, i - 1, 0);
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (chars[i][j] == '1' && chars[i - 1][j] == '1') {
                    unionFind2.union(i, j, i - 1, j);
                }

                if (chars[i][j] == '1' && chars[i][j - 1] == '1') {
                    unionFind2.union(i, j, i, j - 1);
                }
            }
        }

        return unionFind2.setSize();
    }

    static class Dot {

    }

    static class UnionFind2 {

        Map<Integer, Dot> valueMap = new HashMap<>();
        Map<Dot, Dot> parent = new HashMap<>();
        Map<Dot, Integer> size = new HashMap<>();
        int col;

        UnionFind2(Map<Integer, Dot> valueMap, int col) {
            this.valueMap = valueMap;
            this.col = col;

            for (Dot curr : valueMap.values()) {
                parent.put(curr, curr);
                size.put(curr, 1);
            }
        }

        public int getIndex(int i, int j) {
            return i * col + j;
        }

        public Dot findFather(Dot dot) {
            final Stack<Dot> stack = new Stack<>();
            Dot curr = dot;
            while (curr != parent.get(curr)) {
                stack.push(curr);
                curr = parent.get(curr);
            }

            while (!stack.isEmpty()) {
                parent.put(stack.pop(), curr);
            }

            return curr;
        }

        public void union(int r1, int c1, int r2, int c2) {
            final int indexI = getIndex(r1, c1);
            final int indexJ = getIndex(r2, c2);

            final Dot fatherI = findFather(valueMap.get(indexI));
            final Dot fatherJ = findFather(valueMap.get(indexJ));

            if (fatherI != fatherJ) {
                Dot max = size.get(fatherI) >= size.get(fatherJ) ? fatherI : fatherJ;
                Dot min = max == fatherI ? fatherJ : fatherI;

                parent.put(min, max);
                size.put(max, size.get(fatherI) + size.get(fatherJ));
                size.remove(min);
            }

        }

        public int setSize() {
            return size.size();
        }


    }

    // 为了测试
    public static char[][] generateRandomMatrix(int row, int col) {
        char[][] board = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = Math.random() < 0.5 ? '1' : '0';
            }
        }
        return board;
    }

    // 为了测试
    public static char[][] copy(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        char[][] ans = new char[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                ans[i][j] = board[i][j];
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int testTime = 10000;

        for (int i = 0; i < testTime; i++) {

            final char[][] chars = generateRandomMatrix(10, 10);
            /*for(int i = 0; i< 4; i++) {
                for(int j = 0; j<4; j++) {
                    System.out.print(chars[i][j] + " ");
                }
                System.out.println();
            }*/

            final int i1 = numberOfIsland(copy(chars));
            final int i2 = numberOfIsland2(copy(chars));
            final int i3 = numberOfIsland3(copy(chars));
            //System.out.println(i3);
            if (i1 != i2 || i2 != i3) {
                System.out.println("failed");
                return;
            }

        }

        System.out.println("success");
    }
}
