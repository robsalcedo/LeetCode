package RegularExpressionMatching;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp=new boolean[s.length()+1][p.length()+1];
        dp[0][0]=true;
        for(int i=1;i<dp[0].length;i++){
            if(p.charAt(i-1)=='*')
                dp[0][i]=dp[0][i-2];
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.')
                    dp[i][j]=dp[i-1][j-1];
                else if(p.charAt(j-1)=='*'){
                    dp[i][j]=dp[i][j-2];
                    if(s.charAt(i-1)==p.charAt(j-2)||p.charAt(j-2)=='.')
                        dp[i][j]=dp[i-1][j]|dp[i][j];
                }
                else
                    dp[i][j]=false;
            }
        }
        return dp[s.length()][p.length()];
    }
}

public class MainClass {
    public static String stringToString(String input) {
        return JsonArray.readFrom("[" + input + "]").get(0).asString();
    }

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);
            line = in.readLine();
            String p = stringToString(line);

            boolean ret = new Solution().isMatch(s, p);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}
