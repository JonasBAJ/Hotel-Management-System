package logic;

import javafx.application.Application;
import javafx.stage.Stage;
import ui.MyController;

public class Main  extends Application
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
        myController.showLogin();
    }
}
