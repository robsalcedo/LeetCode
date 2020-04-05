import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution2 {
    static int maxToys = 0;
    // Complete the maximumToys function below.
    static int maximumToys(int[] prices, int k) {
        helper(prices,k,0,0);
        return maxToys;
    }

    private static void helper(int[] prices, int k, int idx, int toys){
        if(idx<=prices.length && k>=0)maxToys = Math.max(maxToys,toys);
        else return;
        for(int i=idx;i<prices.length;i++){
            if(prices[i]>k)continue;
            k -= prices[i];
            helper(prices,k,i+1, toys+1);
            k += prices[i];
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = 7;

        int k = 50;

        int[] prices = new int[]{1,12,5,111,200,1000,10};


        int result = maximumToys(prices, k);
        System.out.println(result);

    }
}

