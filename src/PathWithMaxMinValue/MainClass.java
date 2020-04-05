package PathWithMaxMinValue;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Solution {
    static class Cell {
        int i, j, val;
        Cell(int i, int j, int val) { this.i = i; this.j = j; this.val = val; }
    }

    static int[][] moves = new int[][] {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int maximumMinimumPath(int[][] A) {
        PriorityQueue<Cell> q = new PriorityQueue<>((a, b) -> b.val - a.val);
        q.add(new Cell(0, 0, A[0][0]));
        boolean[][] discovered = new boolean[A.length][A[0].length];
        discovered[0][0] = true;
        int min = A[0][0];
        while (!q.isEmpty()) {
            Cell bestMove = q.poll();
            if (bestMove.val < min) min = bestMove.val;

            if (bestMove.i == A.length - 1 && bestMove.j == A[0].length - 1) {
                return min;
            }
            for (int[] move : moves) {
                int i = bestMove.i + move[0];
                int j = bestMove.j + move[1];
                if (i >= 0 && i < A.length && j >= 0 && j < A[0].length && !discovered[i][j]) {
                    q.add(new Cell(i, j, A[i][j]));
                    discovered[i][j] = true;
                }
            }
        }
        throw new IllegalStateException("Unable to reach end cell");
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
            int[][] A = stringToInt2dArray(line);

            int ret = new Solution().maximumMinimumPath(A);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}