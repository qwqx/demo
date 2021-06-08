package c09;

import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class LinkedListMid {

    static class Node {
        public int value;
        public Node next;

        Node(int v) {
            this.value = v;
        }
    }

    public static Node midOrUpMid(Node head) {
        if (null == head || null == head.next || null == head.next.next) {
            return head;
        }
        Node fast = head;
        Node slow = head;
        //1 2 3 4 5
        //1 2 3 4 5 6
        while (null != fast.next && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    //先走一步， 再按midOrUpMid走
    public static Node midOrDownMid(Node head) {
        if (null == head || null == head.next) {
            return head;
        }
        //先走一步
        Node fast = head.next;
        Node slow = head.next;

        while (null != fast.next && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    // fast先走两步，再按照midOrUpMid走
    public static Node midOrUpMidPre(Node head) {
        if (null == head || null == head.next || null == head.next.next) {
            return null;
        }
        //1 2 3 4 5
        //1 2 3 4 5 6

        // fast先走
        Node fast = head.next.next;
        Node slow = head;

        while (null != fast.next && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static Node midOrDownMidPre(Node head) {
        if (null == head || null == head.next) {
            return null;
        }
        if (null == head.next.next) {
            return head;
        }
        //先走一步
        Node fast = head.next;
        Node slow = head;

        while (null != fast.next && null != fast.next.next) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    //1 2 3 4 5
    //1 2 3 4 5 6
    public static Node midOrUpMidRight(Node head) {
        List<Node> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }

        if (list.isEmpty()) {
            return null;
        }

        return list.get((list.size() - 1) / 2);
    }

    //1 2 3 4 5
    //1 2 3 4 5 6
    public static Node midOrDownMidRight(Node head) {
        List<Node> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }

        if (list.isEmpty()) {
            return null;
        }
        return list.get((list.size()) / 2);
    }

    //1 2 3 4 5
    //1 2 3 4 5 6
    public static Node midOrUpMidPreRight(Node head) {
        List<Node> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }

        if (list.isEmpty() || list.size() <= 2) {
            return null;
        }

        return list.get((list.size() - 1) / 2 - 1);
    }

    public static Node midOrDownMidPreRight(Node head) {
        List<Node> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }

        if (list.isEmpty() || list.size() <= 1) {
            return null;
        }
        return list.get((list.size()) / 2 - 1);
    }


    public static void main(String[] args) {
        int testTimes = 10000;

        Node root = new Node(10);
        root.next = new Node(15);
        root.next.next = new Node(20);
        root.next.next.next = new Node(25);
        root.next.next.next.next = new Node(30);
        root.next.next.next.next.next = new Node(35);
        root.next.next.next.next.next.next = new Node(40);

        System.out.println(midOrUpMid(root)== midOrUpMidRight(root));
        System.out.println(midOrDownMid(root) == midOrDownMid(root));
        System.out.println(midOrUpMidPre(root) == midOrUpMidPre(root));
        System.out.println(midOrDownMidPre(root) == midOrUpMidPre(root));
    }

}
