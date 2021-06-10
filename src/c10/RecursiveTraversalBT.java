package c10;

import util.BTNode;
import util.BTUtil;

/**
 * @author TK
 * ☆
 */
public class RecursiveTraversalBT {

    public static void pre(BTNode root) {
        if(null == root) {
            return;
        }

        System.out.print(root.value + " ");
        pre(root.left);
        pre(root.right);
    }

    public static void in(BTNode root) {
        if(null == root) {
            return;
        }

        in(root.left);
        System.out.print(root.value + " ");
        in(root.right);
    }

    public static void post(BTNode root) {
        if(null == root) {
            return;
        }

        post(root.left);
        post(root.right);
        System.out.print(root.value + " ");
    }

    public static void main(String[] args) {
        final BTNode root = BTUtil.generateBT(5);


        BTUtil.print(root);
        pre(root);
        System.out.println();
        in(root);
        System.out.println();
        post(root);
    }
}
