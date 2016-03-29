package logic;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.Contract;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class Client implements Selectable
{
    private final SimpleStringProperty name;
    private final SimpleStringProperty surname;
    private Boolean selected;
    private LocalDate arrival;
    private LocalDate departure;
    private Long duration;

    public Client(String name, String surname, LocalDate arrival, LocalDate departure)
    {
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.selected = false;
        this.arrival = arrival;
        this.departure = departure;
        this.duration = ChronoUnit.DAYS.between(arrival, departure);
    }

    @Contract(pure = true) public static String[] getColumnNames()
    {
        String[] columns = new String[5];
        columns[0] = "Name";
        columns[1] = "Surname";
        columns[2] = "Arrival";
        columns[3] = "Departure";
        columns[4] = "Duration";
        return columns;
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

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public SimpleStringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }
}
