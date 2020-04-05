package CountSquareSubmatricesWithAllOnes;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int countSquares(int[][] matrix){
        int res = 0;
        int dp[][] = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < dp.length; i++){
            dp[i][dp[0].length-1] = matrix[i][matrix[0].length-1];
        }
        for(int i = 0; i < dp[0].length; i++){
            dp[dp.length-1][i] = matrix[matrix.length-1][i];
        }
        for(int i = dp.length - 2; i >= 0; --i){
            for(int j = dp[i].length - 2; j >= 0; j--){
                if(matrix[i][j] == 1)
                    dp[i][j] = 1 + Math.min(dp[i+1][j+1],
                            Math.min(dp[i+1][j], dp[i][j+1]));
            }
        }
        for(int i = 0; i < dp.length; i++){
            for(int j = 0; j < dp[i].length; j++)
                res += dp[i][j];
        }
        return res;
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

    public static int[][] stringToInt2dArray(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        if (jsonArray.size() == 0) {
            return new int[0][0];
        }

        int[][] arr = new int[jsonArray.size()][];
        for (int i = 0; i < arr.length; i++) {
            JsonArray cols = jsonArray.get(i).asArray();
            arr[i] = stringToIntegerArray(cols.toString());
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] matrix = stringToInt2dArray(line);

            int ret = new Solution().countSquares(matrix);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
