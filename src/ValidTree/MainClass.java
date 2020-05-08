package ValidTree;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {

    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) return false;
        UnionFind unionFind = new UnionFind(n);
        for(int[] edge : edges){
            if(!unionFind.union(edge[0],edge[1]))return false;
        }
        return true;
    }
}

class UnionFind{
    int[] parents;

    public UnionFind(int k){
        this.parents = new int[k];
        for(int i = 0; i<k;i++){
            this.parents[i] = i;
        }
    }

    public boolean union(int A, int B){
        int rootA = find(A);
        int rootB = find(B);
        if(rootA==rootB)return false;
        parents[rootA] = rootB;
        return true;
    }

    public int find(int node){
        while(this.parents[node]!=node){
            node = parents[node];
        }
        return node;
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            line = in.readLine();
            int[][] edges = stringToInt2dArray(line);

            boolean ret = new Solution().validTree(n, edges);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }
}
