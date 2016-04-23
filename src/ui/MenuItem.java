package ui;

/**
 * Created by jonas on 16.4.8.
 */
public enum MenuItem
{
    EMPLOYEES,
    CLIENTS,
    TASKS,
    ROOMS;

    public static MenuItem[] getMenuItems() {
        MenuItem[] items = new MenuItem[4];
        items[0] = EMPLOYEES;
        items[1] = CLIENTS;
        items[2] = TASKS;
        items[3] = ROOMS;
        return items;
    }
}
