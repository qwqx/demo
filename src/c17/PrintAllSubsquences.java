package c17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TK
 */
public class PrintAllSubsquences {

    public static void printAllSubsquences(String s) {
        final List<String> ans = new ArrayList<>();
        f(s.toCharArray(), 0, ans, "");

        for (String str : ans) {
            System.out.println(str);
        }
    }

    public static void f(char[] str, int index, List<String> ans, String path) {
        if(index == str.length) {
            ans.add(path);
            return;
        }

        f(str, index + 1, ans, path);

        f(str, index + 1, ans, path + str[index]);
    }

    public static void main(String[] args) {
        printAllSubsquences("abc");
    }
}
