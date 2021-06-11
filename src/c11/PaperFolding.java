package c11;

/** ☆☆
 * @author TK
 */
public class PaperFolding {

    public static void printAllFold(int N) {
        process(1, N, true);
    }

    public static void process(int i, int N, boolean down) {

        if (i > N) {
            return;
        }

        process(i + 1, N, true);
        System.out.println(down ? "凹" : "凸");
        process(i + 1, N, false);
    }

    public static void main(String[] args) {
        printAllFold(3);
    }
}
