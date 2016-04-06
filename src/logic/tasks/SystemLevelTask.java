package logic.tasks;

import javafx.collections.FXCollections;
import logic.Hotel;
import logic.HotelSystem;
import logic.employees.Employee;

import java.util.Collection;
import java.util.List;

/**
 * Created by jonas on 16.4.6.
 */
public class SystemLevelTask implements Task
{
    /**
     * Field contains boolean value for easier manipulation in Hotel object.
     */
    protected Boolean selected;
    protected List<HotelSystem> children;
    protected Type type;

    public SystemLevelTask(Collection<HotelSystem> children) {
        this.children = FXCollections.observableArrayList();
        this.children.addAll(children);
        this.selected = false;
        this.type = Type.SYSTEM_LEVEL;
    }

    public SystemLevelTask(HotelSystem child) {
        this.children = FXCollections.observableArrayList();
        this.children.add(child);
        this.selected = false;
        this.type = Type.SYSTEM_LEVEL;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<HotelSystem> getTaskLevelObjects() {
        return this.children;
    }

    @Override
    public <T> void setTaskLevelObjects(Collection<T> children) {
        children.forEach(child -> {
            if (child instanceof HotelSystem)
                this.children.add((HotelSystem) child);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Task promoteTo(Type type, Collection<T> children) {
        if (this.type != type)
            switch (type){
                case HOTEL_LEVEL:
                    return TaskFactory.getHotelLevel(((Collection<Hotel>) children));
                case EMPLOYEE_LEVEL:
                    return TaskFactory.getEmployeeLevel(((Collection<Employee>) children));
            }
        return this;
    }

    @Override
    public <T> Task promoteTo(Type type, T child) {
        if (this.type != type)
            switch (type){
                case HOTEL_LEVEL:
                    return TaskFactory.getHotelLevel(((Hotel) child));
                case EMPLOYEE_LEVEL:
                    return TaskFactory.getEmployeeLevel(((Employee) child));
            }
        return this;
    }

    @Override
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public Boolean getSelected() {
        return this.selected;
    }

    public Type getType()
    {
        return this.type;
    }
}
