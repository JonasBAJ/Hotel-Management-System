package ui.addInterface;

import logic.hotels.Hotel;
import org.jetbrains.annotations.Nullable;
import ui.MenuItem;


public class AddUiFactory
{
    @Nullable
    public static AddUI getAddUi(MenuItem item, Hotel hotel) {
        switch (item){
            case EMPLOYEES:
                return new AddEmployee(hotel);
            case ROOMS:
                return new AddRoom(hotel);
            case CLIENTS:
                return new AddClient(hotel);
            default:
                return null;
        }
    }
}
