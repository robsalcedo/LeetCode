package NetworkDelayTime;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {

    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 1; i <= N; i++) map.put(i, new HashMap<>());
        for (int[] t : times) map.get(t[0]).put(t[1], t[2]);

        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[K] = 0;
        boolean[] marked = new boolean[N + 1];

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> { return a[1] - b[1]; });
        pq.offer(new int[]{K, 0});
        while (!pq.isEmpty()) {
            int node = pq.poll()[0];
            if (marked[node]) continue;
            marked[node] = true;
            for (int next : map.get(node).keySet()) {
                if (!marked[next] && map.get(node).get(next) + costs[node] < costs[next]) {
                    costs[next] = map.get(node).get(next) + costs[node];
                    pq.offer(new int[]{next, costs[next]});
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= N; i++) {
            if (costs[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, costs[i]);
        }
        return max;
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
            int[][] times = stringToInt2dArray(line);
            line = in.readLine();
            int N = Integer.parseInt(line);
            line = in.readLine();
            int K = Integer.parseInt(line);

            int ret = new Solution().networkDelayTime(times, N, K);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
