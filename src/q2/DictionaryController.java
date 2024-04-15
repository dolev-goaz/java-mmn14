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

    public void initialize() {
        dictionary = new Dictionary();
        dictionary.addPhrase("Dolev", "A modern day legend");
        displayListItems();
        overlaySidebar.setVisible(false);
    }

    private void displayListItems() {
        phraseList.getItems().clear();
        phraseList.getItems().addAll(dictionary.getPhrases());
    }

    @FXML
    void onAddDefinition(ActionEvent event) {
        overlaySidebar.setVisible(true);
    }

    @FXML
    void onClearSearch(ActionEvent event) {

    }

    @FXML
    void onEditDefinition(ActionEvent event) {

    }

    @FXML
    void onRemoveDefinition(ActionEvent event) {

    }

    @FXML
    void onSearch(ActionEvent event) {

    }

    @FXML
    void onSubmitNewDefinition(ActionEvent event) {
        // TODO: add new definition
        String phrase = newExpressionField.getText();
        String definition = newDefinitionField.getText();
        if (phrase.isEmpty() || definition.isEmpty()) return;
        if (dictionary.phraseExists(phrase)) return;

        dictionary.addPhrase(phrase, definition);
        displayListItems();
        overlaySidebar.setVisible(false);
    }

    @FXML
    void onCloseNewDefinition(ActionEvent event) {
        overlaySidebar.setVisible(false);
    }
}
