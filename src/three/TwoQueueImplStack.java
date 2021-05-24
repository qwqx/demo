package three;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author TK
 */
public class TwoQueueImplStack {

    public static class MyStack{
        public Queue<Integer> q = new LinkedList<>();
        public Queue<Integer> p = new LinkedList<>();


        public void push(int value) {
            q.add(value);
        }

        public Integer pop() {
            while(q.size() > 1) {
                p.add(q.poll());
            }

            final Integer top = q.poll();
            //swap(p, q);
            Queue temp = q;
            q = p;
            p = temp;
            return top;
        }

        public void swap(Queue q, Queue p) {

        }


        public static void main(String[] args) {
            final MyStack myStack = new MyStack();
            myStack.push(2);
            myStack.push(6);
            myStack.push(4);
            myStack.push(9);
            myStack.push(1);

            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
        }


    }
}
