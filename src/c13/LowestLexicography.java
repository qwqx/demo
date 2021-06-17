package c13;

import java.util.Arrays;

/**
 * @author TK
 */
public class LowestLexicography {

    public static String lowestLexicography(String[] strArr) {


        Arrays.sort(strArr, (a, b) -> (a + b).compareTo(b + a));

        String ans = "";
        for (int i = 0; i < strArr.length; i++) {
            ans += strArr[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        String[] arr = {"b", "ba"};

        String[] arr1 = {"b", "ba", "abc", "ccd"};
        System.out.println(lowestLexicography(arr1));
    }


}
