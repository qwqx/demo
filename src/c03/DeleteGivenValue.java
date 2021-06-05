package c03;

import java.util.LinkedList;
import java.util.List;

/**
 * @author TK
 */
public class DeleteGivenValue {

    public static class Node {
        public int value;
        public Node next;

        Node(int v) {
            this.value = v;
        }
    }

    public static Node delete(Node head, int value) {

        while(head != null && head.value == value) {
            head = head.next;
        }

        Node pre = head;
        Node curr = head;
        while(curr != null) {
            if(curr.value != value) {

                pre = curr;
            }else {
                pre.next = curr.next;
            }
            curr = curr.next;
        }


        return head;
    }

    public static List<Integer> deleteTest(Node head, int value) {
        final List<Integer> list = nodeToList(head);
        list.removeIf(s -> s == value);
        return list;
    }

    public static int getRandomValue(Node head) {

        int random = (int) (Math.random() * 10);
        int delValue = 0;
        while(head != null && random > 0) {
            delValue = head.value;
            head = head.next;
            random--;
        }
        return delValue;
    }

    public static List<Integer> nodeToList(Node head) {
        final List<Integer> list = new LinkedList<>();
        while(head != null) {
            list.add(head.value);
            head = head.next;
        }
        return list;
    }

    public static Node listCopy(Node head) {

        Node newHead = null;
        Node pre = null;
        while(head != null) {
            final Node node = new Node(head.value);
            if(null == newHead ) {
                newHead = node;
                pre = node;

            }else {
                pre.next = node;
                pre = node;
            }
            head = head.next;
        }
        return newHead;
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

    public static boolean listEq(List<Integer> l1, List<Integer> l2) {
        if(l1.size() != l2.size()) {
            return false;
        }

        final int size = l1.size();
        for(int i = 0; i < size; i++) {
            if(!l1.get(i).equals(l2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static void print(Node head) {
        System.out.println();
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        int len = 10;
        int value = 100;
        int testTimes = 1000;

        while(testTimes > 0) {
            final Node head = generateRandomLinkedList(len, value);
            final Node copyHead = listCopy(head);
            int deleteValue = getRandomValue(head);
            print(head);
            System.out.println("delete:" + deleteValue);
            final Node deleted = delete(head, deleteValue);
            print(deleted);
            final List<Integer> deleteList1 = nodeToList(deleted);
            final List<Integer> deleteList2 = deleteTest(copyHead, deleteValue);

            if(!listEq(deleteList1, deleteList2)) {
                System.out.println("failed!");
                break;
            }

            testTimes--;
        }

        if(testTimes == 0) {
            System.out.println("success!");
        }

    }
}
