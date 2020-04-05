package BoundaryBinaryTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
}

class Solution {

    List<Integer> nums;

    public TreeNode balanceBST(TreeNode root) {
        nums = new ArrayList<Integer>();
        getNumbers(root);
        Collections.sort(nums);
        TreeNode res = balanceTree(0,nums.size()-1);
        return res;
    }

    private void getNumbers(TreeNode node){
        if(node==null)return;
        nums.add(node.val);
        if(node.left!=null)getNumbers(node.left);
        if(node.right!=null)getNumbers(node.right);
    }

    private TreeNode balanceTree(int l, int r){
        if(l<0 || r>=nums.size() || l>r)return null;
        if(l==r){
            TreeNode res = new TreeNode(nums.get(l));
            return res;
        }else if(l+1==r){
            TreeNode res = new TreeNode(nums.get(l));
            res.right = new TreeNode(nums.get(r));
            return res;
        }else{
            int middleIdx = l+ ((r-l)/2);
            TreeNode res = new TreeNode(nums.get(middleIdx));
            res.left = balanceTree(0,middleIdx-1);
            res.right = balanceTree(middleIdx+1,r);
            return res;
        }
    }






    List result = new ArrayList();

    public List boundaryOfBinaryTree(TreeNode root) {
        if(root!= null){
            result.add(root.val);
            printLeftBoundary(root.left);
            printleaves(root.left);
            printleaves(root.right);
            printRightBoundary(root.right,result.size());
        }
        return result;
    }

    private void printleaves(TreeNode root){
        if(root!=null){
            printleaves(root.left);
            if(root.left == null && root.right == null){
                result.add(root.val);
            }
            printleaves(root.right);
        }
    }
    private void printLeftBoundary(TreeNode root){
        if(root != null){
            if(root.left != null){
                result.add(root.val);
                printLeftBoundary(root.left);
            }
            else if(root.right != null){
                result.add(root.val);
                printLeftBoundary(root.right);
            }
        }
    }
    private void printRightBoundary(TreeNode root,int index){
        if(root != null){
            if(root.right != null){
                result.add(index,root.val);
                printRightBoundary(root.right,index);
            }
            else if(root.left != null){
                result.add(index,root.val);
                printRightBoundary(root.left,index);
            }
        }
    }

}

public class MainClass {
    public static TreeNode stringToTreeNode(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        int index = 1;
        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int leftNumber = Integer.parseInt(item);
                node.left = new TreeNode(leftNumber);
                nodeQueue.add(node.left);
            }

            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();
            if (!item.equals("null")) {
                int rightNumber = Integer.parseInt(item);
                node.right = new TreeNode(rightNumber);
                nodeQueue.add(node.right);
            }
        }
        return root;
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
            TreeNode root = stringToTreeNode(line);

            new Solution().balanceBST(root);

       //     String out = integerArrayListToString(ret);

       //     System.out.print(out);
        }
    }
}