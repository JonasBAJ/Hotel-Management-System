package logic.hotels;


import javafx.collections.FXCollections;
import logic.Selectable;
import logic.tasks.Task;
import logic.tasks.TaskFactory;

import java.util.Collection;
import java.util.List;


/**
 * HotelSystem class is main class for controlling all system
 * object of this class contains all needed getters and setters
 * for this program to run. This class must extend controller
 * object to create user interface.
 */
public class HotelCluster implements Selectable
{
    /**
     * Field hotels contains list of all hotels entered to the system.
     */
    private List<Hotel> hotels;
    /**
     * Field users contains list of all tasks in the system.
     */
    private List<Task> tasks;
    /**
     * Field contains brand name of hotel cluster.
     */
    private String brand;
    /**
     * Field contains boolean value for easier manipulation.
     */
    protected Boolean selected;

    /**
     * Constructor.
     */
    public HotelCluster(String clusterBrand) {
        this.hotels = FXCollections.observableArrayList();
        this.tasks = FXCollections.observableArrayList();
        this.brand = clusterBrand;
    }

    @Override
    public String[] getColumnNames() {
        String[] columns = new String[2];
        columns[0] = "Selected";
        columns[1] = "Brand";
        return columns;
    }

    /**
     * Method returns all hotels in the system.
     * @return hotels
     */
    public List<Hotel> getHotels() {
        return hotels;
    }

    /**
     * Method return hotel names as string array for easier ui development,
     * to quickly load data to drop menus or simply display hotel names.
     * @return namesArray
     */
    public String[] getClusterNames() {
        String[] namesArray = new String[hotels.size()];
        for (int i = 0; i < hotels.size(); i++) {
            namesArray[i] = hotels.get(i).toString();
        }
        return namesArray;
    }

    /**
     * Method returns Hotel object by name if such object doesn't exist returns null.
     * @param name
     *      Hotel object name.
     * @return null or Hotel object.
     */
    public Hotel getHotelByName(String name)
    {
        for (Hotel hotel : hotels)
        {
            if (hotel.toString().equals(name))
                return hotel;
        }
        return null;
    }

    public void createHotel(String name) {
        this.hotels.add(new Hotel(name));
    }

    public void addHotel(Hotel hotel) {
        if (!hotels.contains(hotel))
            hotels.add(hotel);
    }

    public void removeHotel(Hotel hotel) {
        if (hotels.contains(hotel))
            hotels.remove(hotel);
    }

    public void addTask(String task)
    {
        this.tasks.add(TaskFactory.getTask(this, task));
    }

    public void addTasks(Collection<Task> tasks)
    {
        tasks.forEach(task -> {
            if (task instanceof HotelCluster)
                this.tasks.addAll(tasks);
        });
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<Task> getAllTasks()
    {
        return this.tasks;
    }

    @Override
    public Boolean getSelected() {
        return this.selected;
    }

    @Override
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return this.brand;
    }

    @Override
    public String[] getFields() {
        String [] fieldValues = new String[6];
        return fieldValues;
    }
}
