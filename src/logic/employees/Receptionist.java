package logic.employees;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Receptionist extends Employee
{
    public Receptionist(String name, String surname)
    {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.salary = new SimpleDoubleProperty(420.00);
        this.selected = false;
    }

    @Override
    Employee promoteTo(Position position) {
        if (this.position != position)
            switch (position){
                case CHEF:
                    return new Chef(getName(), getSurname());
                case MANAGER:
                    return new Manager(getName(), getSurname());
            }
        return this;
    }

    public void makeReservation()
    {

    }
}
