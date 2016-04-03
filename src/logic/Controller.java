package logic;

/**
 * Interface must be implemented by ui controller class to create custom user interface.
 */
public interface Controller
{
    /**
     * Method should set pass file field in derived class to realize login routine.
     * Setter should be called before calling showLogin method.
     * @param path
     *      Absolute pass file path.
     */
    void setPassFilePath(String path);

    /**
     * Method should allow user to login to the system by realizing all input output methods
     * to gather all required information about user to preform login attempt.
     */
    void showLogin();

    /**
     * Method should provide main menu utilities for system administrator to work with this program.
     */
    void showMainAdmin();

    /**
     * NOT IMPLEMENTED
     * Method should provide main menu utilities form employee to work whit this program.
     * @param hotel
     *      Hotel object in which employee works.
     */
    void showMainEmployee(Hotel hotel);  // Not implemented
}
