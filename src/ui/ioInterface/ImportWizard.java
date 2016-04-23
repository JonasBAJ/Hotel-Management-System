package ui.ioInterface;

import io.CsvImporter;
import io.Deserializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.Client;
import logic.Room;
import logic.Selectable;
import logic.employees.Employee;
import logic.hotels.Hotel;
import logic.tasks.Task;
import ui.MenuItem;

import java.io.File;
import java.util.List;

/**
 * Created by jonas on 16.4.21.
 */
public class ImportWizard {

    @FXML protected ChoiceBox<String> importBox;
    @FXML protected ChoiceBox<String> delimiterBox;
    @FXML protected ChoiceBox<MenuItem> objectBox;
    @FXML protected Button importButton;
    @FXML protected Button cancelButton;
    private Hotel hotel;
    private Stage stage;

    public void initForm(Hotel hotel, Stage stage) {
        this.hotel = hotel;
        this.stage = stage;
        initCancelButton();
        initDelimiterBox();
        initObjectBox();
        initImportBox();
    }

    private void initImportBox() {
        importBox.getItems().addAll("Deserialize", "CSV");
        importBox.setValue("Deserialize");
        objectBox.setDisable(true);
        delimiterBox.setDisable(true);
        importBox.setOnAction(event -> {
            if (importBox.getValue().equals("Deserialize")) {
                objectBox.setDisable(true);
                delimiterBox.setDisable(true);
                initDeserializeButton();
            }
            else {
                objectBox.setDisable(false);
                delimiterBox.setDisable(false);
                initCsvButton();
            }
        });
    }

    private void initDelimiterBox() {
        delimiterBox.getItems().addAll(",", ";", ":", "|", "/", "\\");
        delimiterBox.setValue(",");
    }

    private void initObjectBox() {
        objectBox.getItems().addAll(MenuItem.getMenuItems());
        objectBox.setValue(MenuItem.getMenuItems()[0]);
    }

    private void initDeserializeButton()
    {
        importButton.setOnAction(e -> {
            try {
                Stage newStage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Open Resource File");
                File file = fileChooser.showOpenDialog(newStage);
                List<Selectable> list =  Deserializer.deserialize(file);
                uploadDeserializedData(list);
                stage.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    private void initCsvButton()
    {
        importButton.setOnAction(e -> {
            Stage newStage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            File file = fileChooser.showOpenDialog(newStage);
            if (file != null) {
                CsvImporter importer = new CsvImporter(hotel, objectBox.getValue(), file, delimiterBox.getValue());
                try {
                    importer.importData();
                    stage.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    private <T> void uploadDeserializedData(List<T> list) {
        Selectable selectable = (Selectable) list.get(0);
        if (selectable instanceof Employee)
            hotel.addEmployees(((List<Employee>) list));
        else if (selectable instanceof Room)
            hotel.addRooms(((List<Room>) list));
        else if (selectable instanceof Client)
            hotel.addClients(((List<Client>) list));
        else if (selectable instanceof Task)
            hotel.addTasks(((List<Task>) list));
    }

    private void initCancelButton() {
        cancelButton.setOnAction(event -> stage.close());
    }
}
