package grr;

public class HashFunction<T> {
    private HashCode<T> function;
    private CompressionFunction compression;
    public HashFunction(HashCode<T> hashCodeFunction, CompressionFunction compressionFunction) {
        this.function = hashCodeFunction;
        this.compression = compressionFunction;
    }
    public int hash(T obj) {
        int hashCode = function.hashCode(obj);
        return compression.value(hashCode);
    }
}
