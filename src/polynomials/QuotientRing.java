package polynomials;

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
    
    public String toString() {
        return "F_" + getModulus() + "[x]/(" + poly + ")";
    }
}
