package alrigothms;

import java.util.ArrayList;
import java.util.List;

public class Searching {
    public static int[] linearSearch(int[] arr, int value) {
        int[] result = null;
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 0; i< arr.length; i++) {
            if(arr[i]> value) {
                list.add(i);
            }
        }
        result = list.stream().mapToInt(Integer::intValue).toArray();
        return result;
    }

    public static int binarySearch(int[] arr, int l,int r, int value) {
        if (r>=l) {
            int m = l+(r-l)/2;
            if(arr[m]== value) {
                // vi mang co the co cac phan tu trung lap nen neu ar[m-1] = value thi ta giam m
                while(m>0 && arr[m-1]== value) {
                    m--;
                }
                return m;
            }
            if (value<arr[m]) {
                return binarySearch(arr, l, m-1, value);
            } else {
                return binarySearch(arr, m+1, r, value);
            }
        }
        return -1;
    }

}
