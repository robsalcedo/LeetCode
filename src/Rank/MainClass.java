package Rank;


import java.util.*;

class Solution {
    public String rankTeams(String[] votes) {
        Map<Character, int[]> map = new HashMap<>();
        int len = votes[0].length();
        for(String vote : votes){
            for(int i = 0; i < len; i++){
                if(!map.containsKey(vote.charAt(i))){
                    map.put(vote.charAt(i), new int[len]);
                }
                map.get(vote.charAt(i))[i]++;
            }
        }
        PriorityQueue<Character> pq = new PriorityQueue<>((a, b)-> {
            for(int i = 0 ;i<len ;i++){
                if(map.get(a)[i] != map.get(b)[i]){
                    return map.get(b)[i] - map.get(a)[i];
                }
            }
            return a-b;
        });
        for(Character ch : map.keySet()){
            pq.add(ch);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){
            sb.append(pq.poll());
        }
        return sb.toString();
    }
}

public class MainClass {

    public static void main(String[] args) {
        new Solution().rankTeams(new String[]{"WXYZ","XYZW"});
    }
}
