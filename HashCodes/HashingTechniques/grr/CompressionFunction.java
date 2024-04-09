package grr;

public class CompressionFunction {
    private int N;
    public CompressionFunction(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N !<= 0");
        }
        this.N = N;
    }
    public int getN() {
        return N;
    }
    public int value(int hashCode) {
        if (N <= 0) {
            throw new IllegalStateException("N !<= 0");
        }
        return hashCode % N;
    }

}
