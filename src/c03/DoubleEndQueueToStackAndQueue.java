package c03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 双端队列实现栈/队列
 * @author TK
 */
public class DoubleEndQueueToStackAndQueue {

    public static class DoubleNode<T> {
        public T value;
        public DoubleNode last;
        public DoubleNode next;

        DoubleNode(T v) {
            this.value = v;
        }
    }

    public static class DoubleEndQueue<T> {
        public DoubleNode<T> head;
        public DoubleNode<T> tail;

        public void addFromHead(T t) {
            final DoubleNode<T> node = new DoubleNode<>(t);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.last = node;
                head = node;
            }
        }

        public void addFromBottom(T t) {
            final DoubleNode<T> node = new DoubleNode<>(t);
            if (tail == null) {
                head = node;
                tail = node;
            }
            node.last = tail;
            tail.next = node;
            tail = node;
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            DoubleNode<T> pop = head;
            if(head == tail) {
                head = null;
                tail = null;
            }else{
                head = head.next;
                head.last = null;
                pop.next = null;
            }
            return pop.value;
        }

        public T popFromBottom() {
            if (tail == null) {
                return null;
            }
            DoubleNode<T> pop = tail;
            if(head == tail) {
                head = null;
                tail = null;
            }else{
                tail = tail.last;
                tail.next = null;
                pop.last = null;
            }

            return pop.value;
        }

        public boolean isEmpty() {
            return head == null;
        }

    }

    public static class MyStack<T> {
        DoubleEndQueue<T> doubleEndQueue = new DoubleEndQueue<> ();

        public T pop() {
            return doubleEndQueue.popFromHead();
        }

        public void push(T t) {
            doubleEndQueue.addFromHead(t);
        }
    }

    public static class MyQueue<T> {
        DoubleEndQueue<T> doubleEndQueue = new DoubleEndQueue<> ();

        public T poll() {
            return doubleEndQueue.popFromHead();
        }

        public void push(T t) {
            doubleEndQueue.addFromBottom(t);
        }
    }

    public static void print(DoubleEndQueue doubleEndQueue) {
        DoubleNode p = doubleEndQueue.head;
        while (p != null) {
            System.out.print(p.value + " ");
            p = p.next;
        }
        System.out.println();

    }

    public static void main(String[] args) {

        int maxValue = 100;
        int len = 20;
        int testTimes = 10000;

        final MyStack<Integer> myStack = new MyStack<>();
        final Stack<Integer> stack = new Stack<>();

        final MyQueue<Object> myQueue = new MyQueue<>();
        final Queue<Object> queue = new LinkedList<>();
        while(testTimes > 0) {

            while(len > 0) {
                final double random = Math.random();
                final int value = (int) (Math.random() * maxValue) + 1;
                if(random > 0.4 || stack.empty()) {
                    myStack.push(value);
                    stack.push(value);
                }else {
                    if(!myStack.pop().equals(stack.pop())) {
                        System.out.println("failed");
                        break;
                    }
                }

                if(random > 0.4 || queue.isEmpty()) {
                    myQueue.push(value);
                    queue.add(value);
                }else {
                    if(!myQueue.poll().equals(queue.poll())) {
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
