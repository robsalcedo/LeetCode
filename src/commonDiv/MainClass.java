package commonDiv;

public class MainClass {

    private int getGDC(int[] arr){
        int dev = arr[0];
        for(int i = 1;i<arr.length;i++){
            if(arr[i]%dev>0){
                dev*=arr[i];
            }
        }
        return dev>arr[arr.length-1] ? 1 : dev;
    }

    public static void main(String[] args) {
        System.out.println(new MainClass().getGDC(new int[]{104,168,190,191,192,209}));
        System.out.println(new GCD().findGCD(new int[]{104,168,190,191,192,209}));
    }

    static class GCD {
        // Function to return gcd of a and b
         int gcd(int a, int b)
        {
            if (a == 0)
                return b;
            return gcd(b % a, a);
        }

        // Function to find gcd of array of
        // numbers
        int findGCD(int arr[])
        {
            int result = arr[0];
            for (int i = 1; i < arr.length; i++)
                result = gcd(arr[i], result);

            return result;
        }
    }

}
