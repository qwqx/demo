package c13;

import util.BTNode;
import util.BTUtil;

import java.util.*;

/**
 * ☆☆☆
 * @author TK
 */
public class LowestAncestor {

    public static BTNode lowestAncestor(BTNode root, BTNode a, BTNode b) {
        return process(root, a, b).lowestAncestor;
    }

    public static Info process(BTNode root, BTNode a, BTNode b) {

        if(null == root) {
            return new Info(false, false, null);
        }

        final Info leftInfo = process(root.left, a, b);
        final Info rightInfo = process(root.right, a, b);

        boolean findA = root == a || leftInfo.findA || rightInfo.findA;
        boolean findB = root == b || leftInfo.findB || rightInfo.findB;

        BTNode lowestAncestor = null;
        if(null != leftInfo.lowestAncestor) {
            lowestAncestor = leftInfo.lowestAncestor;
        }else if(null != rightInfo.lowestAncestor) {
            lowestAncestor = rightInfo.lowestAncestor;
        }else if(findA && findB){
            lowestAncestor = root;
        }


        return new Info(findA, findB, lowestAncestor);
    }

    public static class Info{
        boolean findA;
        boolean findB;

        BTNode lowestAncestor;

        Info(boolean findA, boolean findB, BTNode lowestAncestor) {
            this.findA = findA;
            this.findB = findB;
            this.lowestAncestor = lowestAncestor;
        }
    }

    public static BTNode lowestAncestor2(BTNode root, BTNode a, BTNode b) {
        if(null == root) {
            return null;
        }

        final Map<BTNode, BTNode> parentMap = getParentMap(root);

        final HashSet<Object> parentSet = new HashSet<>();
        BTNode curr = a;
        while(null != curr) {
            parentSet.add(curr);
            curr = parentMap.get(curr);
        }

        curr = b;
        while (null != curr && !parentSet.contains(curr)) {

            curr = parentMap.get(curr);
        }

        return  curr;
    }

    public static Map<BTNode, BTNode> getParentMap(BTNode root) {

        final Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);
        Map<BTNode, BTNode> parentMap = new HashMap<>();

        parentMap.put(root, null);
        while(!queue.isEmpty()) {
            final BTNode poll = queue.poll();

            if(null != poll.left) {
                queue.add(poll.left);
                parentMap.put(poll.left, poll);
            }

            if(null != poll.right) {
                queue.add(poll.right);
                parentMap.put(poll.right, poll);
            }
        }
        return parentMap;
    }

    public static void main(String[] args) {
        int testTimes = 10000;

        for (int i = 0; i < testTimes; i++) {

            final BTNode root = BTUtil.generateBT(4, 20D);
            final BTNode a = BTUtil.getRandomNode(root, 4);
            final BTNode b = BTUtil.getRandomNode(root, 4);

            final BTNode l1 = lowestAncestor(root, a, b);
            final BTNode l2 = lowestAncestor(root, a, b);
            if(l1 != l2) {
                BTUtil.print(root);
                System.out.println(a.value + "  " + b.value);
                System.out.println(l1.value + "  " + l2.value);
                System.out.println("failed");
                return;
            }


        }
        System.out.println("success");
    }
}
