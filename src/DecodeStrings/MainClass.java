package DecodeStrings;

import com.eclipsesource.json.JsonArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Solution {
    public int numDecodings(String s) {
        if (s == null) {
            return 0;
        }
        int[] ways = new int[s.length() + 1];
        ways[0] = 1;
        ways[1] = s.charAt(0) == '0'? 0 : 1;
        for (int i = 2; i <= s.length(); i ++) {
            if (s.charAt(i - 1) != '0') {
                ways[i] += ways[i - 1];
            }
            if (s.charAt(i - 2) != '0') {
                int twoNum = ((s.charAt(i - 2)-'0')*10)+(s.charAt(i - 1)-'0');
                if (twoNum>=10 && twoNum<=26) {
                    ways[i] += ways[i - 2];
                }
            }
        }
        return ways[s.length()];
    }
}

public class MainClass {
    public static String stringToString(String input) {
        return JsonArray.readFrom("[" + input + "]").get(0).asString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = stringToString(line);

            int ret = new Solution().numDecodings(s);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
