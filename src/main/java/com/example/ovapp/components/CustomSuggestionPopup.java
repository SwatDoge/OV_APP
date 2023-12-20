package com.example.ovapp.components;

import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.stage.Window;
import javafx.stage.Popup;
import javafx.event.Event;
import javafx.event.EventType;

public class CustomSuggestionPopup extends Popup {

    private final ListView<String> suggestionsList = new ListView<>();

    private EventHandler<SuggestionSelectedEvent> onSuggestionSelected;

    public CustomSuggestionPopup(SortedList<String> suggestions) {
        suggestionsList.setItems(suggestions);
        suggestionsList.setPrefWidth(Region.USE_COMPUTED_SIZE);

        getContent().add(suggestionsList);
    }

    public void show(TextField textField) {
        Window stage = textField.getScene().getWindow();

        Bounds bounds = textField.localToScene(textField.getBoundsInLocal());
        double screenX = stage.getX() + bounds.getMinX() + bounds.getWidth();
        double screenY = stage.getY() + bounds.getMinY();

        setAutoHide(true);
        show(stage, screenX, screenY);

        suggestionsList.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            String selectedItem = suggestionsList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                if (onSuggestionSelected != null) {
                    onSuggestionSelected.handle(new SuggestionSelectedEvent(selectedItem));
                }
            }
        });
    }

    public void hide() {
        super.hide();
    }

    public void setOnSuggestionSelected(EventHandler<SuggestionSelectedEvent> onSuggestionSelected) {
        this.onSuggestionSelected = onSuggestionSelected;
    }

    public static class SuggestionSelectedEvent extends Event {
        public static final EventType<SuggestionSelectedEvent> SUGGESTION_SELECTED =
                new EventType<>(Event.ANY, "SUGGESTION_SELECTED");

        private final String selectedItem;

        public SuggestionSelectedEvent(String selectedItem) {
            super(SUGGESTION_SELECTED);
            this.selectedItem = selectedItem;
        }

        public String getSelectedItem() {
            return selectedItem;
        }

        @Override
        public String toString() {
            return selectedItem;  // Return the selected item directly
        }
    }
}
