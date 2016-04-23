package logic.employees;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import logic.Selectable;
import org.jetbrains.annotations.Contract;

import java.text.ParseException;

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
    protected String name;
    /**
     * Field contains employee's surname.
     */
    protected String surname;
    /**
     * Field contains employee's salary.
     */
    protected Double salary;
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
        RECEPTIONIST;

        public static Position parsePosition(String pos) {
            switch (pos){
                case "MANAGER":
                    return MANAGER;
                case "CHEF":
                    return CHEF;
                case "RECEPTIONIST":
                    return RECEPTIONIST;
                default:
                    return null;
            }
        }
    }

    /**
     * Method returns array of object fields for easier ui development,
     * to quickly display information in droop down list or etc.
     * @return columns
     */
    @Override
    public String[] getColumnNames() {
        String[] columns = new String[5];
        columns[0] = "Selected";
        columns[1] = "Name";
        columns[2] = "Surname";
        columns[3] = "Position";
        columns[4] = "Salary";
        return columns;
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

    /**
     * Abstract method which should provide utility to employee object to be
     * promoted to other category (type) of employee.
     * @param position
     *      Category promote to.
     * @return Chef, Receptionist or Manager
     */
    abstract Employee promoteTo(Position position);

    /**
     * Getter for employee's name.
     * @return name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for employee's name.
     * @param name
     *      New employee's name.
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for employee's surname.
     * @return surname
     */
    public String getSurname()
    {
        return surname;
    }

    /**
     * Setter for employee's surname.
     * @param surname
     *      New employee's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Setter for employee's salary.
     * @param salary
     *      New employee's salary.
     */
    public void setSalary(Double salary) {
        this.salary = salary;
    }

    /**
     * Getter for employee's salary.
     * @return salary
     */
    public Double getSalary() {
        return this.salary;
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

    @Override
    public String toString() {
        return this.selected +" "+
                this.getName() +" "+
                this.getSurname() +" "+
                this.getPosition() +" "+
                this.getSalary();
    }

    @Override
    public String[] getFields() {
        String [] fieldValues = new String[5];
        fieldValues[0] = getSelected().toString();
        fieldValues[1] = getName();
        fieldValues[2] = getSurname();
        fieldValues[3] = getPosition().toString();
        fieldValues[4] = getSalary().toString();
        return fieldValues;
    }
}
