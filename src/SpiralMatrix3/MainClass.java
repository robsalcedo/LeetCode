package SpiralMatrix3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
        int[][] res = new int[(R*C)][2];
        int[][] m = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        int i = r0, j = c0;
        int mIdx = 0;
        int num = 1;
        int steps = 1;
        res[0][0] = r0;
        res[0][1] = c0;
        while(num<R*C){
            for(int s=0;s<2;s++){
                for(int k=0;k<steps;k++){
                    i = i+m[mIdx][0];
                    j = j+m[mIdx][1];
                    if(i>=0 && j>=0 && i<R && j<C ){
                        res[num] = new int[]{i,j};
                        num++;
                    }
                }
                if(mIdx==m.length-1) mIdx = 0;
                else mIdx++;
            }
            steps++;
        }
        return res;
    }
}

public class MainClass {
    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
   //         sb.append(Integer.toString(item));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int R = Integer.parseInt(line);
            line = in.readLine();
            int C = Integer.parseInt(line);
            line = in.readLine();
            int r0 = Integer.parseInt(line);
            line = in.readLine();
            int c0 = Integer.parseInt(line);

            int[][] ret = new Solution().spiralMatrixIII(R, C, r0, c0);

 //           String out = int2dArrayToString(ret);

            System.out.print(ret);
        }
    }
}