package polynomials;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuotientRing {
    
    private Polynomial poly;
    
    /**
     * Constructs the quotient group F_m[x]/p(x)
     */
    public QuotientRing(Polynomial base) {
        this.poly = base;
    }
    
    public Polynomial add(Polynomial p1, Polynomial p2) {
        modCheck(p1,p2);
        return reduce(PolyMath.add(p1, p2));
    }
    
    public Polynomial pow(Polynomial p, int n) {
        modCheck(p);
        return reduce(PolyMath.pow(p, n));
    }
    
    public Polynomial multiply(Polynomial p1, Polynomial p2) {
        modCheck(p1,p2);
        return reduce(PolyMath.multiply(p1, p2));
    }
    
    private void modCheck(Polynomial...polynomials) {
        int m = poly.getModulus();
        for (Polynomial p : polynomials) {
            if (p.getModulus() != m) {
                throw new IllegalArgumentException("Polynomial must have same"
                        + " modulus as the QuotientRing.");
            }
        }
    }
    
    public Polynomial reduce(Polynomial p) {
        modCheck(p);
        return PolyMath.divide(p, poly)[1];
    }
    
    public int getModulus() {
        return poly.getModulus();
    }
    
    public Polynomial getQuotient() {
        return poly;
    }
    
    public List<Polynomial> getElements() {
        List<Polynomial> res = new ArrayList<Polynomial>();
        int m = getModulus();
        Polynomial zero = Polynomial.ZERO(m);
        res.add(zero);
        
        List<Integer> coefs = new ArrayList<Integer>();
        Utils.padWithZeros(coefs, poly.getDegree());
        coefs.set(0, 1);
        
        Polynomial p = new Polynomial(m, coefs);
        while(!p.equals(zero)) {
            res.add(p);
            increment(coefs, m);
            p = new Polynomial(m, coefs);
        }
        
        Collections.sort(res);
        return res;
    }
    
    private static void increment(List<Integer> coefs, int mod) {
        for (int i = 0; i < coefs.size(); i++) {
            if (coefs.get(i) < mod - 1) {
                coefs.set(i, coefs.get(i) + 1);
                return;
            } else {
                coefs.set(i, 0);
            }
        }
        return;
    }
    
    public String toString() {
        return "F_" + getModulus() + "[x]/(" + poly + ")";
    }
}
