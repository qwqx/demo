package eight;

/**
 * @author TK
 */
public class Radio {

    public static void s(int[] arr) {

        int digit = 1;
        for(Integer item : arr) {
            int tmplDigit = 1;
            while ((item = item / 10) != 0) {
                tmplDigit++;
            }
            digit = tmplDigit > digit ? tmplDigit : digit;
        }

        s(arr, 0, arr.length - 1, digit);
    }

    public static int get(int num, int digit) {
        while((num / 10) != ) {
            num = num / 10;
        }
        return num;
    }


    public static void s(int[] arr, int l, int r, int digit) {

        int[] endCount = new int[10];
        for(int i = l; i<= r; i++) {
            arr[i]



        }




    }
}
