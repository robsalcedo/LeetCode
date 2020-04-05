package DiceRolls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    public int numRollsToTarget(int d, int f, int target) {

        if(f*d<target)
            return 0;

        if(d==1){
            return 1;}

        int[][] dp=new int[d+1][target+1];
        for(int i=1;i<=f&&i<=target;i++)
            dp[1][i]=1;

        for(int k=2;k<=d;k++){
            for(int i=1;i<=f&&i<=target;i++){
                for(int j=1;j<=target-i;j++){
                    if(j+i<=target){
                        dp[k][j+i]= (dp[k][j+i]+dp[k-1][j]) ;

                    }

                }
            }
        }


        return dp[d][target];

    }
}

public class MainClass {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int d = Integer.parseInt(line);
            line = in.readLine();
            int f = Integer.parseInt(line);
            line = in.readLine();
            int target = Integer.parseInt(line);

            int ret = new Solution().numRollsToTarget(d, f, target);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
