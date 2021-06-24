package c15;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class FriendCircles {

    public static int findFriendCircleNum(int[][] M) {
        final UnionFind unionFind = new UnionFind(M);
        return unionFind.size();
    }

    static class UnionFind {
        int[] parent;
        int[] size;
        int[] help;

        int count = 0;

        UnionFind(int[][] M) {
            parent = new int[M.length];
            size = new int[M.length];
            help = new int[M.length];
            for (int i = 0; i < M.length; i++) {
                parent[i] = i;
                size[i] = 1;
                count++;
            }

            for (int i = 0; i < M.length; i++) {
                for (int j = i+1; j < M.length; j++) {
                    if (M[i][j] == 1) {
                        union(i, j);
                    }
                }
            }
        }


        public int findFather(int i) {
            int j = 0;
            while(parent[i] != i) {
                help[j++] = i;
                i = parent[i];
            }

            while(j > 0) {
                parent[help[--j]] = i;
            }

            return i;
        }

        public boolean isSameSet(int i, int j) {
            return findFather(i) == findFather(j);
        }

        public void union(int i, int j) {
            final int fatherI = findFather(i);
            final int fatherJ = findFather(j);
            if(fatherI != fatherJ) {
                int max = size[fatherI] >= size[fatherJ] ? fatherI : fatherJ;
                int min = max == fatherI ? fatherJ : fatherI;

                parent[min] = max;
                size[max] = size[max] + size[min];
                size[min] = 0;
                count--;
            }
        }

        public int size() {
            return count;
        }
    }

    public static void main(String[] args) {
        int[][] m = {
                {1,0,0,1},
                {0,1,0,0},
                {0,0,1,0},
                {1,0,0,1}
        };

        System.out.println(findFriendCircleNum(m));


        final UnionFind unionFind = new UnionFind(m);
        System.out.println(unionFind.isSameSet(0, 1));
        System.out.println(unionFind.isSameSet(0, 3));
    }


}
