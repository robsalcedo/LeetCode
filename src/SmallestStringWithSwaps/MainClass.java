package SmallestStringWithSwaps;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
// Problem abstract: Sort the characters within each connected group.

    // For each the given pairs, create connected groups using union-find. Always mark the smaller index as parent;
// For each character in s, create mapping from root -> a list of candidate char. Since we want to use the smallest one every time we pick from them, use PriorityQueue.
// Finally, for each index, choose the first char in the associated pq and append into result.
    private int[] parent;
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (s == null || s.length() == 0) {
            return null;
        }

        // init
        parent = new int[s.length()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }

        // union
        for (List<Integer> pair : pairs) {
            union(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        char[] sChar = s.toCharArray();
        for (int i = 0; i < sChar.length; i++) {
            int root = find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(sChar[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sChar.length; i++) {
            sb.append(map.get(find(i)).poll());
        }
        return sb.toString();
    }


    private int find(int index) {
        while (parent[index] != index) {
            parent[index] = parent[parent[index]];
            index = parent[index];
        }
        return index;
    }


    private void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }

}

public class MainClass {
    public static String stringToString(String input) {
        return JsonArray.readFrom("[" + input + "]").get(0).asString();
    }

    private static List<Integer> stringToIntegerList(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        List<Integer> arr = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            arr.add(jsonArray.get(i).asInt());
        }
        return arr;
    }

    public static List<List<Integer>> stringToInt2dList(String input) {
        JsonArray jsonArray = JsonArray.readFrom(input);
        if (jsonArray.size() == 0) {
            return new ArrayList<List<Integer>>();
        }

        List<List<Integer>> list = new ArrayList<>(jsonArray.size());
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonArray cols = jsonArray.get(i).asArray();
            list.add(stringToIntegerList(cols.toString()));
        }
        return list;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);
            line = in.readLine();
            List<List<Integer>> pairs = stringToInt2dList(line);

            String ret = new Solution().smallestStringWithSwaps(s, pairs);

            String out = (ret);

            System.out.print(out);
        }
    }
}