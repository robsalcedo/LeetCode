package BusiestTimeInMall;

class Solution {



    static int findBusiestPeriod(int[][] data) {
        int max = Integer.MIN_VALUE;
        int maxTime = 0;
        int peopleIn = 0;
        for(int i = 0; i<data.length;i++){
            int timeTmp = data[i][0];
            int j = i;
            while(j<data.length && data[i][0]==data[j][0])j++;
            peopleIn = getMaxPersons(i,j-1,data,peopleIn);
            if(peopleIn>max){
                max = peopleIn;
                maxTime = data[i][0];
            }
            i= j== i ? i : j-1;
        }
        return maxTime;
    }

    static private int getMaxPersons(int i, int j, int[][] data, int peopleIn){
        int res = peopleIn;
        for(int k = i; k<=j;k++){
            if(data[k][2]==0){
                res-=data[k][1];
            }else{
                res+=data[k][1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] data = new int[][]
                {{1487799425,14,1},{1487799425,4,0},{1487799425,2,0},{1487800378,10,1},{1487801478,18,0},{1487801478,18,1},{1487901013,1,0},{1487901211,7,1},{1487901211,7,0}};
        int res = new Solution().findBusiestPeriod(data);
        System.out.println(res);
    }

}

