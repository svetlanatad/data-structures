package grr;

public class ProductHashCode implements HashCode<StringHolder>{
    public int hashCode(StringHolder stringHolder) {
        String value = stringHolder.getValue();
        if (value == null) {
            return 0;
        }
        int product = 1;
        for (char c : value.toCharArray()) {
            product *=c;
        }
        return product;
    }
}
