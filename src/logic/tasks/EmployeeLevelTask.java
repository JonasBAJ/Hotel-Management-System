package logic.tasks;

import javafx.collections.FXCollections;
import logic.Hotel;
import logic.HotelSystem;
import logic.employees.Employee;

import java.util.Collection;
import java.util.List;

/**
 * Created by jonas on 16.4.7.
 */
public class EmployeeLevelTask implements Task
{
    protected Boolean selected;
    protected List<Employee> children;
    protected Type type;

    public EmployeeLevelTask(Collection<Employee> children)
    {
        this.children = FXCollections.observableArrayList();
        this.children.addAll(children);
        this.selected = false;
        this.type = Type.EMPLOYEE_LEVEL;
    }

    public EmployeeLevelTask(Employee children)
    {
        this.children = FXCollections.observableArrayList();
        this.children.add(children);
        this.selected = false;
        this.type = Type.EMPLOYEE_LEVEL;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getTaskLevelObjects() {
        return this.children;
    }

    @Override
    public <T> void setTaskLevelObjects(Collection<T> children) {
        children.forEach(child -> {
            if (child instanceof Employee)
                this.children.add((Employee) child);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Task promoteTo(Type type, Collection<T> children) {
        if (this.type != type)
            switch (type){
                case SYSTEM_LEVEL:
                    return TaskFactory.getSystemLevel(((Collection<HotelSystem>) children));
                case HOTEL_LEVEL:
                    return TaskFactory.getHotelLevel(((Collection<Hotel>) children));
            }
        return this;
    }

    @Override
    public <T> Task promoteTo(Type type, T child) {
        if (this.type != type)
            switch (type){
                case SYSTEM_LEVEL:
                    return TaskFactory.getSystemLevel(((HotelSystem) child));
                case HOTEL_LEVEL:
                    return TaskFactory.getHotelLevel(((Hotel) child));
            }
        return this;
    }

    @Override
    public Type getType() {
        return this.type;
    }

    @Override
    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    @Override
    public Boolean getSelected() {
        return this.selected;
    }
}
