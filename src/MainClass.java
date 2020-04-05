import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import com.eclipsesource.json.JsonArray;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }

 class ListNode {
     int val;
      ListNode next;
     ListNode(int x) { val = x; }
  }

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        if (edges == null || edges.length == 0 || edges[0].length == 0) {
            return 0;
        }

        int[][] graph = new int[n][n];
        for (int[] edge: edges) {
            graph[edge[0]][edge[1]] = edge[2];
            graph[edge[1]][edge[0]] = edge[2];
        }

        int min = Integer.MAX_VALUE;
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();

        for (int i = 0; i < n; i++) {
            int connections = djstraHelper(graph, i, n, distanceThreshold);
            if (min > connections) {
                min = connections;
            }
            if (!map.containsKey(connections)) {
                map.put(connections, new ArrayList<Integer>());
            }
            map.get(connections).add(i);
        }

        List<Integer> list = map.get(min);
        int res = 0;
        for (int num : list) {
            res = Math.max(res, num);
        }

        return res;

    }

    protected int djstraHelper(int[][] graph, int startPoint, int n, int distanceThreshold) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[startPoint] = 0;

        boolean[] visit = new boolean[n];

        for (int i = 0; i < n; i++) {
            int nextP = minDistance(dist, visit, n);
            visit[nextP] = true;

            for (int p = 0; p < n; p++) {
                if (!visit[p] &&
                        graph[nextP][p] != 0 &&
                        dist[nextP] + graph[nextP][p] < dist[p]) {

                    dist[p] = dist[nextP] + graph[nextP][p];
                }
            }

        }

        int res = 0;
        for (int i = 0; i < n; i++) {
            if (dist[i] <= distanceThreshold) {
                res++;
            }
        }

        return res;
    }

    protected int minDistance(int[] dist, boolean[] visit, int n) {
        int min = Integer.MAX_VALUE;
        int res = -1;

        for (int i = 0; i < n; i++)
            if (visit[i] == false && dist[i] <= min) {
                min = dist[i];
                res = i;
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
            int n = Integer.parseInt(line);
            line = in.readLine();
            int[][] edges = stringToInt2dArray(line);
            line = in.readLine();
            int distanceThreshold = Integer.parseInt(line);

            int ret = new Solution().findTheCity(n, edges, distanceThreshold);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}