package logic.employees;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Manager extends Employee
{

    public Manager(String name, String surname)
    {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.salary = new SimpleDoubleProperty(800.00);
        this.selected = false;
    }

    @Override
    Employee promoteTo(Position position) {
        if (this.position != position)
            switch (position){
                case CHEF:
                    return new Chef(getName(), getSurname());
                case RECEPTIONIST:
                    return new Receptionist(getName(), getSurname());
            }
        return this;
    }
}
