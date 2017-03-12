package polynomials;
import static polynomials.Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Polynomial implements Comparable<Polynomial>{
    
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
        
        coefficients = new ArrayList<Integer>();
        padWithZeros(coefficients, terms.size());
        for (String term : terms) {
            int coef = 0;
            int pow = 0;
            if (!term.contains("x")) {
                coef = Integer.parseInt(term); 
                pow = 0;
            } else if (!term.contains("^")) {
                term = term.substring(0, term.indexOf("x"));
                pow = 1;
                if (term.equals("")) {
                    coef = 1;
                } else {
                    coef = Integer.parseInt(term);
                }
            } else {
                String[] parts = term.split("x\\^");
                if (parts[0].equals("")) {
                    coef = 1;
                } else {
                    coef = Integer.parseInt(parts[0]);
                }
                pow = Integer.parseInt(parts[1]);
            }
            if (getDegree() < pow) {
                padWithZeros(coefficients, pow+1);
            }
            coefficients.set(pow, mod(coef + coefficients.get(pow), mod));
        }
        removeLeadingZeros();
    }
    
    public Polynomial(int mod, List<Integer> coefficients) {
        this.mod = mod;
        this.coefficients = coefficients.stream()
                .map(i -> mod(i, mod))
                .collect(Collectors.toList());
        removeLeadingZeros();
    }
    
    public boolean isZero() {
        return coefficients.size() == 0;
    }
    
    public static Polynomial ZERO(int m) {
        return new Polynomial(m, "0");
    }
    
    public static Polynomial ONE(int m) {
        return new Polynomial(m, "1");
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
            return mod(coefficients.get(pow), getModulus());
        }
    }
    
    public int getLeadingCoefficient() {
        if (getDegree() == -1) {
            return 0;
        } else {
            return getCoefficient(getDegree());
        }
    }
    
    public List<Integer> getCoefficients() {
        return new ArrayList<Integer>(coefficients);
    }
    
    public Polynomial leadingTerm() {
        int coef = getLeadingCoefficient();
        if (coef == 0) {
            return ZERO(getModulus());
        } else {
            int deg = getDegree();
            return new Polynomial(getModulus(), coef + "x^" + deg);
        }
    }
    
    public int numTerms() {
        int num = 0;
        for (Integer c : coefficients) {
            if (c != 0) {
                num++;
            }
        }
        return num;
    }
    
    @Override
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
    
    @Override
    public boolean equals(Object other) {
        if (other instanceof Polynomial) {
            Polynomial p = (Polynomial)other;
            if (p.getModulus() != getModulus()) {
                return false;
            } else {
                return p.coefficients.equals(coefficients);
            }
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(getModulus(), coefficients);
    }
    
    public int compareTo(Polynomial other) {
        if (other.getDegree() != getDegree()) {
            return getDegree() - other.getDegree();
        } else {
            for (int i = getDegree(); i >= 0; i--) {
                if (other.getCoefficient(i) != getCoefficient(i)) {
                    return getCoefficient(i) - other.getCoefficient(i);
                }
            }
            return 0;
        }
    }
    
    public static void main(String[] args) {
        String input = "2x^2 + x^6 - 5x^6 + 19 + 12 + x^1 + x^12";
        int mod = 9;
        System.out.println("mod="+mod+", input="+input);
        Polynomial p = new Polynomial(mod, input);
        System.out.println(p);
    }

}
