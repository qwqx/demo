package c02;

public class EvenTimesOddTimes {


    /**
     * 打印出现奇数次的数(只有一个)
     * @param arr
     */
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for(int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        System.out.println(eor);
    }

    /**
     * 打印出现奇数次的数（两个）
     * @param arr
     */
    public static void printOddTimesNum2(int[] arr) {
        int eor = 0;
        for(int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }

        int rightOne = eor & (-eor); // eor & (~eor + 1)

        int eor1 = eor;
        for(int i = 0; i < arr.length; i++) {
            if((arr[i] & rightOne) > 0) {
                eor1 ^= arr[i];
            }
        }


        int oth = eor ^ eor1;
        System.out.println(eor1 + ", " + oth);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 6, 8, 1, 2, 2, 3, 8, 8, 8, 1}; //2,6
        printOddTimesNum2(arr);

    }


}
