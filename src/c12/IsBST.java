package c12;

import util.BTNode;
import util.BTUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class IsBST {

    public static boolean isBST(BTNode root) {
        if(null == root) {
            return true;
        }
        return process(root).isSBT;
    }

    public static Info process(BTNode root) {
        if (root == null) {
            return null;
        }

        final Info leftInfo = process(root.left);
        final Info rightInfo = process(root.right);

        boolean isSBT = true;
        int max = root.value;
        int min = root.value;


        if(null != leftInfo) {
            max = Math.max(max, leftInfo.max);
            min = Math.min(min, leftInfo.min);
        }

        if(null != rightInfo) {
            max = Math.max(max, rightInfo.max);
            min = Math.min(min, rightInfo.min);
        }

        if(null != leftInfo && !leftInfo.isSBT) {
            isSBT = false;
        }

        if(null != rightInfo && !rightInfo.isSBT) {
            isSBT = false;
        }

        if(null != leftInfo && root.value <= leftInfo.max) {
            isSBT = false;
        }

        if(null != rightInfo && root.value >= rightInfo.min) {
            isSBT = false;
        }


        return new Info(isSBT, max, min);

    }

    public static class Info {
        public boolean isSBT;
        public int max;
        public int min;

        Info(boolean isSBT, int max, int min) {
            this.isSBT = isSBT;
            this.max = max;
            this.min = min;
        }
    }

    // bst中序遍历有序
    public static boolean isBST2(BTNode root) {

        final List<Integer> list = new ArrayList<>();
        in(root, list);

        for(int i = 1; i < list.size(); i++) {
            if(list.get(i) <= list.get(i-1)) {
                return false;
            }
        }

        return true;
    }

    public static void in(BTNode root, List<Integer> list) {
        if(null == root) {
            return;
        }

        in(root.left, list);
        list.add(root.value);
        in(root.right, list);
    }


    public static void main(String[] args) {
        int testTimes = 100000;


        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(3, 20D);

          /* *//* final BTNode root = new BTNode(5);
            root.right = new BTNode(3);
            root.right.right = new BTNode(9);
*//*
            BTUtil.print(root);*/
            final boolean bst = isBST(root);
            final boolean bst2 = isBST2(root);
            if(bst2 != bst) {

                BTUtil.print(root);

                System.out.println(bst);
                System.out.println(bst2);
                System.out.println("failed!");
                return;
            }
        }
        System.out.println("success");
    }
}
