package QueueReconstructionByHeight;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int[][] reconstructQueue(int[][] people) {
        List<int[]> pLst = new ArrayList<int[]>();
        int[][] res = new int[people.length][2];
        for(int[] p : people)pLst.add(p);
        while(pLst.size()>0){
            int[] p = getMin(pLst);
            fillRes(res,p);
        }
        return res;
    }


    public int test(){
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        return map.get(4);
    }

    private void fillRes(int[][] res, int[] p){
        int pos = p[1];
        for(int i = 0; i<res.length;i++){
            if(pos==0 && res[i][0]==0){
                res[i]=p;
                break;
            }else if(res[i][0]==0){
                pos--;
            } else{
                if(res[i][0]>=p[0])pos--;
            }
        }
    }

    private int[] getMin(List<int[]> pLst){
        int[] res = pLst.get(0);
        int  idx = 0;
        int idxMin = 0;
        for(int[] p : pLst){
            if(p[0] == res[0]){
                if(res[1]>p[1]){
                    res = p;
                    idxMin = idx;
                }
            }else{
                if(res[0]>p[0]){
                    res = p;
                    idxMin = idx;
                }
            }
            idx++;
        }
        pLst.remove(idxMin);
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

    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
          //  sb.append(Integer.toString(item));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        new Solution().test();
        String line;
        while ((line = in.readLine()) != null) {
            int[][] people = stringToInt2dArray(line);

            int[][] ret = new Solution().reconstructQueue(people);

            String out = int2dArrayToString(ret);

            System.out.print(out);
        }
    }
}