package ui;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import logic.Client;
import logic.Hotel;
import logic.Room;
import logic.employees.Employee;
import ui.addInterface.AddClient;
import ui.addInterface.AddEmployee;
import ui.addInterface.AddRoom;
import ui.addInterface.AddUI;


public class AdminUI
{
    @FXML protected TreeView<String> treeView;
    @FXML protected Label mainLabel;
    @FXML protected BorderPane borderPane;
    @FXML protected ChoiceBox<String> hotelBox;
    private TreeController treeController;
    private TableController tableController;
    private MyController myController;

    private enum MenuItem
    {
        EMPLOYEES,
        CLIENTS,
        TASKS,
        ROOMS
    }

    public void initAdminForm(MyController myController)
    {
        this.myController = myController;
        this.treeController = new TreeController(this);
        treeController.initTreeView();
        this.tableController = new TableController(this);
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
                updateTable(MenuItem.EMPLOYEES);
                addButton(initAddButton("+", new AddEmployee(myController, hotelBox.getValue())),
                        initRemoveButton("Employees"),
                        initExportButton());
                break;
            case "Assign task":
                initNullScene("Employees > Assign task");
                break;
            case "All tasks":
                initNullScene("Employees > All tasks");
                updateTable(MenuItem.TASKS);
                addButton(initExportButton());
                break;

            //Clients branch
            case "Clients":
                initNullScene("Clients");
                break;
            case "All clients":
                initNullScene("Clients > All clients");
                updateTable(MenuItem.CLIENTS);
                addButton(initAddButton("+", new AddClient(myController, hotelBox.getValue())),
                        initRemoveButton("Clients"),
                        initExportButton());
                break;

            //Rooms branch
            case "Rooms":
                initNullScene("Rooms");
                break;
            case "All rooms":
                initNullScene("Rooms > Show all rooms");
                updateTable(MenuItem.ROOMS);
                addButton(initAddButton("+", new AddRoom(myController, hotelBox.getValue())),
                        initRemoveButton("Rooms"),
                        initExportButton());
                break;
        }
    }

    private void updateTable(MenuItem item)
    {
        String hotelName = hotelBox.getValue();
        Hotel hotel = myController.getHotelByName(hotelName);
        if (hotelName != null && hotel != null) {
            switch (item) {
                case EMPLOYEES:
                    tableController.updateTableView(hotel.getEmployees(), Employee.getFieldNames());
                    break;
                case CLIENTS:
                    tableController.updateTableView(hotel.getClients(), Client.getFieldNames());
                    break;
                case TASKS:
                    String[] fname = new String[1];
                    fname[0] = "Type";
                    tableController.updateTableView(myController.getAllTasks(), fname);
                    break;
                case ROOMS:
                    tableController.updateTableView(hotel.getRooms(), Room.getFieldNames());
                    break;
            }
        }
    }

    private void initHotelBox()
    {
        if (!myController.getHotels().isEmpty())
        {
            String[] names = myController.getHotelNames();
            hotelBox.getItems().addAll(names);
            hotelBox.setValue(names[0]);
            hotelBox.setOnAction(treeController);
        }
        else
        {
            System.out.println("No data loaded!");
        }
    }

    private void addButton(Button... btn)
    {
        borderPane.setBottom(initHBox(10, Pos.CENTER_RIGHT, new Insets(5,5,5,5), btn));
    }
    
    private Button initRemoveButton(String type)
    {
        Button button = new Button("-");
        button.setOnAction(e -> {
            Hotel hotel = myController.getHotelByName(hotelBox.getValue());
            switch (type){
                case "Employees":
                    hotel.removeSelected(hotel.getEmployees());
                    updateTable(MenuItem.EMPLOYEES);
                    break;
                case "Rooms":
                    hotel.removeSelected(hotel.getRooms());
                    updateTable(MenuItem.ROOMS);
                    break;
                case "Clients":
                    hotel.removeSelected(hotel.getClients());
                    updateTable(MenuItem.CLIENTS);
                    break;
            }
        });
        return button;
    }

    private Button initExportButton()
    {
        Button button = new Button("Export to CSV");
        button.setOnAction(e -> System.out.println("Not implemented"));
        button.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (button.isFocused() && e.getCode() == KeyCode.ENTER)
                System.out.println("Not implemented");
        });
        return button;
    }

    private Button initAddButton(String buttonText, AddUI addInterface)
    {
        Button button = new Button(buttonText);
        button.setOnAction(e -> {
            try {
                myController.showAddWizard(addInterface);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
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