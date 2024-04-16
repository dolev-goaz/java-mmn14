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
    private boolean isEditMode;

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
    private void clearDisplayList() {
        phraseList.getItems().clear();
        selectedPhrase = null;
    }

    private void displayDictionaryItems() {
        clearDisplayList();
        phraseList.getItems().addAll(dictionary.getPhrases());
    }

    private void displaySingleItem(Phrase phrase) {
        clearDisplayList();
        if (phrase == null) {
            // TODO: maybe show fallback
            return;
        }
        phraseList.getItems().add(phrase);
    }

    @FXML
    void onAddDefinition(ActionEvent event) {
        isEditMode = false;
        overlaySidebar.setVisible(true);
    }

    @FXML
    void onEditDefinition(ActionEvent event) {
        if (selectedPhrase == null) return;
        isEditMode = true;
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

        if (isEditMode) {
            handleEditPhrase(phrase, definition);
        } else {
            handleAddPhrase(phrase, definition);
        }
        displayDictionaryItems();

        // clear previous fields
        newExpressionField.clear();
        newDefinitionField.clear();

        // close the sidebar
        overlaySidebar.setVisible(false);
    }

    void handleAddPhrase(String phrase, String definition) {
        if (dictionary.phraseExists(phrase)) return;
        dictionary.addPhrase(phrase, definition);
    }
    void handleEditPhrase(String phrase, String definition) {
        dictionary.updatePhrase(phrase, definition);
        newExpressionField.setDisable(false);
    }

    @FXML
    void onCloseNewDefinition(ActionEvent event) {
        overlaySidebar.setVisible(false);
    }
}
