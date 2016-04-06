package logic;


/**
 * Selectable interface is not implemented yet.
 * This interface will allow to select all derived objects
 * from this class.
 */
public interface Selectable
{
    /**
     * Set should provide utility to set field to true or false.
     * @param selected
     *      New boolean value.
     */
    void setSelected(Boolean selected);

    /**
     * Method should provide utility to get current object status.
     * @return true or false
     */
    Boolean getSelected();


}
