package polynomials;

public class QuotientGroup {
    
    private Polynomial poly;
    
    /**
     * Constructs the quotient group F_m[x]/p(x)
     */
    public QuotientGroup(Polynomial base) {
        this.poly = base;
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
