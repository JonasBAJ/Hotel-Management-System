package logic;

import org.jetbrains.annotations.Contract;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * User class preforms user login routine.
 * and keeps log of users in the system.
 */
public class User
{
    protected Data user;
    protected String passFilePath;
    protected static Integer sessionID = 0;

    /**
     * Public enum Type represents user types which can login in this system.
     */
    public enum Type
    {
        EMPLOYEE,
        CLIENT,
        ADMIN
    }

    /**
     * Data class contains user data.
     */
    public static class Data
    {
        public String name;
        public String pass;
        public Type type;

        /**
         * Method returns a String object in order:
         *      name + " " + pass + " " + type
         * Example:
         *      Name Password Admin
         * @return string
         */
        @Override
        public String toString() {
            return name + " " + pass + " " + type;
        }
    }

    /**
     * This method returns array of user types for easier ui development,
     * to quickly load user types into form or table, for user to chose,
     * while attempting to login into system.
     * @return array of available user types.
     */
    @Contract(pure = true)
    public static Type[] getUserTypes()
    {
        Type[] types = new Type[3];
        types[0] = Type.ADMIN;
        types[1] = Type.EMPLOYEE;
        types[2] = Type.CLIENT;
        return types;
    }

    /**
     * Constructor for User class, requires one parameter with user info.
     * @param userData
     *      Parameter contains user name, surname and type.
     * @param passFilePath
     *      Parameter must contain full path to password file.
     */
    public User(Data userData, String passFilePath)
    {
        this.user = userData;
        this.passFilePath = passFilePath;
        authorize();
    }

    /**
     * Public method returns session ID if this number is greater than zero user
     * successfully logged in to system.
     * @return sessionID
     */
    public Integer getAuth(){
        return sessionID;
    }

    /**
     * Method increases static variable sessionID.
     * @return sessionID
     */
    private Integer generateID()
    {
        return (++sessionID);
    }

    /**
     * Method keeps log of users in the system.
     * Opens or creates file sessionLog.log in working directory.
     */
    private void keepBook()
    {
        try{
            String wDir = System.getProperty("user.dir");
            String filePath = wDir.concat("/sessionLog.log");
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(sessionID.toString() + " : " + user.toString() + "\n");
            fw.close();
        } catch (IOException ex){ ex.printStackTrace(); }
    }

    /**
     * Method reads password from pass file, and matches
     * against password and user name entered.
     */
    private void authorize()
    {
        String pass = user.toString();
        try{
            for (String line : Files.readAllLines(Paths.get(this.passFilePath))) {
                if (line.equals(pass)) {
                    sessionID = generateID();
                    keepBook();
                    break;
                }
            }
        }
        catch (IOException ex) { System.out.println(ex.toString()); }
    }
}
