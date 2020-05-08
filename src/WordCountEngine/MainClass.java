package WordCountEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class MainClass {

    public static void main(String[] args) {
        String[][] res = new Solution().wordCountEngine("Every book is a quotation; and every house is a quotation out of all forests, and mines, and stone quarries; and every man is a quotation from all his ancestors. ");
        for(String[] r : res){
            System.out.println(r[0] + " " + r[1]);
        }
    }

}

class Solution {

    static String[][] wordCountEngine(String document) {
        String[] words = document.split(" ");
        Map<String,Integer> map = new HashMap<String,Integer>();
        Map<String,Integer> firstOcc = new HashMap<String,Integer>();
        PriorityQueue<StringCount> pq = new PriorityQueue<StringCount>((a, b)->{
            if(a.count == b.count){
                return firstOcc.get(a.string) < firstOcc.get(b.string) ? -1 : 1;
            }else{
                return b.count-a.count;
            }});
        int idx = 0;
        for(String word : words){
            word = word.toLowerCase().replaceAll("[^a-zA-Z]","");
            map.put(word,map.getOrDefault(word,0)+1);
            if(!firstOcc.containsKey(word)){
                firstOcc.put(word,idx);
            }
            idx++;
        }
        for(Map.Entry<String,Integer> e : map.entrySet()){
            pq.offer(new StringCount(e.getKey(),e.getValue()));
        }
        String[][] res = new String[pq.size()][2];
        idx = 0;
        while(!pq.isEmpty()){
            StringCount sC = pq.poll();
            res[idx++] = new String[]{sC.string,sC.count+""};
        }
        return res;
    }

    static class StringCount{
        int count;
        String string;
        public StringCount(String string, int count){
            this.string = string;
            this.count = count;
        }

        @Override
        public String toString() {
            return "StringCount{" +
                    "count=" + count +
                    ", string='" + string + '\'' +
                    '}';
        }
    }


}
