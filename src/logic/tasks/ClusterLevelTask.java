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
 * Created by jonas on 16.4.6.
 */
public class ClusterLevelTask implements Task
{
    /**
     * Field contains boolean value for easier manipulation in Hotel object.
     */
    protected Boolean selected;
    protected List<HotelCluster> children;
    protected LocalDateTime assigned;
    protected String task;
    protected Type type;

    public ClusterLevelTask(Collection<HotelCluster> children, String task) {
        this.children = FXCollections.observableArrayList();
        this.children.addAll(children);
        this.task = task;
        this.selected = false;
        this.type = Type.SYSTEM_LEVEL;
        setTimestamp();
    }

    public ClusterLevelTask(HotelCluster child, String task) {
        this.children = FXCollections.observableArrayList();
        this.children.add(child);
        this.task = task;
        this.selected = false;
        this.type = Type.SYSTEM_LEVEL;
        setTimestamp();
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
    @SuppressWarnings("unchecked")
    public List<HotelCluster> getTaskLevelObjects() {
        return this.children;
    }

    @Override
    public List<String> getChildrenNames() {
        return null;
    }

    @Override
    public String getTask() {
        return this.task;
    }

    @Override
    public <T> void setTaskLevelObjects(Collection<T> children) {
        children.forEach(child -> {
            if (child instanceof HotelCluster)
                this.children.add((HotelCluster) child);
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> Task promoteTo(Type type, Collection<T> children) {
        if (this.type != type)
            switch (type){
                case HOTEL_LEVEL:
                    return TaskFactory.getHotelLevel(((Collection<Hotel>) children), this.task);
                case EMPLOYEE_LEVEL:
                    return TaskFactory.getEmployeeLevel(((Collection<Employee>) children), this.task);
            }
        return this;
    }

    @Override
    public <T> Task promoteTo(Type type, T child) {
        if (this.type != type)
            switch (type){
                case HOTEL_LEVEL:
                    return TaskFactory.getTask(child, this.task);
                case EMPLOYEE_LEVEL:
                    return TaskFactory.getTask(child, this.task);
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
    public String[] getFields() {
        String [] fieldValues = new String[3];
        fieldValues[0] = getAssigned();
        fieldValues[1] = getTask();
        fieldValues[2] = getType().toString();
        return fieldValues;
    }
}
