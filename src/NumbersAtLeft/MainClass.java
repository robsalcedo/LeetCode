package NumbersAtLeft;

import javax.swing.tree.TreeNode;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<Integer>();
        if(nums==null || nums.length==0)return res;
        TreeNode root = new TreeNode(nums[nums.length-1]);
        res.add(0);
        for(int i = nums.length-2;i>=0;i--){
            int count = insertNode(root,nums[i]);
            res.add(count);
        }
        Collections.reverse(res);
        return res;
    }

    private int insertNode(TreeNode node, int val){
        int count = 0;
        while(true){
            if(val<=node.val){
                node.count++;
                if(node.left==null){
                    node.left = new TreeNode(val);
                    break;
                }else{
                    node = node.left;
                }
            }else{
                count += node.count;
                if(node.right==null){
                    node.right = new TreeNode(val);
                    break;
                }else{
                    node = node.right;
                }
            }
        }
        return count;
    }

    class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        int count;
        public TreeNode(int val){
            this.val = val;
            this.count = 1;
        }
    }
}

/*
              i
[5,2,6,15,3,6,5,9,4]
                  lr

[,,,,,,,,4]

sort(4,9)
*/

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

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            List<Integer> ret = new Solution().countSmaller(nums);

            String out = integerArrayListToString(ret);

            System.out.print(out);
        }
    }
}