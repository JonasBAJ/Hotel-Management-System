package ui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Selectable;

import java.util.List;


public class TableController extends TableView
{
    private AdminUI adminUI;

    public TableController(AdminUI adminUI)
    {
        this.adminUI = adminUI;
        setPadding(new Insets(5, 5, 5, 5));
    }

    public TableController(AdminUI adminUI, Insets value) {
        this.adminUI = adminUI;
        setPadding(value);
    }

    @SuppressWarnings("unchecked")
    public <T> void updateTableView(List<T> objects)
    {
        List<Selectable> list = ((List<Selectable>) objects);
        if (!list.isEmpty()) {
            String[] colNames = list.get(0).getColumnNames();
            getColumns().removeAll(getColumns());
            for (String colName : colNames)
            {
                if (colName.equals("Selected"))
                    getColumns().add(getBoolColumn(colName));
                else
                    getColumns().add(getColumn(colName));
            }
            setItems((ObservableList<Selectable>) objects);
            adminUI.borderPane.setCenter(this);
        }
    }

    private <T, S> TableColumn<T, S> getColumn(String colName)
    {
        TableColumn<T, S> nameColumn = new TableColumn<>(colName);
        nameColumn.setResizable(true);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>(colName.toLowerCase()));
        return nameColumn;
    }

    private TableColumn<Selectable, Boolean> getBoolColumn(String colName)
    {
        TableColumn<Selectable, Boolean> boolColumn = new TableColumn<>(colName);
        boolColumn.setResizable(true);
        boolColumn.setEditable(true);
        boolColumn.setCellFactory(new CheckCell());
        return boolColumn;
    }
}