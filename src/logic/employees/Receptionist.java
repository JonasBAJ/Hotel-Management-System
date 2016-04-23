package logic.employees;


public class Receptionist extends Employee
{
    public Receptionist(String name, String surname)
    {
        this.name = name;
        this.surname = surname;
        this.position = Position.RECEPTIONIST;
        this.salary = 420.00;
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
