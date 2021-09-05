package c11;

import java.util.*;

/**
 * n叉树转2叉树
 * ☆☆☆
 * @author TK
 */
public class EncodeNTToBT {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // 提交时不要提交这个类
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // 只提交这个类即可
    static class Codec {
        // Encodes an n-ary tree to a binary tree.
        public TreeNode encode(Node root) {
            if (null == root) {
                return null;
            }

            final TreeNode br = new TreeNode(root.val);
            br.left = en(root.children);
            return br;
        }

        private TreeNode en(List<Node> children) {
            if(null == children) {
                return  null;
            }
            TreeNode head = null;
            TreeNode tail = null;
            for (Node child : children) {
                final TreeNode c = new TreeNode(child.val);
                if (null == head) {
                    head = c;
                } else {
                    tail.right = c;
                }

                tail = c;
                c.left = en(child.children);
            }
            return head;
        }

        // Decodes your binary tree to an n-ary tree.
        public Node decode(TreeNode root) {

            if (null == root) {
                return null;
            }

            return new Node(root.val, de(root.left));
        }

        public List<Node> de(TreeNode root) {

            final List<Node> children = new ArrayList<>();
            TreeNode curr = root;
            while (null != curr) {
                final Node child = new Node(curr.val, de(curr.left));
                children.add(child);
                curr = curr.right;
            }

            return children;
        }

    }

    public static void main(String[] args) {
        final Node root = new Node(1);
        final Node node2 = new Node(4);
        root.children = Arrays.asList(new Node(2), new Node(3), node2, new Node(5));

        node2.children = Arrays.asList(new Node(6), new Node(7));

        final Codec codec = new Codec();
        final TreeNode encode = codec.encode(root);

        final Node decode = codec.decode(encode);

        System.out.println();

    }
}
