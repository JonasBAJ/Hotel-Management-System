package logic.hotels;

import javafx.collections.FXCollections;
import logic.Client;
import logic.Room;
import logic.User;
import logic.employees.Chef;
import logic.employees.Manager;
import logic.employees.Receptionist;
import logic.tasks.Task;
import org.jetbrains.annotations.Contract;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Created by jonas on 16.4.7.
 */
public class HotelSystem {

    private static HotelSystem instance = null;
    private List<HotelCluster> clusters;
    private List<User> systemUsers;

    protected HotelSystem() {
        this.clusters = FXCollections.observableArrayList();
        this.systemUsers = FXCollections.observableArrayList();
    }

    @Contract(pure = true)
    public static HotelSystem getInstance()
    {
        return instance == null ? new HotelSystem(): instance;
    }

    public HotelCluster createCluster(String clusterBrand)
    {
        HotelCluster cluster = new HotelCluster(clusterBrand);
        clusters.add(cluster);
        return cluster;
    }

    public HotelCluster getClusterByBrand(String brand) {
        for (HotelCluster cluster : clusters) {
            if (cluster.getBrand().equals(brand))
                return cluster;
        }
        return null;
    }

    public String[] getClusterBrands() {
        String[] namesArray = new String[clusters.size()];
        for (int i = 0; i < clusters.size(); i++) {
            namesArray[i] = clusters.get(i).toString();
        }
        return namesArray;
    }

    /**
     * Method preforms very basic authentication routine.
     * @param userData
     *      Data object from User class, containing name, surname and user type.
     * @param passFilePath
     *      File path which contains passwords.
     * @return true or false.
     */
    public Boolean authorize(User.Data userData, String passFilePath)
    {
        User newUser = new User(userData, passFilePath);
        if (newUser.getAuth() > 0) {
            systemUsers.add(newUser);
            return true;
        }
        else {
            return false;
        }
    }

    public List<Task> getTasksByType(Task.Type type, Collection<Task> tasks)
    {
        List<Task> typeList = FXCollections.observableArrayList();
        tasks.forEach(task -> {
            if (task.getType() == type)
                typeList.add(task);
        });
        return typeList;
    }

    public List<HotelCluster> getClusters() {
        return clusters;
    }

    public void setClusters(List<HotelCluster> clusters)
    {
        this.clusters = clusters;
    }

    /**
     * Method loads test data into the HotelCluster object.
     */
    public void loadTestToCluster(HotelCluster cluster)
    {
        cluster.createHotel("Hotel1");
        cluster.createHotel("Hotel2");
        cluster.createHotel("Hotel3");
        cluster.createHotel("Hotel4");
        cluster.createHotel("Hotel5");
        cluster.createHotel("Hotel6");
        cluster.createHotel("Hotel7");
        cluster.createHotel("Hotel8");
        cluster.createHotel("Hotel9");
        cluster.createHotel("Hotel10");
        cluster.createHotel("Hotel11");
        cluster.createHotel("Hotel12");

        cluster.getHotels().forEach(hotel -> {
            hotel.addEmployee(new Chef("Name1", "Surname1"));
            hotel.addEmployee(new Manager("Name2", "Surname2"));
            hotel.addEmployee(new Chef("Name3", "Surname3"));
            hotel.addEmployee(new Manager("Name4", "Surname4"));

            Room room = new Room(4, Room.Type.PREMIUM);
            room.setClient(new Client("ClientName", "ClientSurname", LocalDate.of(2016, 5, 29), LocalDate.of(2016, 6, 1)));
            hotel.addRoom(room);
            hotel.addRoom(new Room(1, Room.Type.STANDARD));
            hotel.addRoom(new Room(1, Room.Type.SUITE));

            hotel.addTask("Task1");
            hotel.addTask("Task2");
        });

        cluster.getHotelByName("Hotel1").addRoom(new Room(4, Room.Type.PREMIUM));
        cluster.getHotelByName("Hotel1").addRoom(new Room(1, Room.Type.STANDARD));
        cluster.getHotelByName("Hotel1").addRoom(new Room(2, Room.Type.SUITE));
        cluster.getHotelByName("Hotel1").addRoom(new Room(3, Room.Type.PREMIUM));

        cluster.getHotelByName("Hotel2").addEmployee(new Receptionist("Name5", "Surname5"));
        cluster.getHotelByName("Hotel2").addEmployee(new Receptionist("Name6", "Surname6"));
        cluster.getHotelByName("Hotel2").addEmployee(new Receptionist("Name7", "Surname7"));
        cluster.getHotelByName("Hotel2").addEmployee(new Receptionist("Name8", "Surname8"));
        cluster.getHotelByName("Hotel2").addEmployee(new Receptionist("Name9", "Surname9"));
    }
}
