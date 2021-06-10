package util;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class ListUtil {

    static int maxValue = 100;

    public static Node generateListNode(int maxLen, boolean loop) {

        int len = (int) (Math.random() * maxLen) + 1;
        int loopIndex = (int) (Math.random() * len);

        int value = (int) (Math.random() * maxValue);
        Node head = new Node(value);
        Node tail = head;
        Node loopNode = null;
        for(int i = 0; i < len; i++) {
            value = (int) (Math.random() * maxValue);
            Node node = new Node(value);

            tail.next = node;
            tail = tail.next;

            if(loopIndex == i) {
                loopNode = node;
            }
        }

        if(loop) {
            tail.next = loopNode;
        }
        return head;
    }

    public static Node generateListNode(int maxLen) {
        return generateListNode(maxLen, false);
    }

    public static Node getRandomNode(Node head) {
        int maxStep = 100;
        Node p = head;
        int n = 0;
        while(null != p && n < maxStep) {
            p = p.next;
            n++;
        }

        int rand = (int) (Math.random() * n);

        p = head;
        while(rand > 0) {
            p = p.next;
            rand--;
        }

        return p;
    }

    public static Node getTailNode(Node head) {
        Node p = head;
        int n = 0;
        while(null != p) {
            p = p.next;
            n++;
        }

        int rand = (int) (Math.random() * n);

        p = head;
        while(n > 1) {
            p = p.next;
            n--;
        }

        return p;
    }

    public static void print(Node head) {
        List<Node> list = new ArrayList<>();
        while(null != head) {
            System.out.print(head.value + " ");
            if(list.contains(head)) {
                break;
            }
            list.add(head);
            head = head.next;
        }
        System.out.println();
    }


}
