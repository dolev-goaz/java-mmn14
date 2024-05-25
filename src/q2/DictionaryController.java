package q2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
        phraseList.getItems().add(phrase);
    }

    @FXML
    void onAddDefinition(ActionEvent event) {
        isEditMode = false;
        overlaySidebar.setVisible(true);
    }

    @FXML
    void onEditDefinition(ActionEvent event) {
        if (selectedPhrase == null) {
            warnPhraseNotSelected();
            return;
        };
        isEditMode = true;
        newExpressionField.setText(selectedPhrase.getPhrase());
        newDefinitionField.setText(selectedPhrase.getDefinition());
        newExpressionField.setDisable(true);
        overlaySidebar.setVisible(true);
    }

    @FXML
    void onRemoveDefinition(ActionEvent event) {
        if (selectedPhrase == null) {
            warnPhraseNotSelected();
            return;
        };
        dictionary.deletePhrase(selectedPhrase.getPhrase());
        displayDictionaryItems();
        selectedPhrase = null;
    }

    @FXML
    void onSearch(ActionEvent event) {
        String phrase = searchField.getText();
        if (phrase.isEmpty()) {
            showWarningAlert("Empty phrase", "The provided phrase was empty.");
            return;
        }
        Phrase foundPhrase = dictionary.searchPhrase(phrase);
        if (foundPhrase == null) {
            showWarningAlert("Not found", String.format("The phrase '%s' was not found.", phrase));
            return;
        }
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

        boolean success;
        if (isEditMode) {
            success = handleEditPhrase(phrase, definition);
        } else {
            success = handleAddPhrase(phrase, definition);
        }
        if (!success) {
            return; // operation failed, stop cleanup
        }
        displayDictionaryItems();

        // clear previous fields
        newExpressionField.clear();
        newDefinitionField.clear();

        // close the sidebar
        overlaySidebar.setVisible(false);
    }
    private static void warnPhraseNotSelected() {
        showWarningAlert("Phrase not selected", "please select a phrase");
    }

    private static void showWarningAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    boolean handleAddPhrase(String phrase, String definition) {
        if (dictionary.phraseExists(phrase)) {
            showWarningAlert("Already exists",
                    String.format("The phrase '%s' already exists in the dictionary.", phrase));
            return false;
        }
        dictionary.addPhrase(phrase, definition);
        return true;
    }
    boolean handleEditPhrase(String phrase, String definition) {
        try {
            dictionary.updatePhrase(phrase, definition);
        } catch (PhraseNotFoundException e) {
            showWarningAlert("Error while editing phrase", e.getMessage());
            return false;
        }
        newExpressionField.setDisable(false);
        return true;
    }

    @FXML
    void onCloseNewDefinition(ActionEvent event) {
        overlaySidebar.setVisible(false);
    }
}
