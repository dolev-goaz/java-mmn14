package q2;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

// represents a dictionary
public class Dictionary {
    private TreeMap<String, String> innerDict;

    // constructor
    public Dictionary() {
        this.innerDict = new TreeMap<String, String>();
    }

    // checks whether a phrase exists in the dictionary
    private boolean phraseExists(String phrase) {
        // note: could also make this method public and call it from outside the class
        // I think it looks cleaner this way, where the dictionary handles all the logic
        return this.innerDict.containsKey(phrase);
    }

    // checks if a phrase exists. If it doesnt- throws an exception
    // used as a helper method
    private void assertPhraseExists(String phrase) throws PhraseNotFoundException {
        if (!phraseExists(phrase)) {
            throw new PhraseNotFoundException(phrase);
        }
    }

    // adds a phrase
    public void addPhrase(String phrase, String definition) throws PhraseAlreadyExistsException {
        if (phraseExists(phrase)) {
            throw new PhraseAlreadyExistsException(phrase);
        }
        this.innerDict.put(phrase, definition);
    }

    // updates a phrase
    public void updatePhrase(String phrase, String definition) throws PhraseNotFoundException {
        assertPhraseExists(phrase);
        this.innerDict.put(phrase, definition);
    }

    // deletes a phrase
    public void deletePhrase(String phrase) throws PhraseNotFoundException {
        assertPhraseExists(phrase);
        this.innerDict.remove(phrase);
    }

    // searches for a phrase&definition pair by phrase
    public Phrase searchPhrase(String phrase) throws PhraseNotFoundException {
        assertPhraseExists(phrase);
        return new Phrase(phrase, this.innerDict.get(phrase));
    }

    // returns a list of all phrases
    public ArrayList<Phrase> getPhrases() {
        ArrayList<Phrase> out = new ArrayList<Phrase>();
        for (Map.Entry<String, String> entry : this.innerDict.entrySet()) {
            out.add(new Phrase(entry.getKey(), entry.getValue()));
        }
        return out;
    }
}
