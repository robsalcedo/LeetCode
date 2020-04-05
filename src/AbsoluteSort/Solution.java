package AbsoluteSort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {

    static int[] absSort(int[] arr) {
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());
        Collections.sort(list, new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                if(Math.abs(a)==Math.abs(b)){
                    return a.compareTo(b);
                }else{
                    return Math.abs(a)-Math.abs(b);
                }
            }
        });
        return list.stream().mapToInt(i->i).toArray();
    }


    public static void main(String[] args) {
        int[] arr = new int[]{2, -7, -2, -2, 0};
        arr = absSort(arr);
        for(int i : arr)System.out.print(i+" ");
    }
}
