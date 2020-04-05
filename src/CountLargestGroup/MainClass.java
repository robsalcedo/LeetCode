package CountLargestGroup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    int[] nums;

    public int countLargestGroup(int n) {
        int len = (n+"").length();
        nums = new int[37];
        getNums(0,n,0);
        int max = 0;
        int maxSize = 0;
        for(int i = 1;i<nums.length;i++){
            int nu = nums[i];
            if(nu>maxSize){
                maxSize=nu;
                max = 1;
            }else if(nu==maxSize){
                max++;
            }
        }
        return max;
    }

    private void getNums(int prevNum, int n, int curSum){
        if(prevNum>n)return;
        for(int i = 0;i<10;i++){
            int num = (prevNum*10)+i;
            if(num==0 || num>n)break;
            nums[curSum+i]++;
            getNums(num,n,curSum+i);
        }
    }
}

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new Solution().countLargestGroup(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}