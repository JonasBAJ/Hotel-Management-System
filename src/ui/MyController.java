package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.Controller;
import logic.hotels.Hotel;
import logic.hotels.HotelSystem;
import ui.addInterface.AddUI;
import ui.ioInterface.ExportWizard;
import ui.ioInterface.ImportWizard;


public class MyController implements Controller
{
    private Stage stage;
    private String passPath;
    private final HotelSystem system = HotelSystem.getInstance();

    public MyController(Stage stage)
    {
        this.stage = stage;
        Image image = new Image("/datafiles/HIcon2.png");
        stage.getIcons().add(image);
    }

    public void setPassFilePath(String path)
    {
        passPath = path;
    }

    public String getPassPath() {
        return passPath;
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

    public void showAddWizard(AddUI addInterface)
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

    public void showExportWizard(TableController table)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui_export.fxml"));
        try {
            Stage secondStage = new Stage();
            GridPane exportForm = loader.load();
            ExportWizard exportWizard = loader.getController();
            exportWizard.initForm(table, secondStage);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setScene(new Scene(exportForm));
            secondStage.setTitle("Export wizard");
            secondStage.showAndWait();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    public void showImportWizard(Hotel hotel)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui_import.fxml"));
        try {
            Stage secondStage = new Stage();
            GridPane exportForm = loader.load();
            ImportWizard importWizard = loader.getController();
            importWizard.initForm(hotel, secondStage);
            secondStage.initModality(Modality.APPLICATION_MODAL);
            secondStage.setScene(new Scene(exportForm));
            secondStage.setTitle("Import wizard");
            secondStage.showAndWait();
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    @Override
    public HotelSystem getSysNode() {
        return system;
    }

    @Override
    public void showLoadTestData() {

    }

    public void showMainEmployee(Hotel hotel)
    {
        System.out.println("Not implemented");
    }
}
