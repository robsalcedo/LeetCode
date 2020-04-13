package LargestTimeDigits;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;

class Solution {
    public String largestTimeFromDigits(int[] A) {
        int[] res = new int[4];
        Arrays.fill(res,-1);
        for(int i=0;i<A.length;i++){
            if(!findSpot(A,res,A[i]))return "";
        }
        return ""+res[0]+res[1]+":"+res[2]+res[3];
    }

    private boolean findSpot(int[] A, int[] res, int num){
        if(num<=2 && (num>res[0] || res[0]==-1)){
            if(res[0]==-1){
                res[0] = num;
                return true;
            }else{
                int tmp = res[0];
                res[0] = num;
                return findSpot(A,res,tmp);
            }
        }else if(num<=3  && (num>res[1] || res[1]==-1)){
            if(res[1]==-1){
                res[1] = num;
                return true;
            }else{
                int tmp = res[1];
                res[1] = num;
                return findSpot(A,res,tmp);
            }
        }else if(num<=5  && (num>res[2] || res[2]==-1)){
            if(res[2]==-1){
                res[2] = num;
                return true;
            }else{
                int tmp = res[2];
                res[2] = num;
                return findSpot(A,res,tmp);
            }
        }
        else if(num>res[3] || res[3]==-1){
            if(res[3]==-1){
                res[3] = num;
                return true;
            }
        }
        return false;
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
            int[] A = stringToIntegerArray(line);

            String ret = new Solution().largestTimeFromDigits(A);

            String out = (ret);

            System.out.print(out);
        }
    }
}
