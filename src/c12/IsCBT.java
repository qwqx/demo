package c12;

import util.BTNode;
import util.BTUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 完全二叉树
 * @author TK
 */
public class IsCBT {



    public static boolean isCBT(BTNode root) {
        return process(root).isCBT;
    }

    public static Info process(BTNode root) {
        if(null == root) {
            return new Info(true, true, 0);
        }

        final Info leftInfo = process(root.left);
        final Info rightInfo = process(root.right);

        boolean isFull = (leftInfo.isFull && rightInfo.isFull) && (leftInfo.height == rightInfo.height);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isCBT = false;
        if(isFull) {
            isCBT = true;
        }else {
            if(leftInfo.isCBT && rightInfo.isCBT) {
                if(rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }

                if(leftInfo.isFull && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }

        }

        return new Info(isCBT, isFull, height);
    }


    public static boolean isCBT2(BTNode root) {
        if(null == root) {
            return true;
        }

        final Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);

        boolean leaf = false;
        while(!queue.isEmpty()) {
            final BTNode r = queue.poll();

            if((null != r.right && null == r.left)
                || (leaf && (null != r.right || null != r.left))) {
                return false;
            }

            if(null != r.left) {
                queue.add(r.left);
            }

            if(null != r.right) {
                queue.add(r.right);
            }

            if(null == r.left || null == r.right) {
                leaf = true;
            }
        }

        return true;
    }

    public static class Info{
        public boolean isCBT;
        public boolean isFull;
        public int height;

        Info(boolean isCBT, boolean isFull, int height) {
            this.isCBT = isCBT;
            this.height = height;
            this.isFull = isFull;
        }
    }

    public static void main(String[] args) {
        int testTimes = 10000;

        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(3, 20D);

            if(isCBT(root) != isCBT2(root)) {
                System.out.println("failed");
                return;
            }
        }

        System.out.println("success");
    }
}
