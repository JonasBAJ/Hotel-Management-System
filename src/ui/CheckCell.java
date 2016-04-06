package ui;

import com.sun.javafx.fxml.ObservableListChangeEvent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import logic.Selectable;


public class CheckCell implements Callback<TableColumn<Selectable, Boolean>, TableCell<Selectable, Boolean>>
{
    @Override
    public TableCell<Selectable, Boolean> call(final TableColumn<Selectable, Boolean> param)
    {
        return new TableCell<Selectable, Boolean>() {

            final CheckBox checkButton = new CheckBox();

            @Override
            public void updateItem(Boolean item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setText(null);
                }
                else {
                    checkButton.setOnAction(e ->
                    {
                        Selectable selectableItem = getTableView().getItems().get(getIndex());
                        if (checkButton.isSelected())
                            selectableItem.setSelected(true);
                        else
                            selectableItem.setSelected(false);
                    });
                    setGraphic(checkButton);
                    setText(null);
                }
            }
        };
    }
}
