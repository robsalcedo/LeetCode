package IP;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Solution {

    List<String> res;
    int[] nums;
    public List<String> restoreIpAddresses(String s) {
        res = new ArrayList<String>();
        nums = new int[s.length()];
        int i= 0;
        for(char sC : s.toCharArray())nums[i++]=sC - '0';
        dfs(new StringBuilder(""),0,0);
        return res;
    }

    private void dfs(StringBuilder sb, int idx, int oct){
        if(idx==nums.length && oct==4){
            res.add(sb.toString());
            return;
        }
        int rNums = nums.length-idx;
        if(oct>=4 || idx==nums.length || ((4-oct) * 3 < rNums)|| ((4-oct) > rNums))return;
        if(oct>0)sb.append(".");
        int num = 0;
        for(int i=idx;i<nums.length && i<(idx+3);i++){
            num*=10;
            num+=nums[i];
            int len = sb.length();
            if(!isValid(num,idx,i))break;
            sb.append(num);
            dfs(sb,i+1,oct+1);
            sb.setLength(len);
        }
    }

    private boolean isValid(int num, int idx, int i){
        if(num>255)return false;
        if(num==0){
            if(idx-i!=0)return false;
        }else{
            int digits = 0;
            while(num!=0){
                num /= 10;
                digits++;
            }
            if(digits==i-idx+1)return true;
            else return false;
        }
        return true;
    }
}

public class MainClass {
    public static String stringToString(String input) {
        return JsonArray.readFrom("[" + input + "]").get(0).asString();
    }

    public static String stringListToString(List<String> stringList) {
        StringBuilder sb = new StringBuilder("[");
        for (String item : stringList) {
            sb.append(item);
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            List<String> ret = new Solution().restoreIpAddresses(s);

            String out = stringListToString(ret);

            System.out.print(out);
        }
    }
}