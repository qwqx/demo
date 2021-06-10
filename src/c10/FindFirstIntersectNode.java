package c10;

import util.ListUtil;
import util.Node;

/**
 * @author TK
 * ☆☆
 */
public class FindFirstIntersectNode {


    public static Node find(Node head1, Node head2) {
        final Node loopNode1 = getLoopNode(head1);
        final Node loopNode2 = getLoopNode(head2);

        if (null == loopNode1 && null == loopNode2) {
            return findBothNoLoop(head1, head2);
        } else if (null != loopNode1 && null != loopNode2) {
            return findBothLoop(head1, head2);
        }

        return null;
    }

    public static Node findBothNoLoop(Node head1, Node head2) {

        return findIntersect(head1, head2, null);
    }

    public static Node findBothLoop(Node head1, Node head2) {
        final Node loopNode1 = getLoopNode(head1);
        final Node loopNode2 = getLoopNode(head2);

        // loopNode1 loopNode2都可
        if (loopNode1 != loopNode2) {
            return loopNode1;
        }

        return findIntersect(head1, head2, loopNode1);
    }

    public static Node findIntersect(Node head1, Node head2, Node end) {
        Node p = head1;
        int n = 0;
        Node q = head2;
        while (end != p) {
            p = p.next;
            n++;
        }

        while (end != q) {
            q = q.next;
            n--;
        }

        p = n > 0 ? head1 : head2;
        q = p == head1 ? head2 : head1;

        n = Math.abs(n);
        while (n > 0) {
            p = p.next;
            n--;
        }

        while (p != q && null!= p && null !=q) {
            p = p.next;
            q = q.next;
        }

        return p;
    }


    public static Node getLoopNode(Node head) {
        if (null == head || null == head.next || null == head.next.next) {
            return null;
        }

        Node slow = head.next;
        Node fast = head.next.next;

        while (slow != fast) {
            if (null == fast.next || null == fast.next.next) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }

        //slow==fast

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }


    public static void main(String[] args) {

        int maxLen = 10;
        final Node head = ListUtil.generateListNode(maxLen, true);

        final Node loopNode = getLoopNode(head);
        System.out.println(loopNode.value);

        System.out.println("============================");
        // both no loop, not intersect
        final Node head1 = ListUtil.generateListNode(maxLen);
        final Node head2 = ListUtil.generateListNode(maxLen);

        ListUtil.print(head1);
        ListUtil.print(head2);
        System.out.println(find(head1, head2));

        System.out.println("============================");
        // both no loop, intersect
        final Node head3 = ListUtil.generateListNode(maxLen);
        final Node randomNode = ListUtil.getRandomNode(head3);
        final Node head4 = ListUtil.generateListNode(maxLen);
        final Node tailNode = ListUtil.getTailNode(head4);
        tailNode.next = randomNode;


        ListUtil.print(head3);
        ListUtil.print(head4);
        System.out.println("intersect1:" + randomNode.value);
        System.out.println(find(head3, head4).value);

        System.out.println("============================");
        // both loop, intersect

        final Node head5 = ListUtil.generateListNode(maxLen, true);
        final Node head6 = ListUtil.generateListNode(maxLen);
        final Node randomNode1 = ListUtil.getRandomNode(head5);
        final Node tailNode1 = ListUtil.getTailNode(head6);
        tailNode1.next = randomNode1;

        ListUtil.print(head5);
        ListUtil.print(head6);
        System.out.println("intersect2:" + randomNode1.value);
        System.out.println(find(head5, head6).value);



    }


}
