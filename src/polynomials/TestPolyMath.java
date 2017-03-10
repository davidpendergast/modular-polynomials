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
        assertEquals(res, expected);
        
    }
    
    private static Polynomial p(int m, String poly) {
        return new Polynomial(m, poly);
    }

}
