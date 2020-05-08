package SmallestSubstringOfAllCharacters;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainClass {

    static String getShortestUniqueSubstring(char[] arr, String str) {
        int l = 0, r=0;
        int min = Integer.MAX_VALUE;
        String minStr = "";
        Set<Character> charSet = new HashSet<Character>();
        for(char a : arr)charSet.add(a);
        while(l<str.length()){
            Set<Character> resSet = new HashSet<Character>();
            r=l;
            if(charSet.contains(str.charAt(l))){
                while(r-l+1 < min && r<str.length()){
                    if(charSet.contains(str.charAt(r))){
                        resSet.add(str.charAt(r));
                        if(charSet.size()==resSet.size()){
                            min = r-l+1;
                            minStr = str.substring(l,r+1);
                            l = r;
                            break;
                        }
                    }
                    r++;
                }
            }
            l++;
        }
        return minStr;
    }
    public static void main(String[] args) {
        System.out.println(getShortestUniqueSubstring(new char[]{'x','y','z'},"xyyzyzyx"));
    }
}
