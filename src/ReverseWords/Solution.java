package ReverseWords;

import java.io.*;
import java.util.*;

class Solution {

    static char[] reverseWords(char[] arr) {
        int l = arr.length-1;
        int idx = 0;
        char[] res = new char[arr.length];
        for(;l>=0;l--){
            char c = arr[l];
            if(c==' ' || l==0){ //[' ',' ']
                int r = l == 0 ? 0 : l+1;
                while(r<arr.length && arr[r]!=' '){
                    res[idx++] = arr[r]; //idx 0
                    r++;
                }
                if(idx<arr.length)res[idx++] = ' ';
            }
        }
        return res;
    }

    public static void main(String[] args) {
        char[] res = reverseWords(new char[]{'a','b','c',' ','d','e','f','g'});
        for(char c : res)System.out.print(c);

    }

}
