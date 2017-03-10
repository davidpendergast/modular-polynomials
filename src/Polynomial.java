import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Polynomial {
    
    private int mod;
    private List<Integer> coefficients;
    
    public Polynomial(int mod, String poly) {
        this.mod = mod;
        poly = poly.replaceAll(" ", "");
        List<String> terms = new ArrayList<String>();
        int i = 0;
        while (i <= poly.length()) {
            if (i == poly.length()) {
                terms.add(poly);
                break;
            }
            if (poly.charAt(i) == '+') {
                terms.add(poly.substring(0, i));
                poly = poly.substring(i+1, poly.length()); // slice off +
                i = 1;
            } else if (poly.charAt(i) == '-'){
                terms.add(poly.substring(0, i));
                poly = poly.substring(i, poly.length()); // don't slice -
                i = 2;
            } else {
                i++;
            }
        }
        
        System.out.println(terms);
    }
    
    public Polynomial(int mod, List<Integer> coefficients) {
        this.mod = mod;
        Runnable x = () -> {System.out.println("yo");};
        this.coefficients = coefficients.stream()
                .map(i -> mod(i, mod))
                .collect(Collectors.toList());
        removeLeadingZeros();
        
    }
    
    private void removeLeadingZeros() {
        while (coefficients.size() > 0 && getCoefficient(getDegree()) == 0) {
            coefficients.remove(coefficients.size()-1);
        }
    }
    
    public int getDegree() {
        return coefficients.size() - 1;
    }
    
    public int getModulus() {
        return mod;
    }
    
    public int getCoefficient(int pow) {
        if (pow < 0 || pow >= coefficients.size()) {
            return 0;
        } else {
            return coefficients.get(pow);
        }
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = getDegree(); i >= 0; i--) {
            int coeff = getCoefficient(i);
            if (coeff > 0) {
                if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (coeff != 1 || i == 0) {
                    sb.append(coeff);
                }
                if (i > 0) {
                    sb.append("x");
                    if (i > 1) {
                        sb.append("^"+i);
                    }
                }
            }
        }
        if (sb.length() == 0) {
            return "0";
        } else {
            return sb.toString();
        }
    }
    
    public static int mod(int val, int mod) {
        return (((val % mod) + mod) % mod); // thanks java
    }
    
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(2,4,0,6,3,2);
        Polynomial p = new Polynomial(3, list);
        System.out.println(p);
        
        String input = "mod=3, 3x^3 + x^4 - 5x^6 + 9";
        System.out.println(input);
        new Polynomial(3, input);
    }

}
