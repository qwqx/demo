package c11;

import util.BTNode;
import util.BTUtil;

import javax.sql.PooledConnection;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ☆☆
 * @author TK
 */
public class TreeMaxWidth {

    public static int maxWidth(BTNode root) {
        if(null == root) {
            return 0;
        }

        final LinkedList<BTNode> queue = new LinkedList<>();
        queue.add(root);

        BTNode currEnd = root;
        BTNode nextEnd = null;
        int maxWidth = 1;
        int currWidth = 0;
        while (!queue.isEmpty()) {

            final BTNode poll = queue.poll();
            currWidth++;

            if(null != poll.left) {
                queue.add(poll.left);
            }

            if(null != poll.right) {
                queue.add(poll.right);
            }


            if(poll == currEnd) {
                currEnd = queue.peekLast();
                maxWidth = Math.max(maxWidth, currWidth);
                currWidth = 0;
            }
        }
        return maxWidth;
    }

    public static long maxWidthMap(BTNode root) {
        final Queue<BTNode> queue = new LinkedList<>();
        final HashMap<BTNode, Integer> map = new HashMap<>();

        queue.add(root);
        map.put(root, 1);
        int currlevel = 1;
        while(!queue.isEmpty()) {
            final BTNode poll = queue.poll();
            final Integer currNodeLevel = map.get(poll);
            if(null != poll.left) {
                queue.add(poll.left);
                map.put(poll.left, currNodeLevel + 1);
            }

            if(null != poll.right) {
                queue.add(poll.right);
                map.put(poll.right, currNodeLevel + 1);
            }

        }

        final Map<Integer, Long> collect = map.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.counting()));


        return collect.values().stream().max((s1,s2) -> (int) (s1-s2)).get();

    }

    public static void main(String[] args) {
        final BTNode root = BTUtil.generateBT(5, 20D);
        BTUtil.print(root);
        System.out.println(maxWidth(root));
        System.out.println(maxWidthMap(root));
    }
}
