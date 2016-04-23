package logic;

import org.jetbrains.annotations.Contract;

import java.time.LocalDate;

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
     * Field contains boolean value for easier manipulation.
     */
    private Boolean selected;
    /**
     * Field contains Client object.
     */
    private Client client = null;

    /**
     * Enum of room types.
     */
    public enum Type
    {
        STANDARD,
        PREMIUM,
        SUITE;

        public static Type parseType(String type) {
            switch (type){
                case "STANDARD":
                    return STANDARD;
                case "SUITE":
                    return SUITE;
                case "PREMIUM":
                    return PREMIUM;
                default:
                    return null;
            }
        }
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
     * @return columns
     */
    @Override
    public String[] getColumnNames() {
        String[] columns = new String[4];
        columns[0] = "Selected";
        columns[1] = "Bedrooms";
        columns[2] = "Type";
        columns[3] = "Booked";
        return columns;
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
        this.bedrooms = bedrooms;
        this.type = type;
        this.selected = false;
    }

    public String getBooked() throws NullPointerException {
        try {
            String arrival = this.client.getArrival().toString();
            String departure = this.client.getDeparture().toString();
            String period = arrival + "|" + departure;
            return period;
        } catch (NullPointerException ex) {
            return "ND";
        }
    }

    public Boolean isBooked(LocalDate date) {
        if(this.client == null) {
            return false;
        } else {
            LocalDate arrival = this.client.getArrival();
            LocalDate departure = this.client.getDeparture();
            return !date.isBefore(arrival) && !date.isAfter(departure);
        }
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Method returns count of bedrooms in room object.
     * @return bedrooms
     */
    public Integer getBedrooms() {
        return bedrooms;
    }

    /**
     * Method returns type in room object.
     * @return type
     */
    public Type getType() {
        return type;
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

    @Override
    public String toString() {
        return this.getSelected() +" "+
                this.getBedrooms() +" "+
                this.getType() +" "+
                this.getBooked();
    }

    @Override
    public String[] getFields() {
        String [] fieldValues = new String[3];
        fieldValues[0] = getSelected().toString();
        fieldValues[1] = getBedrooms().toString();
        fieldValues[2] = getType().toString();
        return fieldValues;
    }
}
