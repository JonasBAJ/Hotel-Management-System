package logic;

import org.jetbrains.annotations.Contract;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class User
{
    protected Data user;
    protected static Integer sessionID = 0;

    public enum Type
    {
        EMPLOYEE,
        CLIENT,
        ADMIN
    }

    public static class Data
    {
        public String name;
        public String pass;
        public Type type;

        @Override
        public String toString() {
            return name + " " + pass + " " + type;
        }
    }

    @Contract(pure = true)
    public static Type[] getUserTypes()
    {
        Type[] types = new Type[3];
        types[0] = Type.ADMIN;
        types[1] = Type.EMPLOYEE;
        types[2] = Type.CLIENT;
        return types;
    }

    public User(Data userData)
    {
        this.user = userData;
        authorize();
    }

    public Integer getAuth(){
        return sessionID;
    }

    private Integer generateID()
    {
        return (++sessionID);
    }

    private void keepBook()
    {
        try{
            FileWriter fw = new FileWriter("/home/jonas/Hotel2/src/datafiles/sessionLog.log", true);
            fw.write(sessionID.toString() + " : " + user.toString() + "\n");
            fw.close();
        } catch (IOException ex){ ex.printStackTrace(); }
    }

    private void authorize()
    {
        String pass = user.toString();
        try{
            for (String line : Files.readAllLines(Paths.get("/home/jonas/Hotel2/src/datafiles/pass"))) {
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
