package Budget;

import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {

    static double findGrantsCap(double[] grantsArray, double newBudget) {
        Arrays.sort(grantsArray);
        double savings = 0.0;
        double amount = newBudget/grantsArray.length;
        int i = 0;
        while(i<grantsArray.length){
            if(amount > grantsArray[i]){
                savings+=amount-grantsArray[i];
                i++;
            }else{
                amount = (savings/(grantsArray.length - i))+amount;
                if(amount<=grantsArray[i])break;
                savings=0;
            }
        }
        return amount;
    }

    public static void main(String[] args) {
        double amt = findGrantsCap(new double[]{2,100,50,120,167}, 400.0);
        System.out.println(amt);
    }

}
