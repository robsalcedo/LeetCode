package BackspaceCompare;

public class MainClass {

    static class Solution {
        public boolean backspaceCompare(String S, String T) {
            StringBuilder cpS = new StringBuilder("");
            StringBuilder cpT = new StringBuilder("");
            for(char c : S.toCharArray()){
                if(c!='#'){
                    cpS.append(c);
                    cpT.append(c);
                }else{
                    if(cpS.length()>0)cpS.setLength(cpS.length()-1);
                    if(cpT.length()>0)cpT.setLength(cpT.length()-1);
                }
            }
            return cpS.toString().equals(cpT.toString()) ? true : false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().backspaceCompare("a#c","b"));
    }
}


