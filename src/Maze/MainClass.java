package Maze;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {

    public boolean hasPath(int[][] m, int[] s, int[] d) {
        int[][] move = new int[][]{{0,1},{0,-1},{1,0},{-1,0}};
        boolean[][] v = new boolean[m.length][m[0].length];
        Deque<int[]> q = new LinkedList<int[]>();
        q.add(s);
        v[s[0]][s[1]] = true;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0;i<size;i++){
                s = q.remove();
                if(d[0]==s[0] && d[1]==s[1])return true;
                v[s[0]][s[1]] = true;
                for(int[] mov : move){
                    int x = s[0];
                    int y = s[1];
                    while(isValid(x,y,mov,m)){
                        x += mov[0];
                        y += mov[1];
                    }
                    if(!v[x][y])q.add(new int[]{x,y});
                }
            }
        }
        return false;
    }

    public int shortestDistance(int[][] m, int[] s, int[] d) {
        int[][] moves = new int[][]{{1,0},{-1,0},{0,1},{0,-1}};
        Queue<int[]> q = new LinkedList<int[]>();
        int[][] stepsTracker = new int[m.length][m[0].length];
        for(int[] sT : stepsTracker)Arrays.fill(sT,Integer.MAX_VALUE);
        q.add(s);
        stepsTracker[s[0]][s[1]] = 0;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i<size;i++){
                s = q.remove();
                for(int[] mov:moves){
                    int x = s[0];
                    int y = s[1];
                    int stepsTmp = stepsTracker[x][y];
                    while(isNextValid(x,y,m,mov)){
                        stepsTmp++;
                        x+=mov[0];
                        y+=mov[1];
                    }
                    if(stepsTmp<stepsTracker[x][y]){
                        stepsTracker[x][y] = stepsTmp;
                        q.add(new int[]{x,y});
                    }
                }
            }
        }
        return stepsTracker[d[0]][d[1]] == Integer.MAX_VALUE ? -1 : stepsTracker[d[0]][d[1]];
    }

    private boolean isValid(int x, int y, int[] mov, int[][] m){
        x +=mov[0];
        y +=mov[1];
        if(x<0 || y<0 || x>=m.length || y>=m[x].length || m[x][y]==1)return false;
        return true;
    }

    private boolean isNextValid(int x, int y, int[][] m, int[] mov){
        x+=mov[0];
        y+=mov[1];
        if(x<0 || y<0 || x>= m.length || y >=m[x].length || m[x][y]==1)return false;
        return true;
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
            int[][] maze = stringToInt2dArray(line);
            line = in.readLine();
            int[] start = stringToIntegerArray(line);
            line = in.readLine();
            int[] destination = stringToIntegerArray(line);

      //      boolean ret = new Solution().hasPath(maze, start, destination);
            int ret = new Solution().shortestDistance(maze, start, destination);

     //       String out = booleanToString(ret);

            System.out.print(ret);
        }
    }
}
