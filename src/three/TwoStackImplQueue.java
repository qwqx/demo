package three;

import java.util.Stack;

/**
 * @author TK
 */
public class TwoStackImplQueue {

    public static class MyQueue{

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> help = new Stack<>();

        public void add(int value) {
            stack.push(value);
            move();
        }

        public Integer poll() {
            move();
            return help.pop();


        }

        public Integer peek() {
            if(!help.isEmpty()) {
                return help.peek();
            }else {
                move();
                return help.peek();
            }

        }

        public void move() {
            if(!help.isEmpty()) {
                return;
            }

            while(!stack.isEmpty()) {
               help.push(stack.pop());
            }
        }


    }

    public static void main(String[] args) {
        final MyQueue myQueue = new MyQueue();

        myQueue.add(2);
        myQueue.add(6);
        myQueue.add(7);
        myQueue.add(2);
        myQueue.add(8);
        myQueue.add(2);
        myQueue.add(6);

        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
        System.out.println(myQueue.poll());
    }
}
