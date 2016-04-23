package logic;

import javafx.application.Application;
import javafx.stage.Stage;
import logic.hotels.Hotel;
import logic.hotels.HotelCluster;
import ui.MyController;


/**
 * Program entry point.
 */
public class Main extends Application
{
    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        MyController myController = new MyController(primaryStage);
        myController.setPassFilePath("/home/jonas/Hotel2/src/datafiles/pass");
        HotelCluster cluster1 = myController.getSysNode().createCluster("HotelCluster1");
        HotelCluster cluster2 = myController.getSysNode().createCluster("HotelCluster2");
        myController.getSysNode().loadTestToCluster(cluster1);
        myController.getSysNode().loadTestToCluster(cluster2);
        myController.showLogin();
    }
}
