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

    public boolean phraseExists(String phrase) {
        return this.innerDict.containsKey(phrase);
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
        if (!phraseExists(phrase)) {
            throw new PhraseNotFoundException(phrase);
        }
        this.innerDict.put(phrase, definition);
    }

    // deletes a phrase
    public void deletePhrase(String phrase) {
        this.innerDict.remove(phrase);
    }

    // searches for a phrase&definition pair by phrase
    public Phrase searchPhrase(String phrase) throws PhraseNotFoundException {
        if (!phraseExists(phrase)) {
            throw new PhraseNotFoundException(phrase);
        }
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
