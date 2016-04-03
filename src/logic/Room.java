package logic;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import org.jetbrains.annotations.Contract;

/**
 * Room class is supplement class for hotel object.
 */
public class Room implements Selectable
{
    /**
     * Field contains bedroom count.
     */
    private final int bedrooms;
    /**
     * Field contains type of room.
     */
    private final Type type;
    /**
     * Field contains boolean value of this room status.
     */
    private boolean booked;
    /**
     * For now this field is not implemented.
     */
    private Boolean selected;

    /**
     * Enum of room types.
     */
    public enum Type
    {
        STANDARD,
        PREMIUM,
        SUITE
    }

    /**
     * Method returns array of available bedroom counts for easier ui development,
     * to quickly display information about available count of bedrooms in one room.
     * @return counts
     */
    @Contract(pure = true) public static Integer[] getBedroomCount()
    {
        Integer[] counts = new Integer[4];
        counts[0] = 1;
        counts[1] = 2;
        counts[2] = 3;
        counts[3] = 4;
        return counts;
    }

    /**
     * Method returns array of available room types for easier ui development,
     * to quickly display information in droop down list or etc.
     * @return types
     */
    @Contract(pure = true) public static Type[] getTypes()
    {
        Type[] types = new Type[3];
        types[0] = Type.PREMIUM;
        types[1] = Type.STANDARD;
        types[2] = Type.SUITE;
        return types;
    }

    /**
     * Method returns string array of this object fields for easier ui development,
     * to quickly display information in droop down list or etc.
     * @return fields
     */
    @Contract(pure = true) public static String[] getFieldNames()
    {
        String[] fields = new String[3];
        fields[0] = "Bedrooms";
        fields[1] = "Type";
        fields[2] = "Booked";
        return fields;
    }

    /**
     * Constructor.
     * @param bedrooms
     *      Count of bedrooms in this room.
     * @param type
     *      Type of new room.
     */
    public Room(int bedrooms, Type type)
    {
        super();
        this.booked = false;
        this.bedrooms = bedrooms;
        this.type = type;
        this.selected = false;
    }

    /**
     * Method returns count of bedrooms in room object.
     * @return bedrooms
     */
    public int getBedrooms() {
        return bedrooms;
    }

    /**
     * Method returns type in room object.
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Method returns boolean value of room status.
     * @return booked
     */
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
