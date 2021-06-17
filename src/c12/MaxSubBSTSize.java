package c12;

import util.BTNode;
import util.BTUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class MaxSubBSTSize {

    /**
     * ☆☆
     * @param root
     * @return
     */
    public static int maxSubBSTSize(BTNode root) {
        if(null == root) {
            return 0;
        }
        return process(root).maxBSTSize;
    }

    public static Info process(BTNode root) {
        if(null == root) {
            return null;
        }

        final Info leftInfo = process(root.left);
        final Info rightInfo = process(root.right);

        boolean isBST = true;
        int max = root.value;
        int min = root.value;
        int maxBSTSize;

        if(null != leftInfo) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }

        if(null != rightInfo) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        if(null != leftInfo && !leftInfo.isBST) {
            isBST = false;
        }

        if(null != rightInfo && !rightInfo.isBST) {
            isBST = false;
        }

        if(null != leftInfo && root.value <= leftInfo.max) {
            isBST = false;
        }

        if(null != rightInfo && root.value >= rightInfo.min) {
            isBST = false;
        }

        int ls = null == leftInfo ? 0 : leftInfo.maxBSTSize;
        int rs = null == rightInfo ? 0 : rightInfo.maxBSTSize;
        if(isBST) {
            maxBSTSize = ls + rs + 1;
        }else {
            maxBSTSize = Math.max(ls, rs);
        }

        return new Info(isBST, max, min, maxBSTSize);
    }

    public static class Info{
        boolean isBST;
        int max;
        int min;
        //int size;

        int maxBSTSize;

        Info(boolean isBST, int max, int min, int maxBSTSize) {
            this.isBST = isBST;
            this.max = max;
            this.min = min;
            this.maxBSTSize = maxBSTSize;
        }
    }

    /**
     * ☆☆☆
     * @param root
     * @return
     */
    public static int maxSubBSTSize2(BTNode root) {
        if(null == root) {
            return 0;
        }
        final int sbtSize = getSBTSize(root);
        if(sbtSize > 0) {
            return sbtSize;
        }

        return Math.max(maxSubBSTSize2(root.left), maxSubBSTSize2(root.right));
    }

    public static int getSBTSize(BTNode root) {
        List<BTNode> list = new ArrayList<>();
        in(root, list);

        for (int i = 1; i < list.size(); i++) {
            if(list.get(i).value <= list.get(i-1).value) {
                return 0;
            }
        }
        return list.size();
    }

    public static void in(BTNode root, List<BTNode> list) {
        if(null == root) {
            return;
        }
        in(root.left, list);
        list.add(root);
        in(root.right, list);
    }

    public static void main(String[] args) {
        int testTimes = 100000;

        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(3, 20D);

            /*final BTNode root = new BTNode(4);
            root.left = new BTNode(6);
            root.left.left = new BTNode(0);
            root.left.right = new BTNode(4);
            root.right = new BTNode(4);
            root.right.left = new BTNode(8);*/


            final int i1 = maxSubBSTSize(root);
            final int i2 = maxSubBSTSize2(root);

            if(i1 != i2) {
                BTUtil.print(root);
                System.out.println(i1);
                System.out.println(i2);
                System.out.println("failed");
                return;
            }
        }

        System.out.println("success");
    }
}
