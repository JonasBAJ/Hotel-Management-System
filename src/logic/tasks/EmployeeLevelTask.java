package logic.tasks;

import javafx.collections.FXCollections;
import logic.hotels.Hotel;
import logic.hotels.HotelCluster;
import logic.employees.Employee;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Collection;
import java.util.List;

/**
 * Created by jonas on 16.4.7.
 */
public class EmployeeLevelTask implements Task
{
    protected Boolean selected;
    protected List<Employee> children;
    protected LocalDateTime assigned;
    protected String task;
    protected Type type;

    public EmployeeLevelTask(Collection<Employee> children, String task)
    {
        this.children = FXCollections.observableArrayList();
        this.children.addAll(children);
        this.task = task;
        this.selected = false;
        this.type = Type.EMPLOYEE_LEVEL;
        setTimestamp();
    }

    public EmployeeLevelTask(Employee children, String task)
    {
        this.children = FXCollections.observableArrayList();
        this.children.add(children);
        this.task = task;
        this.selected = false;
        this.type = Type.EMPLOYEE_LEVEL;
        setTimestamp();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Employee> getTaskLevelObjects() {
        return this.children;
    }

    @Override
    public List<String> getChildrenNames() {
        List<String> list = FXCollections.observableArrayList();
        this.children.forEach(employee -> list.add(employee.toString()));
        return list;
    }

    @Override
    public String getTask() {
        return this.task;
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
                    return TaskFactory.getClusterLevel(((Collection<HotelCluster>) children), this.task);
                case HOTEL_LEVEL:
                    return TaskFactory.getHotelLevel(((Collection<Hotel>) children), this.task);
            }
        return this;
    }

    @Override
    public <T> Task promoteTo(Type type, T child) {
        if (this.type != type)
            switch (type){
                case SYSTEM_LEVEL:
                    return TaskFactory.getTask(child, this.task);
                case HOTEL_LEVEL:
                    return TaskFactory.getTask(child, this.task);
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

    @Override
    public String getAssigned() {
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("dd-MMM-yyyy HH:mm:ss")
                .toFormatter();
        return assigned.format(format);
    }

    @Override
    public void setAssigned(LocalDateTime assigned) {
        this.assigned = assigned;
    }

    @Override
    public String toString() {
        return selected +" "+
                assigned +" "+
                task +" "+
                type;
    }

    private void setTimestamp() {
        this.assigned = LocalDateTime.now();
    }

    @Override
    public String[] getColumnNames() {
        String[] columns = new String[4];
        columns[0] = "Selected";
        columns[1] = "Assigned";
        columns[2] = "Task";
        columns[3] = "Type";
        return columns;
    }

    @Override
    public String[] getFields() {
        String [] fieldValues = new String[4];
        fieldValues[0] = getSelected().toString();
        fieldValues[1] = getAssigned();
        fieldValues[2] = getTask();
        fieldValues[3] = getType().toString();
        return fieldValues;
    }
}
