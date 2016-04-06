package logic.tasks;

import logic.Selectable;

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
        EMPLOYEE_LEVEL
    }

    <T> List<T> getTaskLevelObjects();

    <T> void setTaskLevelObjects(Collection<T> children);

    <T> Task promoteTo(Type type, Collection<T> children);

    <T> Task promoteTo(Type type, T child);

    Type getType();
}
