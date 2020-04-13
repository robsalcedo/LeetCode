package SerializeBT;

import java.util.LinkedList;
import java.util.Queue;

public class MainClass {

    static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return this.data+"";
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        String ser = new MainClass().serializeTree(root);
        System.out.println(ser);
        TreeNode res = new MainClass().restoreTree(ser);
        System.out.println(res);
    }

    public String serializeTree(TreeNode root){
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            StringBuilder sb = new StringBuilder();
            while(size-->0){
                TreeNode tmp = q.poll();
                if(tmp==null){
                    sb.append("n,");
                    q.add(null);
                    q.add(null);
                }
                else{
                    sb.append(tmp.data);
                    sb.append(",");
                    q.add(tmp.left);
                    q.add(tmp.right);
                }
            }
            if(!isValidSb(sb))break;
            res.append(sb.toString());
        }
        res.setLength(res.length()-1);
        return res.toString();
    }

    private boolean isValidSb(StringBuilder sb){
        for(String s : sb.toString().split(",")){
            if(s.length()>0 && !s.equals("n"))return true;
        }
        return false;
    }

    public TreeNode restoreTree(String str){
        if(str.length()==0)return null;
        String[] strAr = str.split(",");
        return restoreTree(strAr);
    }
    int idx = 0;
    private TreeNode restoreTree(String[] strAr){
        if(strAr[idx] == "n") {
            idx++;
            return null;
        }

        TreeNode node = new TreeNode(new Integer(strAr[idx++]));
        node.left = restoreTree(strAr);
        node.right = restoreTree(strAr);
        return node;
    }
}
