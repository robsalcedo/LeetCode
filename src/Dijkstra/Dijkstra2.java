package Dijkstra;

import java.util.Arrays;

public class Dijkstra2 {

    public static void main(String[] args) {
        int[][] matrix = new int[][]
                {{0,4,5,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},//A
                 {4,0,Integer.MAX_VALUE,Integer.MAX_VALUE,2,Integer.MAX_VALUE,8,Integer.MAX_VALUE},//B
                 {5,Integer.MAX_VALUE,0,5,Integer.MAX_VALUE,9,Integer.MAX_VALUE,Integer.MAX_VALUE},//C
                 {Integer.MAX_VALUE,Integer.MAX_VALUE,5,0,6,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},//D
                 {Integer.MAX_VALUE,2,Integer.MAX_VALUE,6,0,12,Integer.MAX_VALUE,Integer.MAX_VALUE},//E
                 {Integer.MAX_VALUE,Integer.MAX_VALUE,9,Integer.MAX_VALUE,12,0,Integer.MAX_VALUE,7},//F
                 {Integer.MAX_VALUE,8,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,0,8},//G
                 {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,7,8,0}};//H
       // int[] distances = dijkstra(matrix,0);
    }

    private  int[] dijkstra(int[][] matrix, int current) {
        int[] distances = new int[matrix.length];
        boolean[] visited = new boolean[matrix.length];
        distances = matrix[current].clone();
        while(true){
            
        }


    }


}
