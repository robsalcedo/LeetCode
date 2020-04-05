package bitOperation;

public class BitOperation {

    private static short countBits(int x){
        Character[] c = new Character[]{'a','b'};
        short numBits = 0;
        while(x!=0){
            numBits += (x & 1);
            x>>>=1;
        }
        return numBits;
    }

    private static short parity(long x){
        short res = 0;
        while(x!=0){
            res ^= (x & 1);
            x>>>=1;
        }
        return res;
    }

    private static void printNumberLRightShift(int x){
        int i = 4999;
        while(x>=0){
            i =i>>1;
            System.out.println(i );
            x--;
        }
    }

    public static int[] countBits2(int num) {
        int[] res = new int[num+1];
        res[0] = 0;
        if(num==0)return res;
        res[1] = 1;
        int n = 2;
        for (int i=2; i<res.length; i *= 2) {
            int next = Math.min(i * 2, res.length);
            for (int j=i; j<next; j++) {
                res[j] = res[j - i] + 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        doubleTest();
    }

    

    private static void doubleTest() {

        int[] factorArray = new int[(210/3600)+1];
        Double d = new Double(10/60);
        factorArray[d.intValue()]++;
        d = new Double(70/60);
        factorArray[d.intValue()]++;
        d = new Double(0/60);
        factorArray[d.intValue()]++;
        d = new Double(15/60);
        factorArray[d.intValue()]++;

    }
}
