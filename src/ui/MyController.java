package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Controller;
import logic.HotelSystem;
import ui.addInterface.AddUI;


public class MyController extends HotelSystem implements Controller
{
    private Stage stage;

    public MyController(Stage stage)
    {
        this.stage = stage;
    }

    public void showLogin()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui_login.fxml"));
        try {
            GridPane loginForm = loader.load();
            Scene scene = new Scene(loginForm);
            LoginUI loginUI = loader.getController();
            loginUI.initForm(this);
            stage.setScene(scene);
            stage.setTitle("LoginUI window");
            stage.show();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void showMainAdmin()
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui_admin.fxml"));
        try {
            SplitPane adminFrom = loader.load();
            Scene scene = new Scene(adminFrom);
            AdminUI adminUI = loader.getController();
            adminUI.initAdminForm(this);
            stage.setScene(scene);
            stage.setTitle("System admin window");
            stage.show();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void showAddWindow(AddUI addInterface)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui_add.fxml"));
        try {
            Stage secondStage = new Stage();
            loader.setController(addInterface);
            GridPane addForm = loader.load();
            addInterface.setStage(secondStage);
            addInterface.initForm();
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setScene(new Scene(addForm));
            secondStage.setTitle("Add wizard");
            secondStage.showAndWait();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void showMainEmployee(String sessionID, String hotelId)
    {
        System.out.println(sessionID + hotelId + "logged in as employee");
        System.out.println("Not implemented");
    }
}
