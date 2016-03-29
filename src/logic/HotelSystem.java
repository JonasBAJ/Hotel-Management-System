package logic;


import javafx.collections.FXCollections;
import logic.employees.Chef;
import logic.employees.Manager;
import logic.employees.Receptionist;

import java.util.List;

public class HotelSystem
{
    private List<Hotel> hotels;
    private List<User> users;

    public HotelSystem() {
        this.users = FXCollections.observableArrayList();
        this.hotels = FXCollections.observableArrayList();
    }

    public List<Hotel> getHotels() {
        return hotels;
    }

    public String[] getHotelNames() {
        String[] namesArray = new String[hotels.size()];
        for (int i = 0; i < hotels.size(); i++) {
            namesArray[i] = hotels.get(i).toString();
        }
        return namesArray;
    }

    public Hotel getHotelByName(String name)
    {
        for (Hotel hotel : hotels)
        {
            if (hotel.toString().equals(name))
                return hotel;
        }
        return null;
    }

    public Boolean authorize(User.Data userData)
    {
        User newUser = new User(userData);
        if (newUser.getAuth() > 0) {
            users.add(newUser);
            return true;
        }
        else {
            return false;
        }
    }

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
            hotel.addEmployee(new Chef("Valodia", "Pankratjef"));
            hotel.addEmployee(new Manager("Piotr", "Ch"));
            hotel.addEmployee(new Chef("Jurgis", "Jurgelevicius"));
            hotel.addEmployee(new Manager("Simonas", "Donskovas"));

            hotel.addRoom(new Room(4, Room.Type.PREMIUM));
            hotel.addRoom(new Room(1, Room.Type.STANDARD));
            hotel.addRoom(new Room(1, Room.Type.SUITE));
        });

        getHotelByName("Hotel1").addRoom(new Room(4, Room.Type.PREMIUM));
        getHotelByName("Hotel1").addRoom(new Room(1, Room.Type.STANDARD));
        getHotelByName("Hotel1").addRoom(new Room(2, Room.Type.SUITE));
        getHotelByName("Hotel1").addRoom(new Room(3, Room.Type.PREMIUM));

        getHotelByName("Hotel2").addEmployee(new Receptionist("Dzordza", "Karaliene"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Vitalija", "Katunskyte"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Dainius", "Pa"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Jonas", "Ha"));
        getHotelByName("Hotel2").addEmployee(new Receptionist("Jonas", "Ha"));
    }
}
