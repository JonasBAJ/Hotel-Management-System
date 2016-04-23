package io;

import logic.Client;
import logic.Room;
import logic.employees.EmoployeeFactory;
import logic.employees.Employee;
import logic.hotels.Hotel;
import logic.tasks.Task;
import logic.tasks.TaskFactory;
import ui.MenuItem;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by jonas on 16.4.20.
 */
public class CsvImporter {

    private File file;
    private Hotel hotel;
    private String separator;
    private MenuItem item;

    public CsvImporter(Hotel hotel, MenuItem item, File file, String separator) {
        this.item = item;
        this.file = file;
        this.hotel = hotel;
        setSeparator(separator);
    }

    private void setSeparator(String separator) {
        switch (separator){
            case "|":
                this.separator = "\\|";
                break;
            case "/":
                this.separator = "/";
                break;
            case "\\":
                this.separator = "\\\\";
                break;
            default:
                this.separator = separator;
        }
    }

    public void importData() throws Exception {
        FileReader fileReader = new FileReader(file);
        LineNumberReader lineReader = new LineNumberReader(fileReader);
        String line;
        Integer lineNo = 0;
        while ((line = lineReader.readLine()) != null) {
            lineNo++;
            if (lineNo > 1)
                switch (item){
                    case EMPLOYEES:
                        parseEmployee(line);
                        break;
                    case ROOMS:
                        parseRoom(line);
                        break;
                    case CLIENTS:
                        parseClient(line);
                        break;
                    case TASKS:
                        parseTask(line);
                        break;
                }
        }
    }

    @SuppressWarnings("unchecked")
    private void parseEmployee(String line) throws Exception {
        String[] data = stringParser(line);
        try {
            Boolean selected = Boolean.parseBoolean(data[0]);
            String name = data[1];
            String surname = data[2];
            Employee.Position position = Employee.Position.parsePosition(data[3]);
            Double salary = Double.parseDouble(data[4]);
            Employee employee = EmoployeeFactory.getEmployee(name, surname, position);
            if (employee != null) {
                employee.setSelected(selected);
                employee.setSalary(salary);
                hotel.addEmployee(employee);
            } else throw new Exception("EmployeeFactory returned null!");
        } catch (Exception ex) {
            throw new Exception("Bad csv file, no Employee data to import!");
        }
    }

    private void parseRoom(String line) throws Exception {
        String[] data = stringParser(line);
        try {
            Boolean selected = Boolean.parseBoolean(data[0]);
            Integer bedrooms = Integer.parseInt(data[1]);
            Room.Type type = Room.Type.parseType(data[2]);
            Room room = new Room(bedrooms, type);
            room.setSelected(selected);
            hotel.addRoom(room);
        } catch (Exception ex) {
            throw new Exception("Bad csv file, no Employee data to import!");
        }
    }

    private void parseClient(String line) throws Exception {
        String[] data = stringParser(line);
        try {
            Boolean selected = Boolean.parseBoolean(data[0]);
            String name = data[1];
            String surname = data[2];
            LocalDate arrival = LocalDate.parse(data[3]);
            LocalDate departure = LocalDate.parse(data[4]);
            Client client = new Client(name, surname, arrival, departure);
            client.setSelected(selected);
            hotel.addClient(client);
        } catch (Exception ex) {
            throw new Exception("Bad csv file, no Employee data to import!");
        }
    }

    private void parseTask(String line) throws Exception {
        String[] data = stringParser(line);
        try {
            Boolean selected = Boolean.parseBoolean(data[0]);
            LocalDateTime assigned = LocalDateTime.parse(data[1]);
            String taskBody = data[2];
            Task.Type type = Task.Type.parseType(data[3]);
            Task task = TaskFactory.getTask(hotel, taskBody, type);
            if (task != null) {
                task.setSelected(selected);
                task.setAssigned(assigned);
                hotel.addTask(task);
            } else throw new Exception("TaskFactory returned null!");
        } catch (Exception ex) {
            throw new Exception("Bad csv file, no Employee data to import!");
        }
    }

    private String[] stringParser(String line){
        String regEx = separator + "(?=([^\"]*\"[^\"]*\")*[^\"]*$)";
        String[] split = line.split(regEx);
        String[] formattedSplit = new String[split.length];
        for (int i = 0; i < split.length; i++)
            formattedSplit[i] = split[i].replaceAll("\"", "");
        return formattedSplit;
    }
}
