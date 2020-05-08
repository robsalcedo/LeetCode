package Croak;

public class MainClass<main> {

    public int minNumberOfFrogs(String croakOfFrogs) {
        if(croakOfFrogs.length()==0 || croakOfFrogs.charAt(0)!='c')return -1;
        int res = 0;
        char[] croakAr = new char[]{'k', 'a', 'o', 'r' ,'c'};
        boolean found = true;
        while(found && croakOfFrogs.length()>0){
            char[] croakArr = croakOfFrogs.toCharArray();
            StringBuilder nCroak = new StringBuilder(croakOfFrogs);
            int i = croakArr.length-1;
            for(char c : croakAr){
                found = false;
                for(;i>=0;i--){
                    if(c==croakArr[i]){
                        found = true;
                        nCroak.deleteCharAt(i);
                        break;
                    }
                }
                if(!found)return -1;
            }
            croakOfFrogs = nCroak.toString();
            if(found)res++;
        }
        return res;
    }

    public static void main(String[] args) {
        new MainClass().minNumberOfFrogs("croakcroak");
    }

}
