package c12;

import util.BTNode;
import util.BTUtil;

/**
 * @author TK
 */
public class IsBalanced {

    public static boolean isbBalanced(BTNode root) {

        return process(root).isBalanced;
    }

    public static Info process(BTNode root) {

        if (null == root) {
            return new Info(true, 0);
        }

        final Info leftInfo = process(root.left);
        final Info rightInfo = process(root.right);

        boolean isbBalanced = false;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        if (leftInfo.isBalanced && rightInfo.isBalanced && Math.abs(leftInfo.height - rightInfo.height) <= 1) {
            isbBalanced = true;
        }

        return new Info(isbBalanced, height);
    }

    public static class Info {
        public boolean isBalanced;
        public int height;

        Info(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public static boolean isbalanced2(BTNode root) {

        final boolean[] ans = new boolean[1];
        ans[0] = true;
        post(root, ans);
        return ans[0];

    }

    public static int post(BTNode root, boolean[] ans) {
        if (!ans[0] || null == root) {
            return -1;
        }

        final int leftHeight = post(root.left, ans);
        final int rightHeight = post(root.right, ans);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;

        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(4, 50D);

            final boolean b = isbBalanced(root);
            final boolean b2 = isbalanced2(root);
            if(b != b2) {

                System.out.println("failed");
                return;
            }
        }

        System.out.println("success");
    }
}
