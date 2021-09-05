package c23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queens {

    public static List<List<String>> solveNQueens(int n) {

        int limit = (1 << n) -1;
        List<List<String>> list = new ArrayList();
        int[] queue = new int[n];
        f(queue, 0, limit, 0, 0, 0, list);

        return list;

    }

    public static void f(int[] queue, int row, int limit, int colLimit, int leftLimit, int rightLimit, List<List<String>> ans) {


        if(colLimit == limit) {
            ans.add(generBoard(queue));
            return;
        }
        int pos = limit & ~(colLimit | leftLimit | rightLimit);

        while(pos != 0) {
            int mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            queue[row] = Integer.bitCount(mostRightOne-1);
            f(queue, row + 1, limit, colLimit | mostRightOne, (leftLimit | mostRightOne) << 1, (rightLimit | mostRightOne) >>> 1, ans);
            queue[row] = -1;

        }
    }

    public static List<String> generBoard(int[] queue) {
        int len = queue.length;
        List<String> board = new ArrayList();
        String str = "......";
        for(int i = 0; i< len; i++) {
            char[] row = new char[len];
            Arrays.fill(row, '.');
            row[queue[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    public static void main(String[] args) {
        List<List<String>> lists = solveNQueens(4);

        System.out.println();
    }
}
