package ui.addInterface;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import logic.Hotel;
import logic.Room;
import ui.MyController;

public class AddRoom extends AddUI
{
    ChoiceBox<Room.Type> typeBox;
    ChoiceBox<Integer> bedroomCount;

    public AddRoom(MyController myController, String hotelName)
    {
        this.myController = myController;
        this.hotelName = hotelName;
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
        mainLabel.setText(hotelName + " > Add room");
    }

    protected void preformAddition()
    {
        //TODO: validation of bedrooms
        Hotel hotel = myController.getHotelByName(hotelName);
        Integer bedrooms = bedroomCount.getValue();
        hotel.addRoom(new Room(bedrooms, typeBox.getValue()));
    }
}
