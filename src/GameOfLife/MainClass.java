package GameOfLife;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    int[][] nMoves = new int[][]{{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
    int[][] newBoard;

    public void gameOfLife(int[][] board) {
        newBoard = new int[board.length][board[0].length];
        int idx = 0;
        for(int[] b : board)System.arraycopy(b, 0, newBoard[idx++], 0, b.length);
        for(int i = 0; i<board.length;i++){
            for(int j = 0; j<board[i].length;j++){
                if(board[i][j]==1){
                    liveCell(i,j,board);
                }else{
                    deadCell(i,j,board);
                }
            }
        }
        for(int[] b : newBoard)System.arraycopy(b, 0, board[idx++], 0, b.length);
    }

    private void liveCell(int i, int j,int[][] board){
        int neightbors = countNeightbors(i,j,board);
        if(neightbors<2 || neightbors==4){
            newBoard[i][j]=0;
        }
    }

    private void deadCell(int i, int j,int[][] board){
        if(countNeightbors(i,j,board)==3)newBoard[i][j]=1;
    }

    private int countNeightbors(int i, int j,int[][] board){
        int res = 0;
        for(int[] move : nMoves){
            int iT = i+move[0];
            int jT = j+move[1];
            if(iT<0 || jT<0 || iT>=newBoard.length || jT>=newBoard[i].length)continue;
            if(board[iT][jT]==1)res++;
            if(res==4)break;
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

    public static String int2dArrayToString(int[][] array) {
        if (array == null) {
            return "null";
        }
        if (array.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int[] item : array) {
 //           sb.append(Integer.toString(item));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[][] board = stringToInt2dArray(line);

            new Solution().gameOfLife(board);
            String out = int2dArrayToString(board);

            System.out.print(out);
        }
    }
}