package logic.employees;

public class Manager extends Employee
{

    public Manager(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
        this.position = Position.MANAGER;
        this.salary = 800.00;
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
