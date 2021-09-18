package c25;

/**
 * @author TK
 * https://leetcode.com/problems/count-submatrices-with-all-ones
 * <p>
 * 给定一个二维数组matrix，其中的值不是0就是1，返回全部由1组成的子矩形数量
 * <p>
 * 1.以每一行为底作为直方图
 * i---j---k
 * 0     1
 * 0 1   1 1
 * 0 1 1 1 1
 * 1 1 1 1 1
 * 1 1 1 1 1 1
 * <p>
 *  2.根据单调栈求出j = 2时的比j小的最近位置i,k
 * i = 0,k = 5
 * 3.求出i-k间的个数，范围,宽：w=(k- i -1),高：h=height[j] - Math(height[i],height[k])
 *  1>计算以0-w范围类矩形个数(n*(n+1))/2,
 *  2>计算以0-w为底不同高度的矩形个数（n*(n+1)）/2 * height
 *
 */
public class Code05_CountSubmatricesWithAllOnes {
    public static int numSubmat(int[][] mat) {

        if (mat == null || mat.length == 0 || mat[0].length == 0) {
            return 0;
        }

        int m = mat.length;
        int n = mat[0].length;
        int[] height = new int[n];
        int num = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                height[j] = mat[i][j] == 0 ? 0 : height[j] + 1;
            }

            num += numberOfRectangle(height);
        }

        return num;
    }

    public static int numberOfRectangle(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int size = height.length;
        int[] maxStack = new int[size];
        int idx = -1;
        int num = 0;
        for (int i = 0; i < size; i++) {
            while (idx != -1 && height[i] <= height[maxStack[idx]]) {
                int curr = maxStack[idx--];
                if (height[curr] > height[i]) {
                    final int leftLess = idx == -1 ? -1 : maxStack[idx];
                    int rightLess = i;
                    int down = Math.max(leftLess == -1 ? 0 : height[leftLess], height[rightLess]);
                    int n = rightLess - leftLess - 1;
                    num += (height[curr] - down) * num(n);
                }
            }

            maxStack[++idx] = i;
        }

        while (idx != -1) {
            int curr = maxStack[idx--];
            final int leftLess = idx == -1 ? -1 : maxStack[idx];
            int rightLess = -1;
            int down = leftLess == -1 ? 0 : height[leftLess];
            int n = height.length - leftLess - 1;
            num += (height[curr] - down) * num(n);
        }

        return num;
    }

    public static int num(int n) {
        return (n * (n + 1)) >> 1;
    }



    public static void main(String[] args) {
        /*int[][] mat = {
                {0, 1, 1, 1, 0},
                {1, 1, 1, 0, 0},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 1, 0}
        };*/

        int[][] mat = {
                {1,0,1},//2
                {1,1,0},//4
                {1,1,0}//1 6
        };
        //[[1,0,1],[1,1,0],[1,1,0]]

        final int num = numSubmat(mat);
        System.out.println(num);

    }
}
