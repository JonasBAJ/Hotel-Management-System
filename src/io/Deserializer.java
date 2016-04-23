package io;

import javafx.collections.FXCollections;
import logic.Selectable;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by jonas on 16.4.21.
 */
public class Deserializer {

    @SuppressWarnings("unchecked")
    public static <T> List<T> deserialize(File file) throws Exception
    {
        List<Selectable> objects = FXCollections.observableArrayList();
        FileInputStream fileIn = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        try {
            Selectable selectable;
            while ((selectable = (Selectable) in.readObject()) != null)
                objects.add(selectable);
            in.close();
            fileIn.close();
        } catch(EOFException ex) {
            in.close();
            fileIn.close();
        } catch (ClassNotFoundException ex) {
            throw new Exception("No such object!");
        }
        return (List<T>) objects;
    }

}
