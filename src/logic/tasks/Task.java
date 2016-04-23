package logic.tasks;

import logic.Selectable;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Created by jonas on 16.4.6.
 */
public interface Task extends Selectable
{
    enum Type{
        SYSTEM_LEVEL,
        HOTEL_LEVEL,
        EMPLOYEE_LEVEL;

        public static Type parseType(String type) {
            switch (type){
                case "SYSTEM_LEVEL":
                    return SYSTEM_LEVEL;
                case "HOTEL_LEVEL":
                    return HOTEL_LEVEL;
                case "EMPLOYEE_LEVEL":
                    return EMPLOYEE_LEVEL;
                default:
                    return null;
            }
        }
    }

    <T> List<T> getTaskLevelObjects();

    <T> void setTaskLevelObjects(Collection<T> children);

    <T> Task promoteTo(Type type, Collection<T> children);

    <T> Task promoteTo(Type type, T child);

    List<String> getChildrenNames();

    String getTask();

    String getAssigned();

    void setAssigned(LocalDateTime assigned);

    Type getType();

    String toString();
}
