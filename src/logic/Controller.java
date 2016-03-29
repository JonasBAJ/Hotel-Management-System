package logic;

import ui.addInterface.AddUI;


public interface Controller
{
    void showLogin();

    void showMainAdmin();

    void showMainEmployee(String session_ID, String hotel_id);  // Not implemented

    void showAddWindow(AddUI addInterface);
}
