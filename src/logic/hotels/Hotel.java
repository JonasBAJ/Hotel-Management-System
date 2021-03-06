package logic.hotels;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import logic.Client;
import logic.Room;
import logic.Selectable;
import logic.employees.Employee;
import logic.tasks.Task;
import logic.tasks.TaskFactory;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.prefs.NodeChangeEvent;


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
     * Field contains list of tasks for this hotel
     * in this hotel.
     */
    private List<Task> tasks;


    /**
     * Constructor.
     * @param name
     *      The name of current hotel.
     */
    public Hotel(String name)
    {
        this.hotelName = name;
        this.employees = FXCollections.observableArrayList();
        this.tasks = FXCollections.observableArrayList();
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

    public void addEmployees(Collection<Employee> employees)
    {
        this.employees.addAll(employees);
        personnelCount += employees.size();
    }

    /**
     * Method removes employee from employees list in hotel object.
     * @param employee
     *      Existing employee object.
     */
    public void removeEmployee(Employee employee)
    {
        if (employees.contains(employee)) {
            employees.remove(employee);
            personnelCount--;
        }
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

    public void addRooms(Collection<Room> rooms)
    {
        this.rooms.addAll(rooms);
        roomsCount++;
    }

    /**
     * Method removes room from rooms list in hotel object.
     * @param room
     *      Existing room object.
     */
    public void removeRoom(Room room)
    {
        if (rooms.contains(room)) {
            rooms.remove(room);
            roomsCount--;
        }
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


    public void addClients(Collection<Client> clients)
    {
        this.clients.addAll(clients);
        clientCount++;
    }

    /**
     * Method removes client from clients list in hotel object.
     * @param client
     *      Existing client object.
     */
    public void removeClient(Client client)
    {
        if (clients.contains(client)) {
            clients.remove(client);
            clientCount--;
        }
    }

    public void addTask(String task)
    {
        this.tasks.add(TaskFactory.getTask(this, task));
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    //TODO: change task type
    public void addTasks(Collection<Task> tasks)
    {
        this.tasks.addAll(tasks);
    }

    public List<Task> getTasks()
    {
        return this.tasks;
    }

    public List<Task> getTasksByType(Task.Type type)
    {
        List<Task> typeList = FXCollections.observableArrayList();
        this.tasks.forEach(task -> {
            if (task.getType() == type)
                typeList.add(task);
        });
        return typeList;
    }

    @SuppressWarnings("unchecked")
    public <T> int removeSelected(List<T> list)
    {
        try {
            List<Selectable> selectableList = ((List<Selectable>) list);
            int oldSize = selectableList.size();
            Iterator<Selectable> iterator = selectableList.iterator();
            while (iterator.hasNext()) {
                Selectable item = iterator.next();
                if (item.getSelected())
                    iterator.remove();
            }
            return oldSize - selectableList.size();
        } catch (ClassCastException ex) {
            ex.printStackTrace();
            return 0;
        }
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
