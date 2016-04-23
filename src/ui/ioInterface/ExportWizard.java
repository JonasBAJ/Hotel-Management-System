package ui.ioInterface;

import io.CsvWriter;
import io.Serializer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.Selectable;
import ui.TableController;

import java.io.IOException;
import java.util.List;

/**
 * Created by jonas on 16.4.21.
 */
public class ExportWizard {

    @FXML protected ChoiceBox<String> exportToBox;
    @FXML protected ChoiceBox<String> delimiterBox;
    @FXML protected TextField fileNameField;
    @FXML protected Button exportButton;
    @FXML protected Button cancelButton;
    private TableController table;
    private Stage stage;

    public void initForm(TableController table, Stage stage) {
        this.table = table;
        this.stage = stage;
        initCancelButton();
        initDelimiterBox();
        initExportToBox();
        initTextField();
    }

    private void initExportToBox() {
        exportToBox.getItems().addAll("Serialize", "Export to CSV");
        exportToBox.setValue("Serialize");
        delimiterBox.setDisable(true);
        initSerializeButton();
        exportToBox.setOnAction(event -> {
            if (exportToBox.getValue().equals("Serialize")) {
                delimiterBox.setDisable(true);
                initSerializeButton();
            }
            else {
                delimiterBox.setDisable(false);
                initExportButton();
            }
        });
    }

    private void initDelimiterBox() {
        delimiterBox.getItems().addAll(",", ";", ":", "|", "/", "\\");
        delimiterBox.setValue(",");
    }

    private void initTextField() {
        fileNameField.setPromptText("save to file");
    }

    @SuppressWarnings("unchecked")
    private void initExportButton() {
        exportButton.setOnAction(e -> {
            CsvWriter csvWriter = new CsvWriter((List<Selectable>) table.getItems(), delimiterBox.getValue());
            try {
                csvWriter.write(fileNameField.getText() + ".csv");
                stage.close();
            } catch (IOException ex) {
                System.out.println("unable to export");
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void initSerializeButton() {
        exportButton.setOnAction(e -> {
            String fileName = fileNameField.getText() + ".ser";
            Serializer.serialize(((List<Selectable>) table.getItems()), fileName);
            stage.close();
        });
    }

    private void initCancelButton() {
        cancelButton.setOnAction(event -> stage.close());
    }
}
