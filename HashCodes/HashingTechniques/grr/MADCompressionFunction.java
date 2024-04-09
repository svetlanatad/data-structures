package grr;
import java.util.Random;
public class MADCompressionFunction extends CompressionFunction {
    private int a;
    private int b;
    private int p;
    public MADCompressionFunction(int N) {
        super(N);
        this.p = findNextPrime(N);
        Random random = new Random();
        this.a = random.nextInt(p);
        this.b = random.nextInt(p);
    }
    public int value(int hashCode) {
        int result = (a * hashCode + b) % p;
        if (result < 0) {
            result += p;
        }
        return result % getN();

    }
    private int findNextPrime(int num) {
        while (!isPrime(++num)) {
        }
        return num;
    }
    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
