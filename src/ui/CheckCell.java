package ui;

import javafx.geometry.Pos;
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
            final CheckBox checkBox = new CheckBox();

            @Override
            public void updateItem(Boolean item, boolean empty)
            {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                    setAlignment(Pos.CENTER);
                    setText(null);
                }
                else {
                    checkBox.setOnAction(e -> {
                        Selectable selectable = getTableView().getItems().get(getIndex());
                        selectable.setSelected(checkBox.isSelected());
                    });
                    checkBox.setSelected(getTableView().getItems().get(getIndex()).getSelected());
                    setGraphic(checkBox);
                    setAlignment(Pos.CENTER);
                    setText(null);
                }
            }
        };
    }
}


