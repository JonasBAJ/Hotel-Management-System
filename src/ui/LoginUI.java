package ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.User;


public class LoginUI
{
    @FXML private ChoiceBox<User.Type> userTypeBox;
    @FXML private ChoiceBox<String> employeeBox;
    @FXML private Button loginButton;
    @FXML private TextField nameField;
    @FXML private PasswordField passField;
    @FXML private Label statusBar;
    @FXML private CheckBox loadDataBox;
    private MyController myController;

    public void initForm(MyController myController)
    {
        this.myController = myController;
        this.statusBar.setText("Please enter your credentials");
        employeeBox.setDisable(true);
        initUserBox();
        initLoadDataBox();
        initPassField();
        initLoginButton();
    }

    private void initLoadDataBox()
    {
        //loadDataBox.setOnAction(e -> initEmployeeBox());
    }

    private void initLoginButton()
    {
        loginButton.setOnAction(e -> {
            if (myController.getSysNode().authorize(getUserData(), myController.getPassPath())) {
                myController.showMainAdmin();
            } else {
                statusBar.setText("No such user");
            }
        });
    }

    private void initPassField()
    {
        passField.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (passField.isFocused() && !passField.getText().equals("") && e.getCode() == KeyCode.ENTER) {
                if (myController.getSysNode().authorize(getUserData(),myController.getPassPath())) {
                    myController.showMainAdmin();
                } else {
                    statusBar.setText("No such user");
                }
            }
        });
    }

    private User.Data getUserData()
    {
        User.Data userData = new User.Data();
        userData.name = nameField.getText();
        userData.pass = passField.getText();
        userData.type = userTypeBox.getValue();
        return userData;
    }

    private void initUserBox()
    {
        userTypeBox.getItems().addAll(User.getUserTypes());
        userTypeBox.setValue(User.getUserTypes()[0]);
        userTypeBox.setOnAction(e -> {
            if (userTypeBox.getValue().equals(User.Type.EMPLOYEE))
                if (!employeeBox.getItems().isEmpty())
                    employeeBox.setDisable(false);
                else
                    employeeBox.setDisable(true);
            else
                employeeBox.setDisable(true);
        });
    }

    /*private void initEmployeeBox()
    {
        if (myController.getSysNode()..isEmpty())
        {
            myController.loadTestData();
        }
        if (loadDataBox.isSelected()) {
            employeeBox.getItems().addAll(myController.getHotelNames());
            employeeBox.setValue(myController.getHotelNames()[0]);
            if (userTypeBox.getValue().equals(User.Type.EMPLOYEE))
                employeeBox.setDisable(false);
        }
        else {
            employeeBox.getItems().removeAll(myController.getHotelNames());
            employeeBox.setDisable(true);
        }
    }*/
}
