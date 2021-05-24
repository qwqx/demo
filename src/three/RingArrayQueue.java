package three;

import java.util.LinkedList;

/**
 * @author TK
 */
public class RingArrayQueue {

    public int[] arr;
    public int head;
    public int tail;
    public int size;
    public int limit;

    RingArrayQueue(int limit) {
        arr = new int[limit];
        this.limit = limit;
    }

    public boolean push(int value) {
        if(size >= limit) {
            return false;
        }
        arr[head] = value;
        head = incrIndex(head);
        size++;
        return true;
    }

    public int pop() {
        if(size == 0) {
            return -1;
        }
        int pop = arr[tail];
        tail = incrIndex(tail);
        size--;
        return pop;
    }

    public boolean isEmpty() {
        return size ==0;
    }

    int incrIndex(int index) {
        if(index == limit - 1) {
            return 0;
        }else {
            return index + 1;
        }
    }

    public static void main(String[] args) {
        int maxSize = 10;
        int maxValue = 100;
        int testTimes = 10000;

        final RingArrayQueue myQueue = new RingArrayQueue(maxSize);
        final LinkedList<Integer> queue = new LinkedList<>();
        while(testTimes > 0) {
            int len = maxSize;
            while(len > 0) {
                final int value = (int) (Math.random() * maxValue + 1);
                final double random = Math.random();
                if(random > 0.3 || queue.isEmpty()) {
                    if(myQueue.push(value)) {
                        queue.add(value);
                    }
                }else {
                    if(!queue.poll().equals(myQueue.pop())) {
                        System.out.println("failed");
                        break;
                    }

                }


                len--;
            }
            testTimes--;

        }
        if(testTimes == 0) {
            System.out.println("success");
        }

    }

}
