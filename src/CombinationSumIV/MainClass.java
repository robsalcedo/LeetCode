package CombinationSumIV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {

    int[] dp;

    public int combinationSum4(int[] nums, int target) {
        dp = new int[target+1];
        Arrays.fill(dp,-1);
        return helper(nums, target, 0);
    }

    private int helper(int[] nums, int target, int sum){
        if(dp[sum]!=-1)return dp[sum];
        if(sum==target) return 1;
        int count = 0;
        for(int num : nums){
            if(sum+num<=target)
                count += helper(nums, target, sum+num);
        }
        dp[sum] = count;
        return count;
    }
}

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
            int target = Integer.parseInt(line);

            int ret = new Solution().combinationSum4(nums, target);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
