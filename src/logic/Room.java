package logic;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.Contract;

public class Room implements Selectable
{
    private final int bedrooms;
    private final Type type;
    private boolean booked;
    private Boolean selected;


    public enum Type
    {
        STANDARD,
        PREMIUM,
        SUITE
    }

    @Contract(pure = true) public static Integer[] getBedroomCount()
    {
        Integer[] counts = new Integer[4];
        counts[0] = 1;
        counts[1] = 2;
        counts[2] = 3;
        counts[3] = 4;
        return counts;
    }

    @Contract(pure = true) public static Type[] getTypes()
    {
        Type[] types = new Type[3];
        types[0] = Type.PREMIUM;
        types[1] = Type.STANDARD;
        types[2] = Type.SUITE;
        return types;
    }

    @Contract(pure = true) public static String[] getColumnNames()
    {
        String[] columns = new String[3];
        columns[0] = "Bedrooms";
        columns[1] = "Type";
        columns[2] = "Booked";
        return columns;
    }

    public Room(int bedrooms, Type type)
    {
        super();
        this.booked = false;
        this.bedrooms = bedrooms;
        this.type = type;
        this.selected = false;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public Type getType() {
        return type;
    }

    public boolean isBooked() {
        return booked;
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
