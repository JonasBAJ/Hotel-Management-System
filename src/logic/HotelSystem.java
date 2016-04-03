package logic;


import javafx.collections.FXCollections;
import logic.employees.Chef;
import logic.employees.Manager;
import logic.employees.Receptionist;

import java.util.List;


/**
 *
 */
public class HotelSystem
{
    /**
     * Field hotels contains list of all hotels entered to the system.
     */
    private List<Hotel> hotels;
    /**
     * Field users contains list of all user in the system.
     */
    private List<User> users;

    /**
     * Constructor.
     */
    public HotelSystem() {
        this.users = FXCollections.observableArrayList();
        this.hotels = FXCollections.observableArrayList();
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
    public String[] getHotelNames() {
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
            users.add(newUser);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Method loads test data into the system.
     */
    public void loadTestData()
    {
        hotels.add(new Hotel("Hotel1"));
        hotels.add(new Hotel("Hotel2"));
        hotels.add(new Hotel("Hotel3"));
        hotels.add(new Hotel("Hotel4"));
        hotels.add(new Hotel("Hotel5"));
        hotels.add(new Hotel("Hotel6"));
        hotels.add(new Hotel("Hotel7"));

        hotels.forEach(hotel -> {
            hotel.addEmployee(new Chef("Name1", "Surname1"));
            hotel.addEmployee(new Manager("Name2", "Surname2"));
            hotel.addEmployee(new Chef("Name3", "Surname3"));
            hotel.addEmployee(new Manager("Name4", "Surname4"));

            hotel.addRoom(new Room(4, Room.Type.PREMIUM));
            hotel.addRoom(new Room(1, Room.Type.STANDARD));
            hotel.addRoom(new Room(1, Room.Type.SUITE));
        });

        getHotelByName("Hotel1").addRoom(new Room(4, Room.Type.PREMIUM));
        getHotelByName("Hotel1").addRoom(new Room(1, Room.Type.STANDARD));
        getHotelByName("Hotel1").addRoom(new Room(2, Room.Type.SUITE));
        getHotelByName("Hotel1").addRoom(new Room(3, Room.Type.PREMIUM));

        getHotelByName("Hotel2").addEmployee(new Receptionist("Name5", "Surname5"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Name6", "Surname6"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Name7", "Surname7"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Name8", "Surname8"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Name9", "Surname9"));
    }
}
