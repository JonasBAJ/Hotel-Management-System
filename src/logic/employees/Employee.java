package logic.employees;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import logic.Selectable;
import org.jetbrains.annotations.Contract;


public class Employee implements Selectable
{
    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private final Position position;
    private Boolean selected;

    public enum Position
    {
        MANAGER,
        CHEF,
        RECEPTIONIST
    }

    @Contract(pure = true) public static String[] getColumnNames()
    {
        String[] columns = new String[3];
        columns[0] = "Name";
        columns[1] = "Surname";
        columns[2] = "Position";
        return columns;
    }

    @Contract(pure = true) public static Position[] getPositions()
    {
        Position[] positions = new Position[3];
        positions[0] = Position.MANAGER;
        positions[1] = Position.RECEPTIONIST;
        positions[2] = Position.CHEF;
        return positions;
    }

    public Employee(String name, String surname, Position position)
    {
        super();
        this.name = new SimpleStringProperty(name);
        this.surname  = new SimpleStringProperty(surname);
        this.position = position;
        this.selected = false;
    }

    public String getName()
    {
        return name.get();
    }

    public void setName(String name)
    {
        this.name.setValue(name);
    }

    public Position getPosition() {
        return position;
    }

    public String getSurname()
    {
        return surname.get();
    }

    public void setSurname(String surname) {
        this.surname.setValue(surname);
    }

    private void calculateSalary()
    {
        System.out.println("Generic");
    }

    @Override
    public ObservableValue<Boolean> getSelected()
    {
        return new SimpleBooleanProperty(this.selected);
    }

    @Override
    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }
}
