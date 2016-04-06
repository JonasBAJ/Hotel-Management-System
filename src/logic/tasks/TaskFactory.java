package logic.tasks;

import logic.Hotel;
import logic.HotelSystem;
import logic.employees.Employee;
import org.jetbrains.annotations.Contract;

import java.util.Collection;

/**
 * Created by jonas on 16.4.7.
 */
public class TaskFactory
{
    @Contract("_ -> !null")
    public static Task getHotelLevel(Collection<Hotel> hotels) {
        return new HotelLevelTask(hotels);
    }

    @Contract("_ -> !null")
    public static Task getSystemLevel(Collection<HotelSystem> hotelSystems) {
        return new SystemLevelTask(hotelSystems);
    }

    @Contract("_ -> !null")
    public static Task getEmployeeLevel(Collection<Employee> employees) {
        return new EmployeeLevelTask(employees);
    }

    @Contract("_ -> !null")
    public static Task getHotelLevel(Hotel hotel) {
        return new HotelLevelTask(hotel);
    }

    @Contract("_ -> !null")
    public static Task getSystemLevel(HotelSystem hotelSystem) {
        return new SystemLevelTask(hotelSystem);
    }

    @Contract("_ -> !null")
    public static Task getEmployeeLevel(Employee employee) {
        return new EmployeeLevelTask(employee);
    }
}
