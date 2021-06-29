package c17;

/**
 * @author TK
 */
public class Hanoi {

    public static void hanoi(int n) {
        f(n, "left", "right", "mid");
    }

    public static void f(int n, String from, String to, String other) {
        if(n == 1) {
            System.out.println("move:" + n  + " from:"+ from + " to:" + to);
            return;
        }

        f(n-1, from, other, to);
        System.out.println("move:" + n  + " from:"+ from + " to:" + to);
        f(n-1, other, to, from);
    }

    public static void main(String[] args) {
        hanoi(3);
    }
}
