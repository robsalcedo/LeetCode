package MaximumPerformanceTeam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    long maxPerformance = 0;
    int MOD = (int) (1e9 + 7);

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        int numOfEngineers= 0;
        while(numOfEngineers++<k){
            int[] engineers = new int[numOfEngineers];
            int idx = numOfEngineers-1;
            while(idx>=0)engineers[idx]=idx--;
            helper(engineers,speed,efficiency);
        }
        return (int) (maxPerformance % MOD);
    }

    private void helper(int[] engineers, int[] speed, int[] efficiency){
        while(engineers[0]+engineers.length <= speed.length){
            maxPerformance = Math.max(maxPerformance,calculatePerformance(engineers, speed, efficiency));
            int engIdxToMove = engineers.length-1;
            int idx = 0;
            while(engineers[engIdxToMove]==speed.length-1-idx){
                engIdxToMove--;
                idx++;
                if(engIdxToMove<0)return;
            }
            engineers[engIdxToMove]++;
            while(engIdxToMove<engineers.length-1){
                engineers[engIdxToMove+1] = engineers[engIdxToMove]+1;
                engIdxToMove++;
            }
        }
    }

    private long calculatePerformance(int[] engineers, int[] speed, int[] efficiency){
        long totSpeed = 0;
        long minEff = Integer.MAX_VALUE;
        for(int eIdx : engineers){
            totSpeed+=speed[eIdx];
            minEff = Math.min(minEff,efficiency[eIdx]);
        }
        return totSpeed * minEff;
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
            int n = Integer.parseInt(line);
            line = in.readLine();
            int[] speed = stringToIntegerArray(line);
            line = in.readLine();
            int[] efficiency = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            int ret = new Solution().maxPerformance(n, speed, efficiency, k);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
