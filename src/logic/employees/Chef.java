package logic.employees;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Chef class provides constructor for employee with chef position.
 * This class will also be providing other methods
 */
public class Chef extends Employee
{
    public Chef(String name, String surname)
    {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.salary = new SimpleDoubleProperty(680.00);
        this.selected = false;
    }

    @Override
    Employee promoteTo(Position position) {
        if (this.position != position)
            switch (position){
                case MANAGER:
                    return new Manager(getName(), getSurname());
                case RECEPTIONIST:
                    return new Receptionist(getName(), getSurname());
            }
        return this;
    }
}
