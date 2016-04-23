package ui.addInterface;

import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import logic.Client;
import logic.hotels.Hotel;


public class AddClient extends AddUI
{
    private TextField nameField;
    private TextField surnameField;
    private ChoiceBox clientCount;
    private DatePicker fromField;
    private DatePicker toField;
    private HBox hBox;


    public AddClient(Hotel hotel)
    {
        this.hotel = hotel;
        clientCount = new ChoiceBox<>();
        nameField = new TextField();
        surnameField = new TextField();
        fromField = new DatePicker();
        toField = new DatePicker();
        hBox = new HBox();
    }

    @Override
    public void initForm()
    {
        initMainLabel();
        addLeftLabel("Name:");
        addLeftLabel("Surname:");
        addLeftLabel("Dates:");
        addTextField(nameField, "Name");
        addTextField(surnameField, "Surname");
        addDatePickers();
        initAddButton();
        initCancelButton();
    }

    protected void addDatePickers()
    {
        hBox.getChildren().removeAll(hBox.getChildren());
        fromField.setPromptText("From");
        fromField.setMaxWidth(95);
        toField.setPromptText("To");
        toField.setMaxWidth(95);
        hBox.getChildren().addAll(fromField, toField);
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER);
        vBoxOne.getChildren().add(hBox);
    }

    @Override
    protected void initMainLabel()
    {
        mainLabel.setText(hotel.getName() + " > Add client");
    }

    @Override
    protected void addTextField(TextField field, String promptText)
    {
        field.setPromptText(promptText);
        field.setMaxWidth(200);
        vBoxOne.getChildren().add(field);
    }

    @Override
    protected void clearFields()
    {
        nameField.clear();
        surnameField.clear();
    }

    @Override
    protected void preformAddition()
    {
        //TODO: validation of name and surname
        hotel.addClient(new Client(
                nameField.getText(),
                surnameField.getText(),
                fromField.getValue(),
                toField.getValue()
        ));
    }
}
