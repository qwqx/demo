package c21;

/**
 * @author TK
 */
public class MinPathSum {

    public static int minPathSum(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return 0;
        }

        return process(matrix, 0, 0);
    }

    public static int process(int[][] matrix, int row, int col) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (row == m - 1 && col == n - 1) {
            return matrix[m - 1][n - 1];
        }

        if (row == m - 1) {
            return matrix[row][col] + process(matrix, row, col + 1);
        }

        if (col == n - 1) {
            return matrix[row][col] + process(matrix, row + 1, col);
        }

        int p1 = matrix[row][col] + process(matrix, row, col + 1);
        int p2 = matrix[row][col] + process(matrix, row + 1, col);

        return Math.min(p1, p2);
    }

    public static int minPathSum1(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[][] dp = new int[m][n];

        dp[m - 1][n - 1] = matrix[m - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            dp[m - 1][i] = matrix[m - 1][i] + dp[m - 1][i + 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            dp[i][n - 1] = matrix[i][n - 1] + dp[i + 1][n - 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i][j + 1], dp[i + 1][j]);
            }
        }
        return dp[0][0];
    }


    public static int minPathSum2(int[][] matrix) {
        if (null == matrix || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;
        int n = matrix[0].length;

        int[] dp = new int[n];
        dp[n-1] = matrix[m-1][n-1];
        for (int i = n - 2; i >= 0; i--) {
            dp[i] = matrix[m - 1][i] + dp[i + 1];
        }

        for(int i = m-2; i>=0; i-- ) {
            dp[n-1] += matrix[i][n-1];
            for(int j = n-2; j >=0; j--) {
                dp[j] = matrix[i][j] + Math.min(dp[j+1], dp[j]);
            }
        }

        return dp[0];
    }


        public static int[][] generateMatrix(int m, int n) {
        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 20);
            }
        }

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int row = 5;
        int col = 5;
        final int[][] matrix = generateMatrix(row, col);
        printMatrix(matrix);

        System.out.println(minPathSum(matrix));
        System.out.println(minPathSum1(matrix));
        System.out.println(minPathSum2(matrix));

    }
}
