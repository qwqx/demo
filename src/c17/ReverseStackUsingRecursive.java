package c17;

import java.util.Stack;

/**
 * @author TK
 */
public class ReverseStackUsingRecursive {

    /**
     * ☆☆☆
     * @param stack
     */
    public static void reverse(Stack<Integer> stack) {

        if(stack.isEmpty()) {
            return;
        }

        final int last = f(stack);
        reverse(stack);
        stack.push(last);
    }

    public static int f(Stack<Integer> stack) {
        if(stack.size() == 1) {
            return stack.pop();
        }else {
            final Integer pop = stack.pop();
            final int last = f(stack);
            stack.push(pop);
            return last;
        }

    }

    public static void print(Stack<Integer> stack) {
        final Stack<Integer> help = new Stack<>();
        while(!stack.isEmpty()) {
            final Integer pop = stack.pop();
            help.push(pop);
            System.out.println(pop);
        }
        while(!help.isEmpty()) {
            stack.push(help.pop());
        }


    }

    public static void main(String[] args) {
        final Stack<Integer> stack = new Stack<>();
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);

        print(stack);
        reverse(stack);
        print(stack);




    }
}

