package logic;

import javafx.collections.FXCollections;
import logic.employees.Employee;

import java.util.List;


/**
 * Hotel class is one of the core classes of this project.
 * Each Hotel object contains its employees, rooms, clients
 * and can manipulate them (add, delete, edit).
 */
public class Hotel
{
    /**
     * Field contains hotel name.
     */
    private String hotelName;
    /**
     * Field contains room count.
     */
    private Integer roomsCount = 0;
    /**
     * Field contains personnel count.
     */
    private Integer personnelCount = 0;
    /**
     * Field contains client count.
     */
    private Integer clientCount = 0;
    /**
     * Field contains list of employees
     * which are working in this hotel.
     */
    private List<Employee> employees;
    /**
     * Field contains list of clients
     * which are staying in this hotel.
     */
    private List<Client> clients;
    /**
     * Field contains list of rooms
     * in this hotel.
     */
    private List<Room> rooms;


    /**
     * Constructor.
     * @param name
     *      The name of current hotel.
     */
    public Hotel(String name)
    {
        this.hotelName = name;
        this.employees = FXCollections.observableArrayList();
        this.clients = FXCollections.observableArrayList();
        this.rooms = FXCollections.observableArrayList();
    }

    /**
     * Setter for object name.
     * @param hotelName
     *      The new name of this hotel.
     */
    public void setName(String hotelName)
    {
        this.hotelName = hotelName;
    }

    /**
     * Name getter.
     * @return hotelName
     */
    public String getName()
    {
        return hotelName;
    }

    /**
     * Personnel count getter.
     * @return personnelCount
     */
    public int getPersonnelCount()
    {
        return personnelCount;
    }

    /**
     * Rooms count getter.
     * @return roomsCount
     */
    public int getRoomsCount()
    {
        return roomsCount;
    }

    /**
     * Client count getter.
     * @return clientCount
     */
    public int getClientCount()
    {
        return clientCount;
    }

    /**
     * Method adds new employee to this hotel object.
     * @param employee
     *      New employee object.
     */
    public void addEmployee(Employee employee)
    {
        employees.add(employee);
        personnelCount++;
    }

    /**
     * Method adds new room to this hotel object.
     * @param room
     *      New room object.
     */
    public void addRoom(Room room)
    {
        rooms.add(room);
        roomsCount++;
    }

    /**
     * Method returns list of employees in this hotel.
     * @return employees
     */
    public List<Employee> getEmployees()
    {
        return employees;
    }

    /**
     * Method returns list of rooms in this hotel.
     * @return rooms
     */
    public List<Room> getRooms()
    {
        return rooms;
    }

    /**
     * Method returns list of clients in this hotel.
     * @return clients
     */
    public List<Client> getClients()
    {
        return clients;
    }

    /**
     * Method adds new client to this hotel object.
     * @param client
     *      New client object.
     */
    public void addClient(Client client)
    {
        clients.add(client);
        clientCount++;
    }

    /**
     * Method returns hotel name.
     * @return hotelName
     */
    @Override public String toString()
    {
        return getName();
    }
}
