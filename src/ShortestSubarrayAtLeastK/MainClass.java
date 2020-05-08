package ShortestSubarrayAtLeastK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int shortestSubarray(int[] A, int K) {
        int sum=0;
        int min=-1;

        Deque<int[]> queue=new ArrayDeque<int[]>();
        queue.offerFirst(new int[]{0, -1});

        for(int i=0; i<A.length; i++) {
            sum+=A[i];

            while(!queue.isEmpty() && queue.peekFirst()[0]>=sum)
                queue.pollFirst();

            while(!queue.isEmpty() && sum-queue.peekLast()[0]>=K) {
                int length=i-queue.pollLast()[1];
                if(min<0 || length<min) min=length;
            }

            queue.offerFirst(new int[]{sum, i});
        }

        return min;
    }
}

class MainClass {
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] A = stringToIntegerArray(line);
            line = in.readLine();
            int K = Integer.parseInt(line);

            int ret = new Solution().shortestSubarray(A, K);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
