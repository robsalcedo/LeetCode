package MinimunCostToMergeStones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if (!isValid(n, K)) {
            return -1;
        }
        int[] preSum = buildPreSum(stones, n);
        int[][] dp = new int[n][n];
        int step = K - 1;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + step; j < n; ++j) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k += step) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
                if (isValid(j - i + 1, K)) {
                    int sum = i == 0 ? preSum[j] : preSum[j] - preSum[i - 1];
                    dp[i][j] += sum;
                }
            }
        }
        return dp[0][n - 1];

    }

    private int[] buildPreSum(int[] array, int n) {
        int[] preSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += array[i];
            preSum[i] = sum;
        }
        return preSum;

    }

    private boolean isValid(int length, int k) {
        return (length - 1) % (k - 1) == 0;
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
            int[] stones = stringToIntegerArray(line);
            line = in.readLine();
            int K = Integer.parseInt(line);

            int ret = new Solution().mergeStones(stones, K);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
