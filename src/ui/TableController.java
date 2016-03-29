package ui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Selectable;

import java.util.List;
import java.util.Vector;


public class TableController extends TableView
{
    private AdminUI adminUI;
    private Integer columnWidth;

    public TableController(AdminUI adminUI)
    {
        this.adminUI = adminUI;
        setPadding(new Insets(5, 5, 5, 5));
        this.columnWidth = 100;
    }

    public TableController(AdminUI adminUI, Insets value) {
        this.adminUI = adminUI;
        setPadding(value);
        this.columnWidth = 100;
    }

    public void setMinCollumnWidth(Integer columnWidth) {
        this.columnWidth = columnWidth;
    }

    @SuppressWarnings("unchecked")
    public <T> void updateTableView(List<T> list, String[] colNames)
    {
        getColumns().removeAll(getColumns());
        getColumns().add(getBoolColumn());
        getColumns().addAll(getTableColumns(columnWidth, colNames));
        setItems((ObservableList<T>) list);
        adminUI.borderPane.setCenter(this);
    }

    private <T> Vector<TableColumn<T, String>> getTableColumns(int minWidth, String[] colNames)
    {
        Vector<TableColumn<T, String>> columns = new Vector<>();
        for (String colName : colNames) {
            TableColumn<T, String> nameColumn = new TableColumn<>(colName);
            nameColumn.setMinWidth(minWidth);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>(colName.toLowerCase()));
            columns.add(nameColumn);
        }
        return columns;
    }

    // Bedele
    private TableColumn<Selectable, Boolean> getBoolColumn()
    {

        TableColumn<Selectable, Boolean> boolColumn = new TableColumn<>("Selected");
        boolColumn.setMaxWidth(30);
        boolColumn.setEditable(true);
        boolColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        boolColumn.setCellFactory(CheckBoxTableCell.forTableColumn(boolColumn));
        //boolColumn.setCellFactory(e -> new CheckBoxTableCell<>());
        return boolColumn;
    }
}
