package logic.tasks;

import logic.hotels.Hotel;
import logic.hotels.HotelCluster;
import logic.employees.Employee;
import org.jetbrains.annotations.Contract;

import java.util.Collection;

/**
 * Created by jonas on 16.4.7.
 */
public class TaskFactory
{
    @Contract("_ -> !null")
    public static Task getHotelLevel(Collection<Hotel> hotels, String task) {
        return new HotelLevelTask(hotels, task);
    }

    @Contract("_ -> !null")
    public static Task getClusterLevel(Collection<HotelCluster> hotelCluster, String task) {
        return new ClusterLevelTask(hotelCluster, task);
    }

    @Contract("_ -> !null")
    public static Task getEmployeeLevel(Collection<Employee> employees, String task) {
        return new EmployeeLevelTask(employees, task);
    }

    public static <T> Task getTask(T object, String task) {
        if (object instanceof Employee)
            return new EmployeeLevelTask((Employee) object, task);
        else if (object instanceof Hotel)
            return new HotelLevelTask((Hotel) object, task);
        else if (object instanceof HotelCluster)
            return new ClusterLevelTask((HotelCluster) object, task);
        else
            return null;
    }

    public static <T> Task getTask(T object, String task, Task.Type type) {
        switch (type) {
            case HOTEL_LEVEL:
                return new HotelLevelTask((Hotel) object, task);
            case EMPLOYEE_LEVEL:
                return new EmployeeLevelTask((Employee) object, task);
            case SYSTEM_LEVEL:
                return new ClusterLevelTask((HotelCluster) object, task);
            default:
                return null;
        }
    }
}
