package c09;

import javax.crypto.Cipher;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author TK
 */
public class CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }


    // 2 7 9 4 6
    // 2 2 7 7 9 9 4 4 6 6
    public static Node copy(Node head) {
        if (null == head) {
            return head;
        }

        //copy next
        Node curr = head;
        Node next = null;
        while (null != curr) {
            Node temp = new Node(curr.value);

            next = curr.next;
            curr.next = temp;
            temp.next = next;
            curr = next;
        }

        //copy random
        curr = head;
        Node copy = null;
        while (null != curr) {
            next = curr.next.next;
            copy = curr.next;
            copy.rand = null == curr.rand ? null : curr.rand.next;

            curr = next;
        }

        //split
        Node newHead = head.next;
        curr = head;
        next = null;
        while (null != curr) {
            next = curr.next.next;
            copy = curr.next;

            curr.next = next;
            copy.next = null == next ? null : next.next;

            curr = next;
        }

        return newHead;

    }


    //container
    public static Node copy1(Node head) {

        // node -> rand
        final Map<Node, Node> oldToNew = new HashMap<>();

        Node curr = head;
        while (curr != null) {
            final Node temp = new Node(curr.value);

            oldToNew.put(curr, temp);
            curr = curr.next;
        }

        curr = head;
        while(null != curr) {
            oldToNew.get(curr).next = oldToNew.get(curr.next);
            oldToNew.get(curr).rand = oldToNew.get(curr.rand);

            curr = curr.next;

        }
        return oldToNew.get(head);
    }

    public static void printList(Node head) {
        while (null != head) {
            System.out.print(head.value + "");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head;
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printList(head);
        //final Node copy = copy(head);
        final Node copy = copy1(head);

        printList(copy);
    }
}
