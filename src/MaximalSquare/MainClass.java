package MaximalSquare;

class Solution {
    public int maximalSquare(char[][] mat) {
        if(mat.length==0)return 0;
        int[][] dp = new int[mat.length][mat[0].length];

        int max = 0;
        for(int i=0;i<dp.length;i++){
            for(int j=0;j<dp[0].length;j++){
                if(i==0||j==0){
                    dp[i][j]=(mat[i][j]=='0')?0:1;
                }else{
                    if(mat[i][j]=='0'){
                        dp[i][j]=0;
                    }else{
                        dp[i][j]=1+Math.min(dp[i-1][j],Math.min(dp[i][j-1],dp[i-1][j-1]));
                    }
                }
                max=Math.max(dp[i][j],max);
            }
        }

        return max*max;
    }
}

public class MainClass {

    public static void main(String[] args) {
        char[][] mat = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','1','1','1'}};
        System.out.println(new Solution().maximalSquare(mat));
    }
}
