package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TreeItem;


public class TreeController implements ChangeListener<TreeItem<String>>, EventHandler<ActionEvent>
{
    private AdminUI adminUI;

    public TreeController(AdminUI adminUI)
    {
        this.adminUI = adminUI;
    }

    public void initTreeView()
    {
        TreeItem<String> root, employeesBranch, clientsBranch, roomsBranch;
        root = new TreeItem<>();
        root.setExpanded(true);

        employeesBranch = makeBranch("Employees", root);
        makeBranch("All employees", employeesBranch);
        makeBranch("Assign task", employeesBranch);
        makeBranch("All tasks", employeesBranch);

        clientsBranch = makeBranch("Clients", root);
        makeBranch("All clients", clientsBranch);

        roomsBranch = makeBranch("Rooms", root);
        makeBranch("All rooms", roomsBranch);

        adminUI.treeView.setRoot(root);
        adminUI.treeView.setShowRoot(false);
        adminUI.treeView.getSelectionModel().selectedItemProperty().addListener(this);
    }

    private TreeItem<String> makeBranch(String title, TreeItem<String> parent)
    {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }

    @Override
    public void changed(ObservableValue<? extends TreeItem<String>> observable,
                        TreeItem<String> oldValue, TreeItem<String> newValue)
    {
        adminUI.updateData(newValue.getValue());
    }

    @Override
    public void handle(ActionEvent event)
    {
        if (!adminUI.treeView.getSelectionModel().isEmpty())
            adminUI.updateData(adminUI.treeView.getSelectionModel().getSelectedItem().getValue());
    }
}
