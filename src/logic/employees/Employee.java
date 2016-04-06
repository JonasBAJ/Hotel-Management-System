package logic.employees;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import logic.Hotel;
import logic.Selectable;
import org.jetbrains.annotations.Contract;

/**
 * Employee class is base class for all employee types.
 * This class provides main methods for all employees.
 * Also implements Selectable interface thus allows employees
 * to be selected and manipulated in Hotel class.
 */
public abstract class Employee implements Selectable
{
    /**
     * Field contains employee's name.
     */
    protected SimpleStringProperty name;
    /**
     * Field contains employee's surname.
     */
    protected SimpleStringProperty surname;
    /**
     * Field contains employee's salary.
     */
    protected DoubleProperty salary;
    /**
     * Field contains employee's position.
     */
    protected Position position;
    /**
     * Field contains boolean value for easier manipulation in Hotel object.
     */
    protected Boolean selected;

    /**
     * Enum contains position field types.
     */
    public enum Position
    {
        MANAGER,
        CHEF,
        RECEPTIONIST
    }

    /**
     * Method returns array of object fields for easier ui development,
     * to quickly display information in droop down list or etc.
     * @return fields
     */
    @Contract(pure = true)
    public static String[] getFieldNames()
    {
        String[] fields = new String[4];
        fields[0] = "Name";
        fields[1] = "Surname";
        fields[2] = "Position";
        fields[3] = "Salary";
        return fields;
    }

    /**
     * Method returns array of available positions for Employee object for easier ui development,
     * to quickly display information in droop down list or etc.
     * @return fields
     */
    @Contract(pure = true) public static Position[] getPositions()
    {
        Position[] positions = new Position[3];
        positions[0] = Position.MANAGER;
        positions[1] = Position.RECEPTIONIST;
        positions[2] = Position.CHEF;
        return positions;
    }


    abstract Employee promoteTo(Position position);

    /**
     * Getter for employee's name.
     * @return name
     */
    public String getName()
    {
        return name.get();
    }

    /**
     * Setter for employee's name.
     * @param name
     *      New employee's name.
     */
    public void setName(String name)
    {
        this.name.setValue(name);
    }

    /**
     * Getter for employee's surname.
     * @return surname
     */
    public String getSurname()
    {
        return surname.get();
    }

    /**
     * Setter for employee's surname.
     * @param surname
     *      New employee's surname.
     */
    public void setSurname(String surname) {
        this.surname.setValue(surname);
    }

    /**
     * Setter for employee's salary.
     * @param salary
     *      New employee's salary.
     */
    public void setSalary(Double salary) {
        this.salary.setValue(salary);
    }

    /**
     * Getter for employee's salary.
     * @return salary
     */
    public Double getSalary() {
        return this.salary.get();
    }

    /**
     * Getter for employee's position.
     * @return position
     */
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Boolean getSelected()
    {
        return selected;
    }

    @Override
    public void setSelected(Boolean selected)
    {
        this.selected = selected;
    }
}
