package decoding;

public class LowPreferredBitExecption extends Exception {
    
    int preferredBits;
    int minimumBits;
    
    public LowPreferredBitExecption(int preferredBits, int minimumBits) {
        super("Wrong bits entered. Minimum bits required was " + minimumBits + " while you entered " + preferredBits);
        this.preferredBits = preferredBits;
        this.minimumBits = minimumBits;
    }
    
    
}
