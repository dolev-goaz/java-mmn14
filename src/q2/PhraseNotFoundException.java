package q2;

public class PhraseNotFoundException extends RuntimeException {
    public PhraseNotFoundException(String phrase) {
        super(String.format("The phrase '%s' was not found.", phrase));
    }
}
