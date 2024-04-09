package grr;

public class StringHashCode implements HashCode<StringHolder> {
    public int hashCode(StringHolder stringHolder) {
        String value = stringHolder.getValue();
        if(value == null){
            return 0;
        }else{
            return value.hashCode();
        }
    }
}
