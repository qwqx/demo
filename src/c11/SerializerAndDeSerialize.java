package c11;

import util.BTNode;
import util.BTUtil;
import util.ListUtil;

import java.util.*;

/**
 * @author TK
 */
public class SerializerAndDeSerialize {

    /*
     * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
     * 以下代码全部实现了。
     * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
     * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
     * 比如如下两棵树
     *         __2
     *        /
     *       1
     *       和
     *       1__
     *          \
     *           2
     * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
     *
     * */

    public static Queue<Integer> preSerialize(BTNode root) {
        final Queue<Integer> queue = new LinkedList<>();
        pre(root, queue);
        return queue;
    }

    public static void pre(BTNode root, Queue<Integer> queue) {
        if (null == root) {
            queue.add(null);
            return;
        }

        queue.add(root.value);
        pre(root.left, queue);
        pre(root.right, queue);
    }

    public static BTNode dePreSerialize(Queue<Integer> queue) {
        if (null == queue || queue.isEmpty()) {
            return null;
        }

        final Integer rootValue = queue.poll();
        if (null == rootValue) {
            return null;
        }

        final BTNode root = new BTNode(rootValue);
        root.left = dePreSerialize(queue);
        root.right = dePreSerialize(queue);

        return root;
    }


    public static Queue<Integer> inSerialize(BTNode root) {
        final Queue<Integer> queue = new LinkedList<>();
        in(root, queue);
        return queue;
    }

    public static void in(BTNode root, Queue<Integer> queue) {
        if (null == root) {
            queue.add(null);
            return;
        }

        in(root.left, queue);
        queue.add(root.value);
        in(root.right, queue);
    }


    public static Queue<Integer> postSerialize(BTNode root) {
        final Queue<Integer> queue = new LinkedList<>();
        post(root, queue);
        return queue;
    }

    public static void post(BTNode root, Queue<Integer> queue) {
        if (null == root) {
            queue.add(null);
            return;
        }

        post(root.left, queue);
        post(root.right, queue);
        queue.add(root.value);
    }

    /**
     * ☆☆
     *
     * @param queue
     * @return
     */
    public static BTNode dePostSerialize(Queue<Integer> queue) {
        if (null == queue || queue.isEmpty()) {
            return null;
        }

        //左右中 -> 中右左
        final Stack<Integer> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }

        return dePost(stack);
    }

    public static BTNode dePost(Stack<Integer> stack) {
        final Integer rootValue = stack.pop();
        if (null == rootValue) {
            return null;
        }

        final BTNode root = new BTNode(rootValue);
        root.right = dePost(stack);
        root.left = dePost(stack);
        return root;
    }

    public static Queue<Integer> levelSerialize(BTNode root) {
        final Queue<Integer> ans = new LinkedList<>();
        final Queue<BTNode> queue = new LinkedList<>();
        queue.add(root);

        if (null == root) {
            ans.add(null);
            return ans;
        } else {
            ans.add(root.value);
        }

        while (!queue.isEmpty()) {
            final BTNode poll = queue.poll();
            if (null != poll.left) {
                queue.add(poll.left);
                ans.add(poll.left.value);
            } else {
                ans.add(null);
            }

            if (null != poll.right) {
                queue.add(poll.right);
                ans.add(poll.right.value);
            } else {
                ans.add(null);
            }

        }

        return ans;

    }

    /**
     * @param queue
     * @return
     */
    public static BTNode deLevelSerialize(Queue<Integer> queue) {

        if (null == queue || queue.isEmpty()) {
            return null;
        }

        final Integer rootValue = queue.poll();
        if (null == rootValue) {
            return null;
        }

        final BTNode root = new BTNode(rootValue);
        final Queue<BTNode> helpQueue = new LinkedList<>();
        helpQueue.add(root);
        while (!helpQueue.isEmpty()) {
            final BTNode h = helpQueue.poll();

            Integer left = queue.poll();
            if (null != left) {
                h.left = new BTNode(left);
                helpQueue.add(h.left);
            } else {
                h.left = null;
            }


            Integer right = queue.poll();
            if (null != right) {
                h.right = new BTNode(right);
                helpQueue.add(h.right);
            } else {
                h.right = null;
            }

        }
        return root;

    }

    public static void main(String[] args) {

        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            final BTNode root = BTUtil.generateBT(3, 20D);
            //BTUtil.print(root);
            final Queue<Integer> list1 = preSerialize(root);
            //list1.forEach(s -> System.out.print(s + ""));
            final BTNode preNode = dePreSerialize(list1);
            //BTUtil.print(dePreSerialize(list1));

            final Queue<Integer> list2 = inSerialize(root);
            //list2.forEach(s -> System.out.print(s + ""));

            final Queue<Integer> list3 = postSerialize(root);
            //list3.forEach(s -> System.out.print(s + ""));
            final BTNode postNode = dePostSerialize(list3);
            //BTUtil.print(dePostSerialize(list3));

            final Queue<Integer> list4 = levelSerialize(root);
            //list4.forEach(s -> System.out.print(s + ""));
            final BTNode levelNode = deLevelSerialize(list4);
            //BTUtil.print(deLevelSerialize(list4));

            if(!BTUtil.same(preNode, postNode) && !BTUtil.same(levelNode, postNode)) {
                System.out.println("failed");
                return;
            }
        }

        System.out.println("success");


    }
}
