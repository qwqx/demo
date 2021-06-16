package c12;

import util.BTNode;
import util.BTUtil;

/**
 * @author TK
 */
public class IsFull {

    public static boolean isFull(BTNode root) {

        return process(root).isFull;
    }

    public static Info process(BTNode root) {
        if(null == root) {
            return new Info(true, 0);
        }

        final Info leftInfo = process(root.left);
        final Info rightInfo = process(root.right);

        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        return new Info(isFull, height);
    }

    public static class Info {
        boolean isFull;
        int height;

        Info(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isFull2(BTNode root) {

        int h = height(root);
        int n = num(root);
        return ((1 << h) -1 == n);
    }

    public static int height(BTNode root) {
        if(null == root) {
            return 0;
        }

        final int lh = height(root.left);
        final int rh = height(root.right);

        return Math.max(lh, rh) + 1;
    }

    public static int num(BTNode root) {
        if(null == root) {
            return 0;
        }

        final int lh = num(root.left);
        final int rh = num(root.right);

        return lh + rh + 1;
    }

    public static void main(String[] args) {
        int testTimes = 10000;

        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(4, 90D);

            final boolean full = isFull(root);
            final boolean full2 = isFull2(root);

            if(full != full2) {
                BTUtil.print(root);
                System.out.println(full);
                System.out.println(full2);
                System.out.println("failed");
                break;
            }
        }

        System.out.println("success");
    }
}
