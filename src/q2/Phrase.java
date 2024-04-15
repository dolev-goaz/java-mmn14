package q2;

// represents a phrase
public class Phrase {
    private String phrase;
    private String definition;

    // constructor
    public Phrase(String phrase, String definition) {
        this.phrase = phrase;
        this.definition = definition;
    }

    // get definition
    public String getDefinition() {
        return definition;
    }

    // get phrase
    public String getPhrase() {
        return phrase;
    }
}
