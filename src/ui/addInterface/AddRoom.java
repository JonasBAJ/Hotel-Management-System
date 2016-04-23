package ui.addInterface;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import logic.hotels.Hotel;
import logic.Room;
import logic.hotels.HotelCluster;
import ui.MyController;

public class AddRoom extends AddUI
{
    ChoiceBox<Room.Type> typeBox;
    ChoiceBox<Integer> bedroomCount;

    public AddRoom(Hotel hotel)
    {
        this.hotel = hotel;
        this.typeBox = new ChoiceBox<>();
        this.bedroomCount = new ChoiceBox<>();
    }

    @Override protected void addTextField(TextField field, String promptText){}

    @Override protected void clearFields(){}

    @Override public void initForm()
    {
        initMainLabel();
        addLeftLabel("Bedrooms:");
        addLeftLabel("Type");
        addChoiceBox(bedroomCount, Room.getBedroomCount());
        addChoiceBox(typeBox, Room.getTypes());
        initAddButton();
        initCancelButton();
    }

    @Override protected void initMainLabel()
    {
        mainLabel.setText(hotel.getName() + " > Add room");
    }

    protected void preformAddition()
    {
        //TODO: validation of bedrooms
        Integer bedrooms = bedroomCount.getValue();
        hotel.addRoom(new Room(bedrooms, typeBox.getValue()));
    }
}
