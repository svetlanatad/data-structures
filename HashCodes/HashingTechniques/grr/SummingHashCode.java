package grr;

public class SummingHashCode implements HashCode<StringHolder> {
    public int hashCode(StringHolder stringHolder) {
        String value = stringHolder.getValue();
        if (value == null) {
            return 0;
        }
        int sum = 0;
        for (char c : value.toCharArray()) {
            sum += c;
        }
        return sum;
    }
}
