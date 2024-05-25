package q2;

public class PhraseAlreadyExistsException extends RuntimeException {
    public PhraseAlreadyExistsException(String phrase) {
        super(String.format("The phrase '%s' already exists.", phrase));
    }
}
