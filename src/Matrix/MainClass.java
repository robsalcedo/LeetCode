package Matrix;


import java.util.*;

class Solution {

    public int countNegatives(int[][] grid) {
        int res = 0;
        for(int i = 0;i<grid.length;i++){
            int[] row = grid[i];
            int start = 0;
            int end = row.length-1;
            int mid = start + ((end-start)/2);
            while(start<=end){
                if(row[mid]>=0){
                    if(mid==row.length-1){
                        break;
                    }else{
                        if(row[mid+1]<0){
                            res+=row.length - mid-1;
                            break;
                        }else{
                            start = mid+1;
                        }
                    }
                }else{
                    if(mid==0){
                        res+=row.length-1;
                    }
                    else{
                        if(row[mid-1]>0){
                            res+=row.length - mid+1;
                            break;
                        }else{
                            end = mid-1;
                        }
                    }
                }
                mid = start + ((end-start)/2);
            }

        }
        return res;
    }
}

public class MainClass {


    public static void main(String[] args) {
        double n = (-1/9);
        double res = (((9*n)+1)/8)+(((3*n)-2)/3)-(((5*n)-3)/2);
        System.out.println(res);
    }
}

