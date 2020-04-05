import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class QuickSort {
    int steps = 0;
    public void quickSort(int[] s) {
        sort(s,0,s.length-1);
        print(s);
        System.out.println("steps: "+steps);
    }

    private void sort(int[] s, int l, int r) {
        if(l>=r)return;
        int p = partition(s, l, r, s[l+(r-l)/2]);
        sort(s, l, p - 1);
        sort(s, p, r);
    }

    private int partition(int[] a, int l, int r, int pivot) {
        steps++;
        while(l<=r){
            while(a[l]<pivot){
                l++;
            }
            while(a[r]>pivot){
                r--;
            }
            if(l<=r){
                swap(a,l,r);
                l++;r--;
            }
        }
        return l;
    }

    public static String stringToString(String input) {
        return JsonArray.readFrom("[" + input + "]").get(0).asString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int[] s = generateArray(10);
        new QuickSort().quickSort(s);
    }

    private static int[] generateArray(int i) {
        int[] res = new int[i];
        Random random = new Random();
        i--;
        while(i>=0){
            res[i] = random.nextInt(100);
            i--;
        }
        return res;
    }

    private void print(int[] ar){
        for(int i : ar){
            System.out.print(i+",");
        }
        System.out.println(" ");
    }

    private void swap(int[] ar, int dest, int source){
        int tmp = ar[dest];
        ar[dest] = ar[source];
        ar[source] = tmp;
    }

}
