package polynomials;

import java.util.List;

public class Utils {
    
    public static int mod(int val, int mod) {
        return (((val % mod) + mod) % mod); // thanks java
    }
    
    public static void padWithZeros(List<Integer> list, int newLength) {
        while (list.size() < newLength) {
            list.add(0);
        }
    }
    
    /**
     * Returns
     */
    public static int[] divide(int n, int d) {
        int q = n / d;
        int r = n - d*q;
        return new int[] {q, r};
    }

}
