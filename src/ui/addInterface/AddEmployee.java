package ui.addInterface;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import logic.employees.Chef;
import logic.employees.Employee;
import logic.employees.Manager;
import logic.employees.Receptionist;
import logic.hotels.Hotel;
import logic.hotels.HotelCluster;
import ui.MyController;


public class AddEmployee extends AddUI
{

    private ChoiceBox<Employee.Position> positionBox;
    private TextField nameField;
    private TextField surnameField;

    public AddEmployee(Hotel hotel)
    {
        this.hotel = hotel;
        this.positionBox = new ChoiceBox<>();
        this.nameField = new TextField();
        this.surnameField = new TextField();
    }

    @Override public void initForm()
    {
        initMainLabel();
        addLeftLabel("Name:");
        addLeftLabel("Surname");
        addLeftLabel("Position:");
        addTextField(nameField, "Name");
        addTextField(surnameField, "Surname");
        addChoiceBox(positionBox, Employee.getPositions());
        initAddButton();
        initCancelButton();
    }

    @Override protected void initMainLabel()
    {
        mainLabel.setText(hotel.getName() + " > Add employee");
    }

    @Override protected void addTextField(TextField field, String promptText)
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

    protected void preformAddition()
    {
        //TODO: validation of name and surname
        try {
            switch (positionBox.getValue()) {
                case MANAGER:
                    hotel.addEmployee(new Manager(nameField.getText(), surnameField.getText()));
                    break;
                case RECEPTIONIST:
                    hotel.addEmployee(new Receptionist(nameField.getText(), surnameField.getText()));
                    break;
                case CHEF:
                    hotel.addEmployee(new Chef(nameField.getText(), surnameField.getText()));
                    break;
            }
        } catch (NullPointerException ex){
            System.out.println("No hotel data to add employees");
        }
    }
}
