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
public class HotelLevelTask implements Task
{
    /**
     * Field contains boolean value for easier manipulation in Hotel object.
     */
    protected Boolean selected;
    protected List<Hotel> children;
    protected Type type;

    public HotelLevelTask(Collection<Hotel> children) {
        this.children = FXCollections.observableArrayList();
        this.children.addAll(children);
        this.selected = false;
        this.type = Type.HOTEL_LEVEL;
    }

    public HotelLevelTask(Hotel child) {
        this.children = FXCollections.observableArrayList();
        this.children.add(child);
        this.selected = false;
        this.type = Type.HOTEL_LEVEL;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> getTaskLevelObjects() {
        return this.children;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void setTaskLevelObjects(Collection<T> children) {
        children.forEach(child -> {
            if (child instanceof Hotel)
                this.children.add((Hotel) child);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Task promoteTo(Type type, Collection<T> children) {
        System.out.println(children.getClass());
        if (this.type != type)
            switch (type){
                case SYSTEM_LEVEL:
                    return TaskFactory.getSystemLevel(((Collection<HotelSystem>) children));
                case EMPLOYEE_LEVEL:
                    return TaskFactory.getEmployeeLevel(((Collection<Employee>) children));
            }
        return this;
    }

    @Override
    public <T> Task promoteTo(Type type, T child) {
        if (this.type != type)
            switch (type){
                case SYSTEM_LEVEL:
                    return TaskFactory.getSystemLevel(((HotelSystem) child));
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
