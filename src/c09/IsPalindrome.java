package c09;

/**
 * @author TK
 */
public class IsPalindrome {

    static class Node {
        public int value;
        public Node next;

        Node(int v) {
            this.value = v;
        }
    }

    // 12345 54321
    // 12345 4321
    public static boolean isPalindrome1(Node head) {
        final Node mid = midOrUpMid(head);

        // reverse right
        Node curr = mid.next;
        Node pre = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }


        Node p = pre;
        Node q = head;
        while(null != p && null != q) {
            if(p.value != q.value) {
                return false;
            }
            p = p.next;
            q = q.next;
        }

        //recover
        curr = pre;
        pre = null;
        next = null;
        while(null != curr) {
            next = curr.next;
            curr.next = pre;

            pre = curr;
            curr = next;
        }

        return true;
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

    public static void printList(Node head) {
        while(null != head) {
            System.out.print(head.value + "");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final Node root = new Node(1);
        root.next = new Node(2);
        root.next.next = new Node(3);
        root.next.next.next = new Node(4);
        root.next.next.next.next = new Node(3);
        root.next.next.next.next.next = new Node(2);
        root.next.next.next.next.next.next = new Node(1);

        printList(root);
        System.out.println(isPalindrome1(root));
        printList(root);
    }
}
