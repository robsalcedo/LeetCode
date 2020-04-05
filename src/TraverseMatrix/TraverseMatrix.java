package TraverseMatrix;

public class TraverseMatrix {

    public static void main(String[] args) {
        int[][] m = new int[][]{{1,2,3},{5,6,7},{9,10,11}};
        for(int[] r :m){
            for(int c : r){
                System.out.print(c+" ");
            }
            System.out.println("");
        }
        transposeMatrix(m);
        System.out.println("");
        for(int[] r :m){
            for(int c : r){
                System.out.print(c+" ");
            }
            System.out.println("");
        }
    }

    public static void transposeMatrix(int[][] matrix) {
        int R = matrix.length, C = matrix[0].length;
        for (int r = 0; r < R; ++r)
            for (int c = 0; c < C; ++c) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[c][r];
                matrix[c][r] = tmp;
            }
    }

}
