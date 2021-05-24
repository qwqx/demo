package three;

import java.util.Stack;

/**
 * @author TK
 */
public class GetMinStack {

    public static class MyStack{
        public Stack<Integer> dataStack = new Stack<>();
        public Stack<Integer> minStack = new Stack<>();

        public void push(int value) {
            dataStack.push(value);
            Integer minTop = null;
            if(!minStack.empty()) {
                minTop = minStack.peek();
            }
            if(null == minTop) {
                minStack.push(value);
            }else if(value <= minTop){
                minStack.push(value);
            }
        }

        public Integer pop() {
            final Integer pop = dataStack.pop();
            if(pop.equals(getMin())) {
                minStack.pop();
            }

            return pop;
        }

        public Integer getMin() {
            final Integer min = minStack.peek();
            return min;
        }
    }


    public static void main(String[] args) {

        final MyStack myStack = new MyStack();
        myStack.push(3);
        myStack.push(6);
        myStack.push(1);
        myStack.push(7);
        myStack.push(2);
        myStack.push(5);
        System.out.println(myStack.getMin());

        myStack.pop();
        System.out.println(myStack.getMin());
        myStack.pop();
        System.out.println(myStack.getMin());
        myStack.pop();
        System.out.println(myStack.getMin());
        myStack.pop();
        System.out.println(myStack.getMin());
        myStack.pop();
        System.out.println(myStack.getMin());
    }
}
