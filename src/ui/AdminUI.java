package ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import logic.hotels.Hotel;
import logic.hotels.HotelCluster;
import ui.addInterface.AddUI;
import ui.addInterface.AddUiFactory;

import java.util.List;


public class AdminUI
{
    @FXML protected TreeView<String> treeView;
    @FXML protected Label mainLabel;
    @FXML protected BorderPane borderPane;
    @FXML protected ChoiceBox<HotelCluster> clusterBox;
    @FXML protected ChoiceBox<Hotel> hotelBox;
    private TreeController treeController;
    private TableController tableController;
    private MyController myController;

    public void initAdminForm(MyController myController)
    {
        this.myController = myController;
        this.treeController = new TreeController(this);
        treeController.initTreeView();
        this.tableController = new TableController(this);
        initClusterBox();
        initHotelBox();
    }

    protected void updateData(String newValue)
    {
        switch (newValue)
        {
            //Employees branch
            case "Employees":
                initNullScene("Employees");
                break;
            case "All employees":
                initNullScene("Employees > All employees");
                displayObjectTable(MenuItem.EMPLOYEES);
                break;
            case "Assign task":
                initNullScene("Employees > Assign task");
                break;
            case "All tasks":
                initNullScene("Employees > All tasks");
                updateTable(MenuItem.TASKS);
                addButtons(initExportButton(), initImportButton());
                break;

            //Clients branch
            case "Clients":
                initNullScene("Clients");
                break;
            case "All clients":
                initNullScene("Clients > All clients");
                displayObjectTable(MenuItem.CLIENTS);
                break;

            //Rooms branch
            case "Rooms":
                initNullScene("Rooms");
                break;
            case "All rooms":
                initNullScene("Rooms > Show all rooms");
                displayObjectTable(MenuItem.ROOMS);
                break;
        }
    }

    private void displayObjectTable(MenuItem item) {
        updateTable(item);
        AddUI addUI = AddUiFactory.getAddUi(item, hotelBox.getValue());
        Button add = initAddButton(addUI, item);
        Button remove = initRemoveButton(item);
        Button export = initExportButton();
        Button importBtn = initImportButton();
        addButtons(add, remove, export, importBtn);
    }

    @SuppressWarnings("unchecked")
    private void updateTable(MenuItem item)
    {
        Hotel hotel = hotelBox.getValue();
        if (hotel != null) {
            switch (item) {
                case EMPLOYEES:
                    tableController.updateTableView(hotel.getEmployees());
                    break;
                case CLIENTS:
                    tableController.updateTableView(hotel.getClients());
                    break;
                case TASKS:
                    tableController.updateTableView(hotel.getTasks());
                    break;
                case ROOMS:
                    tableController.updateTableView(hotel.getRooms());
                    break;
            }
        }
    }

    private void initClusterBox() {

        List<HotelCluster> clusters = myController.getSysNode().getClusters();
        if (!clusters.isEmpty()) {
            clusterBox.getItems().addAll(clusters);
            clusterBox.setValue(clusters.get(0));
            clusterBox.setOnAction(treeController);
            clusterBox.setOnAction(e -> initHotelBox());
        }
    }

    private void initHotelBox()
    {
        HotelCluster cluster = clusterBox.getValue();
        List<Hotel> hotels = cluster.getHotels();
        if (!hotels.isEmpty()) {
            hotelBox.getItems().removeAll(hotelBox.getItems());
            hotelBox.getItems().addAll(hotels);
            hotelBox.setValue(hotels.get(0));
            hotelBox.setOnAction(treeController);
        }
    }

    private void addButtons(Button... btn)
    {
        borderPane.setBottom(initHBox(10, Pos.CENTER_RIGHT, new Insets(5,5,5,5), btn));
    }
    
    private Button initRemoveButton(MenuItem item)
    {
        Button button = new Button("-");
        Hotel hotel = hotelBox.getValue();
        button.setOnAction(e -> {
            switch (item){
                case EMPLOYEES:
                    hotel.removeSelected(hotel.getEmployees());
                    updateTable(MenuItem.EMPLOYEES);
                    break;
                case ROOMS:
                    hotel.removeSelected(hotel.getRooms());
                    updateTable(MenuItem.ROOMS);
                    break;
                case CLIENTS:
                    hotel.removeSelected(hotel.getClients());
                    updateTable(MenuItem.CLIENTS);
                    break;
                case TASKS:

                    break;
            }
        });
        return button;
    }

    @SuppressWarnings("unchecked")
    private Button initExportButton() {
        Button button = new Button("Export");
        button.setOnAction(e -> myController.showExportWizard(tableController));
        return button;
    }

    private Button initImportButton() {
        Button button = new Button("Import");
        button.setOnAction(e -> myController.showImportWizard(hotelBox.getValue()));
        return button;
    }

    private Button initAddButton(AddUI addInterface, MenuItem item)
    {
        Button button = new Button("+");
        button.setOnAction(e -> {
            myController.showAddWizard(addInterface);
            updateTable(item);
        });
        return button;
    }

    private HBox initHBox(double spacing, Pos value, Insets padding, Button... buttons)
    {
        HBox h_box = new HBox(spacing, buttons);
        h_box.setAlignment(value);
        h_box.setPadding(padding);
        return h_box;
    }

    private void initNullScene(String msg)
    {
        mainLabel.setText(msg);
        borderPane.setCenter(null);
        borderPane.setLeft(null);
        borderPane.setRight(null);
        borderPane.setBottom(null);
    }
}