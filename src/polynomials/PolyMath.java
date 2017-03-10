package polynomials;
import static polynomials.Utils.*;

import java.util.ArrayList;
import java.util.List;

public class PolyMath {
    
    private static void checkMods(Polynomial p1, Polynomial p2) {
        if (p1.getModulus() != p2.getModulus()) {
            throw new IllegalArgumentException("Polynomials must have the same modulus.");
        }
    }
    
    public static Polynomial add(Polynomial p1, Polynomial p2) {
        checkMods(p1, p2);
        
        int deg = Math.max(p1.getDegree(), p2.getDegree());
        List<Integer> c = new ArrayList<Integer>();
        padWithZeros(c, deg+1);
        
        for (int i = 0; i <= deg; i++) {
            c.set(i, p1.getCoefficient(i) + p2.getCoefficient(i));
        }
        return new Polynomial(p1.getModulus(), c);
    }
}
