package Kelement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        return findKthLargest(nums, 0, len-1, len+1-k);
    }

    private int findKthLargest(int[] nums, int l, int r, int k) {
        int random = l + ((r-l)/2);
        swap(nums, random, l);
        int pivot = nums[l], p1=l;
        for (int i=l+1; i<=r; i++) {
            if (nums[i] < pivot) {
                p1++;
                if(p1!=i)swap(nums, p1, i);
            }
        }
        if(l!=p1)swap(nums, l, p1);
        if (p1 == k-1) {
            return pivot;
        } else  if (p1 > k-1) {
            return findKthLargest(nums, l, p1-1, k);
        } else {
            return findKthLargest(nums, p1+1, r, k);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

/*
k=4
        l
 [3,2,1,5,6,4]
          p limit
          r

          */

public class MainClass {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            int ret = new Solution().findKthLargest(nums, k);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
