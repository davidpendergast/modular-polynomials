package polynomials;

import org.junit.Test;

public class TestQuotientRing {
    
    @Test
    public void testGetElements() {
        QuotientRing qr = q(4, "x^3 + 2x + 1");
        //System.out.println(qr.getElements());
    }
    
    @Test
    public void testGetOrder() {
        QuotientRing qr = q(4, "x^3 + 2x + 1");
        for (Polynomial p : qr.getElements()) {
            //System.out.println(qr.getOrder(p)+": "+p);
        }
    }
    
    @Test
    public void testGetPrimitives() {
        QuotientRing qr = q(3, "x^3 + 2x + 1");
        for (Polynomial p : qr.getPrimatives()) {
            System.out.println(p);
        }
    }
    
    private static Polynomial p(int m, String p) {
        return new Polynomial(m, p);
    }
    
    private static QuotientRing q(int m, String p) {
        return new QuotientRing(p(m, p));
    }
    
    

}
