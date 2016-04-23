package ui.addInterface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import logic.hotels.Hotel;
import logic.hotels.HotelCluster;
import ui.MyController;


public abstract class AddUI
{
    @FXML protected VBox vBoxOne;
    @FXML protected VBox vBoxTwo;
    @FXML protected Button addButton;
    @FXML protected Button cancelButton;
    @FXML protected Label mainLabel;
    protected Stage stage;
    protected Hotel hotel;


    public abstract void initForm();

    protected abstract void initMainLabel();

    protected abstract void addTextField(TextField field, String promptText);

    protected abstract void preformAddition();

    protected abstract void clearFields();

    protected void initCancelButton()
    {
        cancelButton.setOnAction(e -> {
            stage.close();
            clearFields();
        });
    }

    protected void initAddButton()
    {
        addButton.setOnAction(e -> {
            preformAddition();
            stage.close();
            clearFields();
        });
    }

    protected void addLeftLabel(String text)
    {
        Label label = new Label(text);
        vBoxTwo.getChildren().add(label);
    }

    protected <T> void addChoiceBox(ChoiceBox<T> box, T[] values)
    {
        box.getItems().removeAll(box.getItems());
        box.getItems().addAll(values);
        box.setValue(values[0]);
        box.setMaxWidth(200);
        this.vBoxOne.getChildren().add(box);
    }

    public void setStage(Stage stage)
    {
        this.stage = stage;
    }
}
