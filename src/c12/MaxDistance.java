package c12;

import util.BTNode;
import util.BTUtil;

import java.util.*;

/**
 * @author TK
 */
public class MaxDistance {

    /**
     * ☆☆
     * @param root
     * @return
     */
    public static int maxDistance(BTNode root) {

        return process(root).maxDistance;
    }

    public static Info process(BTNode root) {

        if(null == root) {
            return new Info(0, 0);
        }

        final Info leftInfo = process(root.left);
        final Info rightInfo = process(root.right);

        int maxInstance = Math.max(Math.max(leftInfo.maxDistance, rightInfo.maxDistance), leftInfo.height + rightInfo.height + 1);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        return new Info(maxInstance, height);
    }

    public static class Info {
        int maxDistance;
        int height;

        Info(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }


    /**
     * ☆☆☆
     * @param root
     * @return
     */
    public static int maxDistance2(BTNode root) {
        if(null == root) {
            return 0;
        }

        final Map<BTNode, BTNode> parentMap = new HashMap<>();
        parentMap.put(root, null);
        getParentMap(root, parentMap);

        final List<BTNode> btNodes = new ArrayList<>(parentMap.keySet());

        int maxInstance = 0;
        for(int i = 0; i < btNodes.size(); i++) {
            for(int j = i; j < btNodes.size(); j++) {
                maxInstance = Math.max(maxInstance, getInstance(btNodes.get(i), btNodes.get(j), parentMap));
            }
        }

        return maxInstance;

    }

    public static void getParentMap(BTNode root, Map<BTNode, BTNode> map) {
        if(null != root.left) {
            map.put(root.left, root);
            getParentMap(root.left, map);
        }

        if(null != root.right) {
            map.put(root.right, root);
            getParentMap(root.right, map);
        }
    }

    public static int getInstance(BTNode node1, BTNode node2, Map<BTNode, BTNode> parentMap) {

        final Set<BTNode> parents = new HashSet<>();
        BTNode curr = node1;
        while(null != curr) {
            parents.add(curr);
            curr = parentMap.get(curr);
        }

        curr = node2;
        BTNode ancestor;
        int instance = 1;
        while(null != node2) {
            if(parents.contains(curr)) {
                break;
            }
            curr = parentMap.get(curr);
            instance++;
        }

        ancestor = curr;
        curr = node1;
        while (curr != ancestor) {
            curr = parentMap.get(curr);
            instance++;
        }

        return  instance;
    }

    public static void main(String[] args) {
        int testTimes = 10000;

        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(4);
            final int maxDistance = maxDistance(root);
            final int maxDistance2 = maxDistance2(root);

            if(maxDistance != maxDistance2) {
                BTUtil.print(root);
                System.out.println(maxDistance);
                System.out.println(maxDistance2);

                System.out.println("failed!");
                return;
            }
        }

        System.out.println("success!");
    }
}
