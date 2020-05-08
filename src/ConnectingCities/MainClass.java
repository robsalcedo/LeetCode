package ConnectingCities;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {

    public int minimumCost(int N, int[][] connections) {
        if(connections.length < N - 1)return -1;
        int res = 0;
        Arrays.sort(connections, (a, b)->a[2]-b[2]);
        int[] parent = new int[N+1];
        for(int node = 0; node<N+1;node++){
            parent[node] = node;
        }
        for(int[] conn : connections){
            int rootA = find(parent,conn[0]);
            int rootB = find(parent,conn[1]);
            if(rootA!=rootB){
                res+=conn[2];
                parent[rootA] = rootB;
                N--;
            }

        }
        return N==1 ? res : -1;

    }

    private int find(int[] parent, int A){
        while(parent[A]!=A){
            A = parent[A];
        }
        return A;
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
            int N = Integer.parseInt(line);
            line = in.readLine();
            int[][] connections = stringToInt2dArray(line);

            int ret = new Solution().minimumCost(N, connections);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
