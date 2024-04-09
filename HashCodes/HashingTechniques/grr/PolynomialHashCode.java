package grr;

public class PolynomialHashCode implements HashCode<StringHolder> {
    private final int constant;
    public PolynomialHashCode(int constant) {
        if (constant <= 0) {
            throw new IllegalArgumentException("C !<= 0");
        }
        this.constant = constant;
    }
    public int hashCode(StringHolder stringHolder) {
        String value = stringHolder.getValue();
        if (value == null) {
            return 0;
        }
        int hash = 0;
        for (char c : value.toCharArray()) {
            hash = constant * hash + (int) c;
        }
        return hash;
    }
}

