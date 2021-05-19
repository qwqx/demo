package three;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.LinkedList;
import java.util.List;

/**
 * @author TK
 */
public class ReverseList {

    public static class Node {
        public int value;
        public Node next;

        Node(int v) {
            this.value = v;
        }
    }

    public static class DoubleNode {
        public int value;
        public DoubleNode last;
        public DoubleNode next;

        DoubleNode(int v) {
            this.value = v;
        }
    }

    public static Node reverseLinkedList(Node head) {

        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;

            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseLinkedList(DoubleNode head) {

        DoubleNode pre = null;
        DoubleNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;

            pre = head;
            head = next;
        }
        return pre;
    }

    public static Node generateRandomLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));

        if (size <= 0) {
            return null;
        }

        Node head = new Node((int) (Math.random() * (value + 1)));
        Node pre = head;
        while (size > 0) {
            Node curr = new Node((int) (Math.random() * (value + 1)));
            pre.next = curr;
            pre = curr;

            size--;
        }

        return head;
    }

    public static DoubleNode generateRandomDoubleLinkedList(int len, int value) {
        int size = (int) (Math.random() * (len + 1));

        if (size <= 0) {
            return null;
        }

        DoubleNode head = new DoubleNode((int) (Math.random() * (value + 1)));
        DoubleNode pre = head;
        while (size > 0) {
            DoubleNode curr = new DoubleNode((int) (Math.random() * (value + 1)));
            pre.next = curr;
            curr.last = pre;

            pre = curr;

            size--;
        }

        return head;
    }

    public static List<Integer> getLinkedListOriginOrder(Node head) {
        List<Integer> list = new LinkedList<>();
        while (null != head) {
            list.add(head.value);
            head = head.next;
        }
        return list;

    }

    public static List<Integer> getLinkedListOriginOrder(DoubleNode head) {
        List<Integer> list = new LinkedList<>();
        while (null != head) {
            list.add(head.value);
            head = head.next;
        }
        return list;

    }

    public static boolean checkLinkedListReversed(List<Integer> originList, Node reverseHead) {
        int size = originList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (originList.get(i) != reverseHead.value) {
                return false;
            }
            reverseHead = reverseHead.next;
        }
        return true;

    }

    public static boolean checkLinkedListReversed(List<Integer> originList, DoubleNode reverseHead) {
        int size = originList.size();
        for (int i = size - 1; i >= 0; i--) {
            if (originList.get(i) != reverseHead.value) {
                return false;
            }
            reverseHead = reverseHead.next;
        }
        return true;

    }

    public static void print(Node head) {
        while (head != null) {
            System.out.println(head.value + " ");
        }
        System.out.println();
    }

    public static void print(DoubleNode head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int maxLen = 5;
        int maxValue = 10;
        int testTimes = 10000;


        while (testTimes > 0) {
            final Node head = generateRandomLinkedList(maxLen, maxValue);
            final List<Integer> list = getLinkedListOriginOrder(head);
            final Node reverseHead = reverseLinkedList(head);
            if (!checkLinkedListReversed(list, reverseHead)) {
                System.out.print("reverse1 failed!");
                break;
            }

            System.out.print("=======================");
            final DoubleNode head2 = generateRandomDoubleLinkedList(maxLen, maxValue);
            print(head2);
            final List<Integer> list2 = getLinkedListOriginOrder(head2);
            final DoubleNode reverseHead2 = reverseLinkedList(head2);
            print(reverseHead2);
            if (!checkLinkedListReversed(list2, reverseHead2)) {
                System.out.print("reverse2 failed!");
                break;
            }

            testTimes--;
        }

        if (testTimes == 0) {
            System.out.print("success");
        }


    }
}
