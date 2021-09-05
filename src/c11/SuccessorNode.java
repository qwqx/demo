package c11;

/**
 * 如果按中序遍历时，node节点的后继节点
 * 简单方法中序遍历，o(n)
 * ☆☆☆
 * @author TK
 */
public class SuccessorNode {

    public static class Node {

        public int value;
        public Node left;
        public Node right;
        public Node parent;

        Node(int v) {
            this.value = v;
        }

        Node(int v, Node parent) {
            this.value = v;
            this.parent = parent;
        }
    }

    public static Node successor(Node node) {

        if(null != node.right) {
            return getMostLeft(node.right);
        }else {
            Node parent = node.parent;
            while(null != parent && parent.right == node) {
                parent = parent.parent;
                node = node.parent;
            }
            return parent;
        }
    }

    public static Node getMostLeft(Node node) {
        while(null != node.left) {
            node = node.left;
        }
        return node;
    }

    public static void main(String[] args) {
        final Node root = new Node(1);

        root.left = new Node(2, root);
        root.right = new Node(3, root);

        root.left.left = new Node(4, root.left);
        root.left.right = new Node(5, root.left);

        root.left.right.right = new Node(6, root.left.right);

        System.out.println(successor(root.right));


    }
}
