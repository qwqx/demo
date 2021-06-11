package c11;

import util.BTNode;
import util.BTUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author TK
 */
public class LevelTraversalBT {

    public static void level(BTNode root) {
        final Queue<BTNode> queue = new LinkedList<>();

        queue.add(root);

        while(!queue.isEmpty()) {
            final BTNode poll = queue.poll();
            System.out.print(poll.value + " ");

            if(null != poll.left) {
                queue.add(poll.left);
            }

            if(null != poll.right) {
                queue.add(poll.right);
            }
        }
        System.out.println();

    }

    public static void main(String[] args) {
        final BTNode root = BTUtil.generateBT(5);
        BTUtil.print(root);
        level(root);
    }
}
