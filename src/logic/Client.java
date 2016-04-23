package logic;

import javafx.beans.property.SimpleStringProperty;
import logic.employees.Employee;
import org.jetbrains.annotations.Contract;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Client class can be created and added to Hotel object.
 * This object hold information about client.
 */
public class Client implements Selectable
{
    /**
     * Client name field.
     */
    private String name;
    /**
     * Client surname field.
     */
    private String surname;
    /**
     * For now this field is not implemented.
     */
    private Boolean selected;
    /**
     * Field holds information about arrival date.
     */
    private LocalDate arrival;
    /**
     * Field holds information about departure date.
     */
    private LocalDate departure;
    /**
     * Field contains duration of stay.
     */
    private Long duration;

    /**
     * Object constructor.
     * @param name
     *      Client's name.
     * @param surname
     *      Client's surname.
     * @param arrival
     *      Arrival date.
     * @param departure
     *      Departure date.
     */
    public Client(String name, String surname, LocalDate arrival, LocalDate departure)
    {
        this.name = name;
        this.surname = surname;
        this.selected = false;
        this.arrival = arrival;
        this.departure = departure;
        this.duration = ChronoUnit.DAYS.between(arrival, departure);
    }

    /**
     * Method returns user class field names for easier ui development,
     * to quickly display information about client.
     * @return columns
     */
    @Override
    public String[] getColumnNames() {
        String[] columns = new String[6];
        columns[0] = "Selected";
        columns[1] = "Name";
        columns[2] = "Surname";
        columns[3] = "Arrival";
        columns[4] = "Departure";
        columns[5] = "Duration";
        return columns;
    }

    @Override
    public String toString() {
        return getSelected() +" "+
                getName() +" "+
                getSurname() +" "+
                getArrival() +" "+
                getDeparture() +" "+
                getDuration();
    }

    @Override
    public Boolean getSelected()
    {
        return this.selected;
    }

    @Override
    public void setSelected(Boolean selected)
    {
        this.selected = selected;
    }

    /**
     * Method returns client's name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Method sets client's name.
     * @param name
     *      New client's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method returns client's surname.
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Method sets client's surname.
     * @param surname
     *      New client's surname.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Method returns client's arrival date.
     * @return arrival
     */
    public LocalDate getArrival() {
        return arrival;
    }

    /**
     * Method sets client's arrival date.
     * @param arrival
     *      New client's arrival date.
     */
    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    /**
     * Method returns client's departure date.
     * @return departure
     */
    public LocalDate getDeparture() {
        return departure;
    }

    /**
     * Method sets client's departure date.
     * @param departure
     *      New client's departure date.
     */
    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    /**
     * Method returns client's stay duration.
     * @return departure
     */
    public Long getDuration() {
        return duration;
    }

    /**
     * Method sets client's stay duration.
     * @param duration
     *      New client's duration.
     */
    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Selectable getSelectable()
    {
        return this;
    }

    @Override
    public String[] getFields() {
        String [] fieldValues = new String[6];
        fieldValues[0] = getSelected().toString();
        fieldValues[1] = getName();
        fieldValues[2] = getSurname();
        fieldValues[3] = getArrival().toString();
        fieldValues[4] = getDeparture().toString();
        fieldValues[5] = getDuration().toString();
        return fieldValues;
    }
}
