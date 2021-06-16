package c10;

import util.BTNode;
import util.BTUtil;

import java.util.Stack;

/**
 * @author TK
 * ☆☆
 */
public class UnRecursiveTraversalBT {

    /**
     * ☆☆
     * @param root
     * 同中序遍历，只是打印位置不同
     */
    public static void pre(BTNode root) {
        if (null == root) {
            return;
        }
        final Stack<BTNode> stack = new Stack<>();
        BTNode curr = root;
        while (!stack.isEmpty() || null != curr) {
            if (null != curr) {
                System.out.print(curr.value + " ");
                stack.push(curr);
                curr = curr.left;
            } else if (!stack.isEmpty()) {
                BTNode pop = stack.pop();
                curr = pop.right;
            }
        }

        System.out.println();

    }

    /**
     * ☆☆
     * @param root
     */
    public static void pre2(BTNode root) {
        if (null == root) {
            return;
        }
        final Stack<BTNode> stack = new Stack<>();
        stack.push(root);
        BTNode curr = root;
        while (!stack.isEmpty()) {
            final BTNode pop = stack.pop();
            System.out.print(pop.value + " ");
            if (null != pop.right) {
                stack.push(pop.right);
            }
            if (null != pop.left) {
                stack.push(pop.left);
            }
        }

        System.out.println();

    }

    /**
     *☆☆
     * @param root
     */
    public static void in(BTNode root) {

        if (null == root) {
            return;
        }
        final Stack<BTNode> stack = new Stack<>();
        BTNode curr = root;
        //1.
        /*while (!stack.isEmpty() || null != curr) {
            if (null != curr) {
                stack.push(curr);
                curr = curr.left;
            } else if (!stack.isEmpty()) {
                BTNode pop = stack.pop();
                System.out.print(pop.value + " ");
                curr = pop.right;
            }
        }*/

        //2.
        while(!stack.isEmpty() || null != curr) {
            while(null != curr) {
                stack.push(curr);
                curr = curr.left;
            }

            final BTNode pop = stack.pop();
            System.out.print(pop.value + " ");
            curr = pop.right;
        }

        System.out.println();
    }


    /**
     * ☆☆@
     * @param root
     */
    public static void post1(BTNode root) {

        if(null == root) {
            return;
        }
        final Stack<BTNode> stack1 = new Stack<>();
        final Stack<BTNode> stack2 = new Stack<>();

        stack1.push(root);

        while(!stack1.isEmpty()) {
            final BTNode pop = stack1.pop();
            stack2.push(pop);// 头右左

            if(null != pop.left) {
                stack1.push(pop.left);
            }
            if(null != pop.right) {
                stack1.push(pop.right);
            }
        }

        while (!stack2.isEmpty()) { //左右头
            System.out.print(stack2.pop().value + " ");
        }
        System.out.println();
    }

    /**
     * ☆☆☆
     * @param root
     */
    public static void post2(BTNode root) {
        if(null == root) {
            return;
        }
        final Stack<BTNode> stack1 = new Stack<>();

        stack1.push(root);
        BTNode pre = root;
        BTNode curr;
        int i = 0;
        while(!stack1.isEmpty()) {
            curr = stack1.peek();
            if(null != curr.left && curr.left != pre && curr.right != pre) {
                stack1.push(curr.left);
            }else if(null != curr.right && pre != curr.right) {
                stack1.push(curr.right);
            }else {
                System.out.print(stack1.pop().value + " ");
                pre = curr;
            }

            if(i++ > 20) {
                break;
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final BTNode root = BTUtil.generateBT(4, 20D);

        BTUtil.print(root);
        pre(root);
        pre2(root);
        in(root);
        System.out.println("===============");
        post1(root);
        post2(root);


    }
}
