package QueriesPermutation;

public class MainClass {
    static class Solution {
        public int[] processQueries(int[] queries, int m) {
            int[] P = new int[m];
            int[] res = new int[queries.length];
            for(int i = 0;i<m;i++)P[i] = i+1;
            for(int i = 0;i<queries.length;i++){
                int num = queries[i];
                int resIdx = 0;
                while(P[resIdx]!=num)resIdx++;
                res[i]=resIdx;
                swapToBegin(P,num);
            }
            return res;
        }

        private void swapToBegin(int[] P, int num){
            int tmp = P[0];
            P[0] = num;
            for(int i=1;i<P.length;i++){
                int prev = P[i];
                P[i] = tmp;
                tmp = prev;
                if(prev==num)break;
            }
        }
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.processQueries(new int[]{7,5,5,8,3}, 8);
    }
}
