package logic;

import javafx.collections.FXCollections;
import logic.employees.Employee;

import java.util.List;

public class Hotel
{
    private String hotelName;
    private Integer roomsCount = 0;
    private Integer personnelCount = 0;
    private Integer clientCount = 0;
    private List<Employee> employees;
    private List<Client> clients;
    private List<Room> rooms;


    public Hotel(String name)
    {
        this.hotelName = name;
        this.employees = FXCollections.observableArrayList();
        this.clients = FXCollections.observableArrayList();
        this.rooms = FXCollections.observableArrayList();
    }

    public void setName(String hotelName)
    {
        this.hotelName = hotelName;
    }

    public String getName()
    {
        return hotelName;
    }

    public int getPersonnelCount()
    {
        return personnelCount;
    }

    public int getRoomsCount()
    {
        return roomsCount;
    }

    public int getRomsCount()
    {
        return clientCount;
    }

    public void addEmployee(Employee employee)
    {
        employees.add(employee);
        personnelCount++;
    }

    public void addRoom(Room room)
    {
        rooms.add(room);
        roomsCount++;
    }

    public List<Employee> getEmployees()
    {
        return employees;
    }

    public List<Room> getRooms()
    {
        return rooms;
    }

    public List<Client> getClients()
    {
        return clients;
    }

    public void addClient(Client client)
    {
        clients.add(client);
        clientCount++;
    }

    @Override public String toString()
    {
        return getName();
    }
}
