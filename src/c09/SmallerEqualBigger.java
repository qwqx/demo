package c09;

/**
 * @author TK
 */
public class SmallerEqualBigger {

    static class Node {
        public int value;
        public Node next;

        Node(int v) {
            this.value = v;
        }
    }


    public static Node partition1(Node head, int pivot) {

        int size = 0;
        Node p = head;
        while(null != p) {
            p = p.next;
            size++;
        }

        final Node[] nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = head;
            head = head.next;
        }

        arrPartition(nodes, pivot);

        int i = 0;
        for (; i < size - 1; i++) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[i].next = null;
        return nodes[0];

    }
    public static void arrPartition(Node[] arr, int pivot) {

        int sIdx = -1;
        int mIsx = arr.length;
        for (int i = 0; i < mIsx; i++) {
            if (arr[i].value < pivot) {
                swap(arr, ++sIdx, i);
            }
            if(arr[i].value > pivot) {
                swap(arr, --mIsx, i--);
            }

        }

    }

    public static void swap(Node[] arr, int i, int j) {
        Node temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static Node partition(Node head, int pivot) {
        Node sH = null, sT = null, eH = null, eT = null, mH = null, mT = null;

        while (null != head) {
            Node next = head.next;
            if (head.value < pivot) {
                if (null == sH) {
                    sH = sT = head;
                } else {
                    sT.next = head;
                    sT = sT.next;
                }
            } else if (head.value == pivot) {
                if (null == eH) {
                    eH = eT = head;
                } else {
                    eT.next = head;
                    eT = eT.next;
                }
            } else if (head.value > pivot) {
                if (null == mH) {
                    mH = mT = head;
                } else {
                    mT.next = head;
                    mT = mT.next;
                }
            }
            head.next = null;
            head = next;
        }

        Node newHead = null != sH ? sH : (null != eH ? eH : mH);

        if (null != sT) {
            sT.next = null != eH ? eH : mH;
        }

        if (null != eT) {
            eT.next = mH;
        }

        return newHead;
    }


    public static void printList(Node head) {
        while (null != head) {
            System.out.print(head.value + "");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        final Node root = new Node(5);
        root.next = new Node(7);
        root.next.next = new Node(1);
        root.next.next.next = new Node(8);
        root.next.next.next.next = new Node(9);
        root.next.next.next.next.next = new Node(3);
        root.next.next.next.next.next.next = new Node(7);
        root.next.next.next.next.next.next.next = new Node(9);

        printList(root);
       // partition(root, 7);
        partition1(root, 7);
        printList(root);

    }
}
