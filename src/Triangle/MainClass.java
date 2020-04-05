package Triangle;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    int[][] memo;

    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==0)return 0;
        int lastLength=triangle.get(triangle.size()-1).size();
        memo =new int[lastLength][lastLength];
        for(int[] a:memo)Arrays.fill(a,Integer.MAX_VALUE);
        return dfs(triangle,0,0);

    }
    public int dfs(List<List<Integer>> triangle,int i,int j){
        if(i<0 || j<0 || i>=triangle.size() || j>=triangle.get(i).size())return 0;
        if(memo[i][j]!=Integer.MAX_VALUE)return memo[i][j];
        int downRight=dfs(triangle,i+1,j+1);
        int down=dfs(triangle,i+1,j);
        memo[i][j]=triangle.get(i).get(j)+Math.min(downRight,down);
        return memo[i][j];
    }
}

public class MainClass {
    private static List<Integer> stringToIntegerList(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        List<Integer> arr = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            arr.add(jsonArray.get(i).asInt());
        }
        return arr;
    }

    public static List<List<Integer>> stringToInt2dList(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        if (jsonArray.size() == 0) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> list = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonArray cols = jsonArray.get(i).asArray();
            list.add(stringToIntegerList(cols.toString()));
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            List<List<Integer>> triangle = stringToInt2dList(line);

            int ret = new Solution().minimumTotal(triangle);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}