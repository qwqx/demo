package c13;

import util.BTNode;
import util.BTUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class MaxSubBSTHead {

    /**
     * ☆☆
     * @param root
     * @return
     */
    public static BTNode maxSubBSTHead(BTNode root) {
        if (null == root) {
            return null;
        }

        return process(root).maxSubBSTHead;
    }

    public static Info process(BTNode root) {
        if (null == root) {
            return null;
        }

        final Info leftInfo = process(root.left);
        final Info rightInfo = process(root.right);

        int max = root.value;
        int min = root.value;
        BTNode maxSubBSTHead;
        int maxSubBSTSize;

        if (null != leftInfo) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }

        if (null != rightInfo) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        boolean leftBST = null == leftInfo ? true : leftInfo.maxSubBSTHead == root.left;
        boolean rightBst = null == rightInfo ? true : rightInfo.maxSubBSTHead == root.right;

        int leftBSTSize = null == leftInfo ? 0 : leftInfo.maxSubBSTSize;
        int rightBSTSize = null == rightInfo ? 0 : rightInfo.maxSubBSTSize;

        BTNode leftBSTHead = null == leftInfo ? null : leftInfo.maxSubBSTHead;
        BTNode rightBSTHead = null == rightInfo ? null : rightInfo.maxSubBSTHead;


        boolean isBST = true;
        if (!leftBST || !rightBst) {
            isBST = false;
        }

        if (null != leftInfo && root.value <= leftInfo.max) {
            isBST = false;
        }

        if (null != rightInfo && root.value >= rightInfo.min) {
            isBST = false;
        }


        if (isBST) {
            maxSubBSTHead = root;
            maxSubBSTSize = leftBSTSize + rightBSTSize + 1;
        } else {
            maxSubBSTHead = leftBSTSize > rightBSTSize ? leftBSTHead : rightBSTHead;
            maxSubBSTSize = leftBSTSize > rightBSTSize ? leftBSTSize : rightBSTSize;
        }

        return new Info(max, min, maxSubBSTHead, maxSubBSTSize);
    }

    /**
     * ☆☆☆
     * @param root
     * @return
     */
    public static BTNode maxSubBSTHead2(BTNode root) {
        if (null == root) {
            return null;
        }

        final int sbtSzie = getMaxSBTSize(root);
        if(sbtSzie > 0) {
            return root;
        }

        final BTNode lsbt = maxSubBSTHead2(root.left);
        final BTNode rsbt = maxSubBSTHead2(root.right);

        return getMaxSBTSize(lsbt) > getMaxSBTSize(rsbt) ? lsbt : rsbt;

    }

    public static int getMaxSBTSize(BTNode root) {
        final List<BTNode> list = new ArrayList();
        in(root, list);


        for(int i = 1; i < list.size(); i++) {
            if(list.get(i).value <= list.get(i-1).value) {
                return 0;
            }
        }

        return list.size();
    }


    public static void in(BTNode root, List<BTNode> list) {
        if (null == root) {
            return;
        }

        in(root.left, list);
        list.add(root);
        in(root.right, list);

    }


    public static class Info {
        int max;
        int min;
        BTNode maxSubBSTHead;
        int maxSubBSTSize;

        Info(int max, int min, BTNode maxSubBSTHead, int maxSubBSTSize) {
            this.max = max;
            this.min = min;
            this.maxSubBSTHead = maxSubBSTHead;
            this.maxSubBSTSize = maxSubBSTSize;
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;

        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(3, 20D);

           /* final BTNode root = new BTNode(5);
            root.left = new BTNode(4);
            root.left.left = new BTNode(7);
            root.left.right = new BTNode(9);*/

            //BTUtil.print(root);
            final BTNode maxSubBSTHead = maxSubBSTHead(root);
            final BTNode maxSubBSTHead2 = maxSubBSTHead2(root);

            if(maxSubBSTHead != maxSubBSTHead2) {
                BTUtil.print(root);
                System.out.println(maxSubBSTHead.value);
                System.out.println(maxSubBSTHead2.value);
                System.out.println("failed");
                return;
            }
        }
        System.out.println("success");
    }
}
