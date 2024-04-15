package q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class DictionaryController {

    @FXML
    private TextField newDefinitionField;

    @FXML
    private TextField newExpressionField;

    @FXML
    private BorderPane overlaySidebar;

    @FXML
    private TextField searchField;


    @FXML
    private ListView<Phrase> phraseList;

    private Dictionary dictionary;
    private Phrase selectedPhrase;

    public void initialize() {
        dictionary = new Dictionary();
        dictionary.addPhrase("Dolev", "A modern day legend");
        initializeDictionaryList();
        overlaySidebar.setVisible(false);
    }

    private void initializeDictionaryList() {
        selectedPhrase = null;
        phraseList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            selectedPhrase = newValue;
        });
        displayDictionaryItems();
    }

    private void displayDictionaryItems() {
        // TODO: clear selection
        phraseList.getItems().clear();
        phraseList.getItems().addAll(dictionary.getPhrases());
    }

    private void displaySingleItem(Phrase phrase) {
        // TODO: clear selection
        phraseList.getItems().clear();
        phraseList.getItems().add(phrase);
    }

    @FXML
    void onAddDefinition(ActionEvent event) {
        overlaySidebar.setVisible(true);
    }

    @FXML
    void onEditDefinition(ActionEvent event) {
        /* TODO: split the save logic to create or update. this should be inside update,
         in the same method that handles the "confirm"
        */
        if (selectedPhrase == null) return;
        newExpressionField.setText(selectedPhrase.getPhrase());
        newDefinitionField.setText(selectedPhrase.getDefinition());
        newExpressionField.setDisable(true);
        overlaySidebar.setVisible(true);
    }

    @FXML
    void onRemoveDefinition(ActionEvent event) {
        if (selectedPhrase == null) return;
        dictionary.deletePhrase(selectedPhrase.getPhrase());
        displayDictionaryItems();
        selectedPhrase = null;
    }

    @FXML
    void onSearch(ActionEvent event) {
        String phrase = searchField.getText();
        Phrase foundPhrase = dictionary.searchPhrase(phrase);
        // TODO: check phrase is found(shows null at the moment)
        displaySingleItem(foundPhrase);
    }

    @FXML
    void onClearSearch(ActionEvent event) {
        searchField.clear();
        displayDictionaryItems();
    }

    @FXML
    void onSubmitNewDefinition(ActionEvent event) {
        String phrase = newExpressionField.getText();
        String definition = newDefinitionField.getText();

        // check it's a new definition with valid data
        if (phrase.isEmpty() || definition.isEmpty()) return;
        if (dictionary.phraseExists(phrase)) return;

        // add the definition and refresh the list
        dictionary.addPhrase(phrase, definition);
        displayDictionaryItems();

        // clear previous fields
        newExpressionField.clear();
        newDefinitionField.clear();

        // close the sidebar
        overlaySidebar.setVisible(false);

        // undo disable(in case of update logic)
        newExpressionField.setDisable(false);
    }

    @FXML
    void onCloseNewDefinition(ActionEvent event) {
        overlaySidebar.setVisible(false);
    }
}
