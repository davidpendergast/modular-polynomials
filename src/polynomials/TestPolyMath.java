package polynomials;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPolyMath {
    
    @Test
    public void testAdd() {
        Polynomial p1 = p(6, "x^3 + 5x + 9x^4 + 11x + 9");
        Polynomial p2 = p(6, "3x^3 - 1x + 2x^5 + 13x - 1");
        Polynomial expected = p(6, "4x^3 + 4x + 9x^4 + 2x^5 + 24x + 8");
        Polynomial res = PolyMath.add(p1, p2);
        assertEquals(expected, res);
    }
    
    @Test
    public void testMultiply() {
        Polynomial p1 = p(6, "x + 1 - 7x^2 + 9x^17");
        Polynomial p2 = p(6, "3x -7x^2 + 23x^7 - 20");
        Polynomial expected = p(6, "207 x^24 - 63 x^19 + 27 x^18 - 180 x^17 - "
                + "161 x^9 + 23 x^8 + 23 x^7 + 49 x^4 - 28 x^3 + 136 x^2 - "
                + "17 x - 20");
        Polynomial res = PolyMath.multiply(p1, p2);
        System.out.println(res.getModulus());
        assertEquals(expected, res);
    }
    
    @Test
    public void testPow() {
        Polynomial p = p(15, "x^2 - 3x + 7");
        int n = 3;
        Polynomial expected = p(15, "x^6 - 9 x^5 + 48 x^4 - 153 x^3 + 336 x^2 - 441 x + 343");
        Polynomial res = PolyMath.pow(p, n);
        assertEquals(expected, res);
        
    }
    
    private static Polynomial p(int m, String poly) {
        return new Polynomial(m, poly);
    }

}
