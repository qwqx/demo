package c02;

import java.util.HashMap;
import java.util.Iterator;

public class KM {

    // 1个 k次, 其余m此次， 求出现一次的元素
    // k< m
    public static int printKM(int[] arr, int k, int m) {
        int[] a = new int[32];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < 32; j++) {
                if (((arr[i] >> j) & 1) == 1) {
                    a[j]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (a[i] % m == k) {
                ans |= 1 << i;
            } else if (a[i] % m == 0) {
                continue;
            } else {
                return -1;
            }

        }

        if (ans == 0) {
            int count = 0;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == 0) {
                    count++;
                }
            }
            if (count != k) {
                return -1;
            }
        }
        return ans;
    }

    public static int test(int arr[], int k, int m) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0;i< arr.length; i++) {

            if(map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) +1);
            }else{
                map.put(arr[i], 1);
            }
        }
        Iterator<Integer> iterator = map.keySet().iterator();
        while(iterator.hasNext()) {
            Integer next = iterator.next();
            if(map.get(next) == k) {
                return next;
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        int k = 2, m = 3;
        int[] arr = new int[]{3,6,0,4,3,6,6,0,0,4,4};//3

        System.out.println(test(arr, k, m));

        int num = printKM(arr, 2, 3);
        System.out.println(num);


    }
}
