package polynomials;
import static polynomials.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    
    public static Polynomial subtract(Polynomial p1, Polynomial p2) {
        return add(p1, negate(p2));
    }
    
    public static Polynomial negate(Polynomial p) {
        List<Integer> c = p.getCoefficients().stream()
                .map(n -> -n).collect(Collectors.toList());
        return new Polynomial(p.getModulus(), c);
    }
    
    public static Polynomial multiply(Polynomial p1, Polynomial p2) {
        checkMods(p1, p2);
        
        int deg = p1.getDegree() + p2.getDegree();
        List<Integer> c = new ArrayList<Integer>();
        padWithZeros(c, deg+1);
        
        for (int i = 0; i <= p1.getDegree(); i++) {
            for (int j = 0; j <= p2.getDegree(); j++) {
                int val = p1.getCoefficient(i) * p2.getCoefficient(j);
                c.set(i+j, val + c.get(i+j));
            }
        }
        return new Polynomial(p1.getModulus(), c);
    }
    
    /**
     * Returns two polynomials {Q(x), R(x)} such that n = Q(x)*d + R(x) and 
     * deg(R(x)) < deg(d)
     * 
     */
    public static Polynomial[] divide(Polynomial n, Polynomial d) {
        checkMods(n, d);
        if (d.isZero()) {
            throw new IllegalArgumentException("Divisor cannot be zero.");
        }
        
        int m = n.getModulus();
        
        Polynomial q = Polynomial.ZERO(n.getModulus());
        Polynomial r = n;
        while (!r.isZero() && r.getDegree() >= d.getDegree()) {
            Polynomial rLead = r.leadingTerm();
            Polynomial dLead = d.leadingTerm();
            int tDeg = rLead.getDegree() - dLead.getDegree();
            int tCoef = Utils.divide(rLead.getLeadingCoefficient(), dLead.getLeadingCoefficient())[0];
            Polynomial t = new Polynomial(m, tCoef+"x^"+tDeg);
            q = add(q, t);
            r = subtract(r, multiply(t, d));
        }
        return new Polynomial[] {q, r};
    }
    
    public static Polynomial pow(Polynomial p, int n) {
        if (n == 0) {
            return new Polynomial(p.getModulus(), "1");
        } else {
            Polynomial res = new Polynomial(p.getModulus(), "1");
            for (int i = 0; i < n; i++) {
                res = multiply(res, p);
            }
            return res;
        }
    }
}
