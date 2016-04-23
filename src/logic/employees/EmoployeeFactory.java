package logic.employees;

/**
 * Created by jonas on 16.4.21.
 */
public class EmoployeeFactory {

    public static Employee getChef(String name, String surname){
        return new Chef(name, surname);
    }

    public static Employee getReceptionist(String name, String surname){
        return new Receptionist(name, surname);
    }

    public static Employee getManager(String name, String surname){
        return new Manager(name, surname);
    }

    public static Employee getEmployee(String name, String surname, Employee.Position position) {
        switch (position) {
            case MANAGER:
                return getManager(name, surname);
            case RECEPTIONIST:
                return getReceptionist(name, surname);
            case CHEF:
                return getChef(name, surname);
            default:
                return null;
        }
    }
}
